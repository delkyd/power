package com.example.winsteam.converter;

public class LengthConverter implements Converter{
	public double convertTo(double data,String unit){
		if(unit.equals("m"))
			return data;
		if(unit.equals("mm"))
			return data/1000;
		if(unit.equals("inch"))
			return data*0.0254;
		if(unit.equals("ft"))
			return data*0.3048;
		return -1;
	}
	public double convertFrom(double data,String unit){
		if(unit.equals("m"))
			return data;
		if(unit.equals("mm"))
			return data*1000;
		if(unit.equals("inch"))
			return data/0.0254;
		if(unit.equals("ft"))
			return data/0.3048;
		return -1;
	}
}
