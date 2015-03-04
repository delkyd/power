package com.example.valve;

/**
 * Created by Administrator on 13-6-11.
 */
public class Steam {
    public static double stmpt(double p){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.P2T97(p/10,tmp,Range);
        return tmp.d;
    }
    public static double stmptv(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2V(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpth(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2H(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpts(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2S(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmptx(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2X97(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpt_ramd(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2RAMD(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpt_eta(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2Eta(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpt_u(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2U(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpt_prn(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2PRN(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmpt_cp(double p,double t){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PT2CP(p,t,tmp,Range);
        return tmp.d;
    }
    public static double stmp_l_v(double p){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.P2VL(p,tmp,Range);
        return tmp.d;
    }
    public static double stmp_l_eta(double p){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.P2ETAL97(p/10,tmp,Range);
        return tmp.d;
    }
    public static double stmp_g_eta(double p){
    	HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.P2ETAG97(p/10,tmp,Range);
        return tmp.d;
    }
    public static double stmp_g_v(double p){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.P2VG(p,tmp,Range);
        return tmp.d;
    }


    public static double stmp_l_u(double p){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        return IFC97.P2UL(p);

    }
    public static double stmp_l_ramd(double p){
        return IFC97.P2RAMDL(p);
    }
    public static double stmp_l_prn(double p){
        return IFC97.P2RAMDL(p);
    }



    public static double stmpht(double p,double h){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PH2T(p,h,tmp,Range);
        return tmp.d;
    }
    public static double stmphx(double p,double h){
        HDouble tmp=new HDouble();
        HInteger Range=new HInteger();
        IFC97.PH2X(p,h,tmp,Range);
        return tmp.d;
    }
}
