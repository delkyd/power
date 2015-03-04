package com.example.winsteam.converter;

public class KVisConverter implements Converter {

	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("m2/s"))
			return data;
		if(unit.equals("ft2/s"))
			return data*10.764;
		if(unit.equals("in2/s"))
			return data*1550;
		if(unit.equals("St"))
			return data*10000;
		if(unit.equals("cSt"))
			return data*1000000;
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("m2/s"))
			return data;
		if(unit.equals("ft2/s"))
			return data/10.764;
		if(unit.equals("in2/s"))
			return data/1550;
		if(unit.equals("St"))
			return data/10000;
		if(unit.equals("cSt"))
			return data/1000000;
		return 0;
	}

}
