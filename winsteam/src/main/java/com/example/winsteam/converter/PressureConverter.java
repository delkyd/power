package com.example.winsteam.converter;

public class PressureConverter implements Converter {
	
	
	@Override
	public double convertTo(double data,String unit) {
		// TODO Auto-generated method stub
		
		if(unit.equals("bar(a)"))
			return data;
		if(unit.equals("bar(g)"))
			return data-1.01325;
		if(unit.equals("mbar(a)"))
			return data*1000;
		if(unit.equals("MPa(a)"))
			return data*0.1;
		if(unit.equals("kPa(a)"))
			return data*100;
		if(unit.equals("psi(a)"))
			return data*14.5037737730209;
		if(unit.equals("psi(g)"))
			return data*14.5037737730209-14.6959487755135;
		if(unit.equals("in Hg(a)"))
			return data*29.5299833010101;
		if(unit.equals("atm(a)"))
			return data*.986923266716013;
		if(unit.equals("m H2O(a)"))
			return 10.19744*data;
		if(unit.equals("cm H2O(a)"))
			return 1019.744*data;
		if(unit.equals("in H2O(a)"))
			return 401.4742*data;
		if(unit.equals("ft H2O(a)"))
			return 12*401.4742*data;
		if(unit.equals("cm Hg(a)"))
			return 75.00638*data;
		if(unit.equals("mm Hg(a)"))
			return 750.0638*data;
		if(unit.equals("kgf/cm2(a)"))
			return 1.019716*data;
		if(unit.equals("kgf/m2(a)"))
			return 10197.16*data;
		
		return 0;
	}

	@Override
	public double convertFrom(double data,String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("bar(a)"))
			return data;
		if(unit.equals("bar(g)"))
			return data+1.01325;
		if(unit.equals("mbar(a)"))
			return data/1000;
		if(unit.equals("MPa(a)"))
			return data/0.1;
		if(unit.equals("kPa(a)"))
			return data/100;
		if(unit.equals("psi(a)"))
			return data/14.5037737730209;
		if(unit.equals("psi(g)"))
			return (data+14.6959487755135)/14.5037737730209;
		if(unit.equals("in Hg(a)"))
			return data/29.5299833010101;
		if(unit.equals("atm(a)"))
			return data/.986923266716013;
		if(unit.equals("m H2O(a)"))
			return data/10.19744;
		if(unit.equals("cm H2O(a)"))
			return data/1019.744;
		if(unit.equals("in H2O(a)"))
			return data/401.4742;
		if(unit.equals("ft H2O(a)"))
			return data/12*401.4742;
		if(unit.equals("cm Hg(a)"))
			return data/75.00638;
		if(unit.equals("mm Hg(a)"))
			return data/750.0638;
		if(unit.equals("kg/cm2(a)"))
			return data/1.019716;
		if(unit.equals("kg/m2(a)"))
			return data/10197.16;
		return 0;
	}
	
	

}
