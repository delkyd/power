package com.example.valve;

import android.util.Log;

/**
 * Created by Administrator on 13-6-11.
 */
public class Leakage {
	//???????
    double k_insulation=0.116;//???????
    double p_steam;//???????
    double t_steam;//???????
    double t_env;//???????
    public double t_wall;//???????
    public int num;//?????
    double d_in;//?????????????
    double d_out;//?????????????
    double d_insulation;//??????????
    public double length;//??γ???

    double m_flow=1;
    
    public double[] T=new double[100];
    public double[] T1=new double[100];
    public double[] T2=new double[100];
    public double[] T3=new double[100];
    public double[] H=new double[100];
    public double[] X=new double[100];
    public double[] Q=new double[100];
    public double[] HI=new double[100];
    public double[] HO=new double[100];
    
    public void init(double p_steam,double t_steam,double t_env,double t_wall,
                     double d_in,double d_out,double d_insulation,double length,int n){
        this.p_steam=p_steam;
        this.t_steam=t_steam;
        this.t_env = t_env;
        this.t_wall=t_wall;
        this.d_in=d_in;
        this.d_out=d_out;
        this.d_insulation=d_insulation;
        this.length=length/n;
        this.num=n;
        H[0]=Steam.stmpth(p_steam, t_steam+273.15);
        for(int i=0;i<n;i++){
            T3[i]=t_env;
            T[i]=t_steam;
            T1[i]=t_steam;
            T2[i]=t_steam;
            Q[i]=0;
            X[i]=1;
        }
    }

    double h_steam(double flow,double h,double p,double t,double t2,double diam,double length){
    	
        double vel_flow=flow*Steam.stmptv(p,t+273.15)*4/(Math.PI*diam*diam);
        double Re=vel_flow*diam/Steam.stmpt_u(p,t+273.15);
        double nu=0,prn=0,u,uw;
        prn=Steam.stmpt_prn(p,t+273.15);
        
        if(t>Steam.stmpt(p)){
            if(Re<2300){
                nu=3.66+0.0688*(diam/length)*Re*prn/(1+0.04*Math.pow(diam/length*Re*prn,2/3));
            }else{
            	u=Steam.stmpt_eta(p, t+273.15);
            	uw=Steam.stmpt_eta(p,t2+273.15);
                nu=0.027*Math.pow(Re,0.8)*Math.pow(prn,1/3)*Math.pow(u/uw, 0.14);
            }
            return nu*Steam.stmpt_ramd(p,t+273.15)/diam*Math.PI*diam*length;
        }else{
            double rho_l=1/Steam.stmp_l_v(p);
            double rho_g=1/Steam.stmp_g_v(p);
            double x=Steam.stmphx(p,h);
            double l_eta=Steam.stmp_l_eta(p);
            Re=flow*diam/l_eta/(Math.PI*diam*diam/4);
            prn=Steam.stmp_l_prn(p);
            nu=0.021*Math.pow(Re,0.8)*Math.pow(prn,0.43)*Math.sqrt(1+x*(rho_l/rho_g-1));
            
            return nu*Steam.stmp_l_ramd(p)/diam*Math.PI*diam*length;
        }

    }
    double h_wall_insulation(double d_in,double d_out,double length,double k){
        return 2*Math.PI*k*length/Math.log(d_out/d_in);
    }
    double h_air(double t_baowen,double t_air,double diam,double length){
        double tm=(t_baowen+t_air)/2;
        double bta=1/(tm+273.16);
        double v_air=(0.1086*(tm+273.16)-17.344)*0.000001;
        double k_air=(0.0076*(tm+273.16)+0.3393)*0.01;
        double pr_air=1.2536*Math.pow(tm+273.16,-0.1001);
        double GrPr= 9.8 * bta * (t_baowen - t_air) * Math.pow(diam,3) /Math.pow(v_air,2)*pr_air;
        double nu;
        if(GrPr<=1e9){
            nu=0.53*Math.pow(GrPr,0.25);
        }else{
            nu=0.13*Math.pow(GrPr,1/3);
        }
        return k_air*nu/diam*Math.PI*diam*length;
    }

    public double calc(double tw){
        double fx,dfx;
        double flow=m_flow*1.1;
        double flow1=m_flow;
        double fx1=calc_single(flow1);
        do{
            fx=calc_single(flow);
            dfx=(fx-fx1)/(flow-flow1);
            fx1=fx;
            flow1=flow;
            flow=flow-(fx-tw)/dfx;
            if(flow<0){
            	flow=flow1*0.1;
            }
            if(flow/flow1>100){
            	flow=flow1*2;
            }
            Log.d("flow=",Double.toString(flow));
            Log.d("TW=",Double.toString(T2[num-1]));
        }while(Math.abs(T2[num-1]/tw-1)>0.001);
        return flow;
    }

    public double iter_control(int i,double flow){

        double h_steam=h_steam(flow,H[i],p_steam,T[i],T2[i],d_in,length);
        double h_insulation=h_wall_insulation(d_out,d_insulation,length,k_insulation);
        HI[i]=h_steam/(Math.PI*d_in*length);
        double q,T3_tmp,h_air,h_wall,k_steel;

        do{
            T3_tmp=T3[i];
            k_steel = -0.0264 * T2[i] + 51.469;
            h_wall=h_wall_insulation(d_in,d_out,length,k_steel);
            h_air=h_air(T[3],t_env,d_insulation,length);
            HO[i]=h_air/(Math.PI*d_insulation*length);
            q=(T[i]-t_env)/(1/h_steam+1/h_wall+1/h_insulation+1/h_air);
            T1[i]=T[i]-q/h_steam;
            T2[i]=T1[i]-q/h_wall;
            T3[i]=T2[i]-q/h_insulation;
        }while(Math.abs(T3_tmp-T3[i])>0.1);
        
        return q;
    }
    public double calc_single(double flow){
        
        for(int i=0;i<num;i++){
            Q[i]=iter_control(i,flow);
            H[i+1]=H[i]-Q[i]/1000/flow;
            X[i+1]=Steam.stmphx(p_steam, H[i+1]);
            double tx=Steam.stmpht(p_steam,H[i+1])-273.15;
            T[i+1]=tx;
        }
        return T2[num-1];
    }

}
