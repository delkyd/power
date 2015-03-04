package com.example.winsteam.converter;

public class SoundConverter implements Converter {

	@Override
	public double convertTo(double data, String s) {
		// TODO Auto-generated method stub
		if(s.equals("m/s"))
			return data;
		if(s.equals("ft/s"))
			return data*3.28083989;
		if(s.equals("km/h"))
			return data*3.6;
		if(s.equals("Mach"))
			return data*0.0029385836;
		if(s.equals("mi/h"))
			return data*2.23693629;
		return 0;
	}

	@Override
	public double convertFrom(double data, String s) {
		if(s.equals("m/s"))
			return data;
		if(s.equals("ft/s"))
			return data/3.28083989;
		if(s.equals("km/h"))
			return data/3.6;
		if(s.equals("Mach"))
			return data/0.0029385836;
		if(s.equals("mi/h"))
			return data/2.23693629;
		return 0;
	}

}
