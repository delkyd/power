package com.example.gasproperty;

public class GasProperty {
	final static double R=8.314298;
	final static double[] A_N2={-0.87663919e4, 0.30406073e5,-0.56917529e4, 0.88690838e4,
						 -0.32712282e4,-0.94275880e3, 0.12413666e4,-0.45758467e3,
						  0.80837813e2,-0.64299353e1, 0.13671117e0};
	final static double S_N2=0.23063904e3;
	final static double[] A_CO2={-0.40240085e6, 0.21098976e5, 0.33526894e5,-0.14994685e5,
			               0.11184872e5, 0.29595750e4,-0.19308771e4, 0.60419564e3,
			              -0.10527234e3, 0.96189457e1,-0.34920199e0};
	final static double S_CO2=0.22118364e3;
	final static double[] A_Ar={-0.61972701e4, 0.20785746e5, 0,0,0,0,0,0,0,0,0};
	final static double S_Ar=0.17989337e3;
	final static double[] A_H2O={-0.25179888e6, 0.33930851e5,-0.43630281e4, 0.10594936e5,
			              -0.54952291e4, 0.14949330e4,-0.22772255e3, 0.17874978e2,
			              -0.53236046e0, 0,0};
	final static double S_H2O=0.23113968e3;
	final static double[] A_O2={-0.87177436e4, 0.30016147e5,-0.74401940e4, 0.20698855e5,
			           -0.18287018e5, 0.76500639e4,-0.92458240e3,-0.48681237e3,
			            0.22875662e3,-0.38490629e2, 0.24142372e1};
	final static double S_O2=0.24357728e3;
	final static double[] A_CO={-0.11930060e6, 0.30585726e5,-0.71499180e4, 0.12942867e5,
			             -0.76435784e4, 0.15073372e4, 0.49405297e3,-0.35416388e3,
			              0.83707563e2,-0.90288522e1, 0.35899694e0};
	final static double S_CO=0.23734904e3;
	final static double[] A_H2={-0.75841600e4, 0.20461002e5, 0.25749527e5,-0.38485897e5,
			              0.31446095e5,-0.13035107e5, 0.16907447e4, 0.76209424e3,
			             -0.37515625e3, 0.64361741e2,-0.40977785e1};
	final static double S_H2=0.14415785e3;
	final static double[] A_OH={ 0.30047277e5, 0.33720683e5,-0.96915966e4, 0.95690442e4,
			             -0.37267379e4, 0.12752147e3, 0.60391424e3,-0.32891310e3,
			              0.88225733e2,-0.12493344e2, 0.74029110e0};
	final static double S_OH=0.22903318e3;
	final static double[] A_NO={ 0.80916220e5, 0.34635248e5,-0.18090592e5, 0.30581507e5,
			             -0.22713965e5, 0.82187104e4,-0.60014795e3,-0.65650733e3,
			              0.26273846e3,-0.41582078e2, 0.25091902e1};
	final static double S_NO=0.25997701e3;
	final static double[] A_H={ 0.21178913e6, 0.20785746e5, 0,0,0,0,0,0,0,0,0};
	final static double S_H=0.13976456e3;
	final static double[] A_O={ 0.24222620e6, 0.25395503e5,-0.96193897e4, 0.11575756e5,
			            -0.83958182e4, 0.37190299e4,-0.98285855e3, 0.14223141e3,
			            -0.86622783e1, 0,           0};
	final static double S_O=0.19612582e3;
	final static double[] A_N={ 0.46645002e6, 0.20771610e5, 0.33719620e2,-0.35888590e2,
			           0.22813004e2,-0.90411346e1, 0.18428743e1,-0.10403263e0,
			           0,0,0};
	final static double S_N=0.17831521e3;
	
	
	public static double gas_h(double T,String gas){
		
		if(gas.equals("N2"))
			return H(T,A_N2);
		if(gas.equals("CO2"))
			return H(T,A_CO2);
		if(gas.equals("Ar"))
			return H(T,A_Ar);
		if(gas.equals("H2O"))
			return H(T,A_H2O);
		if(gas.equals("O2"))
			return H(T,A_O2);
		if(gas.equals("CO"))
			return H(T,A_CO);
		if(gas.equals("H2"))
			return H(T,A_H2);
		if(gas.equals("OH"))
			return H(T,A_OH);
		if(gas.equals("NO"))
			return H(T,A_NO);
		if(gas.equals("H"))
			return H(T,A_H);
		if(gas.equals("O"))
			return H(T,A_O);
		if(gas.equals("N"))
			return H(T,A_N);
		return -1;
		
		
	}
	public static double gas_cp(double T,String gas){
		if(gas.equals("N2"))
			return Cp(T,A_N2);
		if(gas.equals("CO2"))
			return Cp(T,A_CO2);
		if(gas.equals("Ar"))
			return Cp(T,A_Ar);
		if(gas.equals("H2O"))
			return Cp(T,A_H2O);
		if(gas.equals("O2"))
			return Cp(T,A_O2);
		if(gas.equals("CO"))
			return Cp(T,A_CO);
		if(gas.equals("H2"))
			return Cp(T,A_H2);
		if(gas.equals("OH"))
			return Cp(T,A_OH);
		if(gas.equals("NO"))
			return Cp(T,A_NO);
		if(gas.equals("H"))
			return Cp(T,A_H);
		if(gas.equals("O"))
			return Cp(T,A_O);
		if(gas.equals("N"))
			return Cp(T,A_N);
		return -1;
	}
	public static double gas_s(double T,String gas){
		if(gas.equals("N2"))
			return S(T,A_N2,S_N2);
		if(gas.equals("CO2"))
			return S(T,A_CO2,S_CO2);
		if(gas.equals("Ar"))
			return S(T,A_Ar,S_Ar);
		if(gas.equals("H2O"))
			return S(T,A_H2O,S_H2O);
		if(gas.equals("O2"))
			return S(T,A_O2,S_O2);
		if(gas.equals("CO"))
			return S(T,A_CO,S_CO);
		if(gas.equals("H2"))
			return S(T,A_H2,S_H2);
		if(gas.equals("OH"))
			return S(T,A_OH,S_OH);
		if(gas.equals("NO"))
			return S(T,A_NO,S_NO);
		if(gas.equals("H"))
			return S(T,A_H,S_H);
		if(gas.equals("O"))
			return S(T,A_O,S_O);
		if(gas.equals("N"))
			return S(T,A_N,S_N);
		return -1;
	}
	public static double K1(double T){
		return (gas_s(T,"CO")+0.5*gas_s(T,"O2")-gas_s(T,"CO2"))/R-(gas_h(T,"CO")+0.5*gas_h(T,"O2")-gas_h(T,"CO2"))/R/T;
	}
	public static double K2(double T){
		return (gas_s(T,"CO")+gas_s(T,"H2O")-gas_s(T,"CO2")-gas_s(T,"H2"))/R-(gas_h(T,"CO")+gas_h(T,"H2O")-gas_h(T,"H2")-gas_h(T,"CO2"))/R/T;
	}
	public static double K3(double T){
		return (gas_s(T,"OH")+0.5*gas_s(T,"H2")-gas_s(T,"H2O"))/R-(gas_h(T,"OH")+0.5*gas_h(T,"H2")-gas_h(T,"H2O"))/R/T;
	}
	public static double K4(double T){
		return (2*gas_s(T,"NO")-gas_s(T,"N2")-gas_s(T,"O2"))/R-(2*gas_h(T,"NO")-gas_h(T,"N2")-gas_h(T,"O2"))/R/T;
	}
	public static double K5(double T){
		return (gas_s(T,"H")+gas_s(T,"H")-gas_s(T,"H2"))/R-(gas_h(T,"H")+gas_h(T,"H")-gas_h(T,"H2"))/R/T;
	}
	public static double K6(double T){
		return (gas_s(T,"O")+gas_s(T,"O")-gas_s(T,"O2"))/R-(gas_h(T,"O")+gas_h(T,"O")-gas_h(T,"O2"))/R/T;
	}
	public static double K7(double T){
		return (gas_s(T,"N")+gas_s(T,"N")-gas_s(T,"N2"))/R-(gas_h(T,"N")+0.5*gas_h(T,"N")-gas_h(T,"N2"))/R/T;
	}
	private static double H(double T,double[] a){
		double t=T/1000;
		double v=0;
		for(int i=0;i<11;i++){
			v+=a[i]*Math.pow(t, i);
		}
		return v;
	}
	private static double Cp(double T,double[] a){
		double t=T/1000;
		double v=0;
		for(int i=1;i<11;i++){
			v+=i*a[i]*Math.pow(t, i-1);
		}
		return v/1000;
	}
	private static double S(double T,double[] a,double s){
		double t=T/1000;
		double v=0;
		for(int i=2;i<11;i++){
			v+=i/(i-1)*a[i]*Math.pow(t, i-1);
		}
		return s+a[1]*Math.log(t)/1000+v/1000;
	}
}
