package com.example.winsteam.converter;

public class DVisConverter implements Converter {

	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("kg/m/s"))
			return data;
		if(unit.equals("Pa-s"))
			return data;
		if(unit.equals("P"))
			return data*10;
		if(unit.equals("kgf-s/m2"))
			return data*0.10197100;
		if(unit.equals("lbf-s/ft2"))
			return data*0.02088540;
		if(unit.equals("lbf-s/in2"))
			return data*0.00014501;
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("kg/m/s"))
			return data;
		if(unit.equals("Pa-s"))
			return data;
		if(unit.equals("P"))
			return data/10;
		if(unit.equals("kgf-s/m2"))
			return data/0.10197100;
		if(unit.equals("lbf-s/ft2"))
			return data/0.02088540;
		if(unit.equals("lbf-s/in2"))
			return data/0.00014501;
		return 0;
	}

}
