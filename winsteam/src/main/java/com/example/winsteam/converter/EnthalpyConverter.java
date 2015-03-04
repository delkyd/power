package com.example.winsteam.converter;

public class EnthalpyConverter implements Converter {

	
	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("kJ/kg"))
			return data;
		if(unit.equals("J/kg"))
			return data*1000;
		if(unit.equals("kcal/kg"))
			return data/4.1868;
		if(unit.equals("cal/kg"))
			return data*1000/4.1868;
		if(unit.equals("BTU/lb"))
			return data/4.1868;
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("kJ/kg"))
			return data;
		if(unit.equals("J/kg"))
			return data/1000;
		if(unit.equals("kcal/kg"))
			return data*4.1868;
		if(unit.equals("cal/kg"))
			return data/1000*4.1868;
		if(unit.equals("BTU/lb"))
			return data*4.1868;
		return 0;
	}

}
