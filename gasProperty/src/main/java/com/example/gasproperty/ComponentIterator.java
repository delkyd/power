package com.example.gasproperty;

public class ComponentIterator {
	
	final double Ma=28.9644e-3,Mc=12.01115e-3,Mh=1.00797e-3,
				 Mo=15.994e-3,Mn=14.0067e-3,Mw=18.01534e-3;
	final double rN2=0.780881e0,rCO2=0.3e-3,rAr=0.9324e-2,rO2=0.209495e0;
	double Ac,Ah,Ao,An;
	
	public void setCarbonAtomNumber(double anc){this.Ac=anc;}
	public void setHydrogenAtomNumber(double anh){this.Ah=anh;}
	public void setOxygenAtomNumber(double ano){this.Ao=ano;}
	public void setNitrogenAtomNumber(double ann){this.An=ann;}
	
	double pN2,pO,pO2,pNO,pCO2,pH2O,pH2,pOH,pAr,pHO,pN,pH,pCO;
	double Nar,Nn,Nc,Nh,No,As;
	double B,b;
	double k1,k2,k3,k4,k5,k6,k7;
	double p,t,Mf;
	
	double Mg;//燃气摩尔质量，解方程时获得
	
	public void calc(){
		double E=1+f+w;
		Mf=Ac*Mc+An*Mn+Ah*Mh+Ao*Mo;
		No=1/E*(2*(rO2+rCO2)/Ma+Ao*f/Mf+w/Mw);
		Nh=1/E*(Ah*f/Mf+2*w/Mw);
		Nc=1/E*(rCO2/Ma+Ac*f/Mf);
		Nn=1/E*(2*rN2/Ma+An*f/Mf);
		Nar=1/E*rAr/Ma;
	}
	
	
	double w,f;//w��ˮ������Ըɿ���������f��ȼ����Կ�������
	public void setBurnMass(double p,double t,double air,double water,double fuel){
		w=water/air;
		f=fuel/air;
		this.p=p;
		this.t=t;
	}
	
	public void setComponentMass(double p,double Nar,double Nn,double Nh,double No,double As){
		this.p=p;this.Nar=Nar;this.Nn=Nn;this.Nh=Nh;this.No=No;this.As=As;
	}
	public void Iterator(){
		
		k1 = GasProperty.K1(t);
		k2 = GasProperty.K2(t);
		k3 = GasProperty.K3(t);
		k4 = GasProperty.K4(t);
		k5 = GasProperty.K5(t);
		k6 = GasProperty.K6(t);
		k7 = GasProperty.K7(t);
		
		double tN2, tO2;
		do {
			pN2 = 0.8;
			pO2 = 0.2;
			
			tN2 = pN2;
			tO2 = pO2;
			pN = Math.sqrt(k7 * pN2);
			pO = Math.sqrt(k6 * pO2);
			pNO = Math.sqrt(k4 * pN2 * pO2);
			As = (2 * pN2 + pNO + pN) / Nn;
			pAr = As * Nar;
			pCO = As * Nc / (1 + Math.sqrt(pO2) / k1);
			pCO2 = As * Nc - pCO;
			b = Math.sqrt(pCO / k2 / pCO2);
			B = k3 / b + b * Math.sqrt(k5);
			pH2O = ((-B + Math.sqrt(B * B + 8 * As * Nh * (1 + b * b))) / 4 / (1 + b
					* b));
			pH2 = b * b * pH2O;
			pOH = k3 * Math.sqrt(k2 / k1) * Math.pow(pO2, 1 / 4)
					* Math.sqrt(pH2O);
			pH = b * Math.sqrt(k5 * pH2O);

			pO2 = (As * No - pH2O - pCO - pOH - pNO - pO) / 2 - pCO2;
			pN2 = p - pCO2 - pAr - pH2O - pO2 - pCO - pH2 - pOH - pNO - pH - pO
					- pN;
		} while (Math.abs(tN2 - pN2) / pN2 < 1e-9
				&& Math.abs(tO2 - pO2) / pO2 < 1e-9);

	}
	public double getN2(){return pN2;}
	public double getCO2(){return pCO2;}
	public double getO2(){return pO2;}
	public double getH(){return pH;}
	public double getO(){return pO;}
	public double getH2(){return pH2;}
	public double getN(){return pN;}
	public double getOH(){return pOH;}
	public double getAr(){return pAr;}
	public double getCO(){return pCO;}
	public double getNO(){return pNO;}
	public double getH2O(){return pH2O;}
	
	public double getEntraphy(double p,double t){
		double hN2=pN2/p*GasProperty.gas_h(t, "N2");
		double hCO2=pCO2/p*GasProperty.gas_h(t, "CO2");
		double hO2=pO2/p*GasProperty.gas_h(t, "O2");;
		double hH=pH/p*GasProperty.gas_h(t, "H");
		double hO=pO/p*GasProperty.gas_h(t, "O");
		double hH2=pH2/p*GasProperty.gas_h(t, "H2");
		double hN=pN/p*GasProperty.gas_h(t, "N");
		double hOH=pOH/p*GasProperty.gas_h(t, "OH");
		double hAr=pAr/p*GasProperty.gas_h(t, "Ar");
		double hCO=pCO/p*GasProperty.gas_h(t, "CO");
		double hNO=pNO/p*GasProperty.gas_h(t, "NO");
		double hH2O=pH2O/p*GasProperty.gas_h(t, "H2O");
		
		return (hN2+hCO2+hO2+hH+hO+hH2+hN+hOH+hAr+hCO+hNO+hH2O)/Mg;
		
	}
}
