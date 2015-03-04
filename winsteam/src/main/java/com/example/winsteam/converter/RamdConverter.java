package com.example.winsteam.converter;

public class RamdConverter implements Converter {

	 
	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("W/m/K"))
			return data;
		if(unit.equals("kcal/m/h/k"))
			return data/1.16279;
		if(unit.equals("BTU/ft/h/F"))
			return data/1.7303;
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("W/m/K"))
			return data;
		if(unit.equals("kcal/m/h/k"))
			return data*1.16279;
		if(unit.equals("BTU/ft/h/F"))
			return data*1.7303;
		return 0;
	}

}
