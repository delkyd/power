package com.example.winsteam.converter;


public class VolumeConverter implements Converter {

	   
    

	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("m3/kg"))
			return data;
		if(unit.equals("kg/m3"))
			return 1/data;
		if(unit.equals("in3/lbm"))
			return data*27679.90471;
		if(unit.equals("gal(US)/lbm"))
			return data*119.8264273;
		if(unit.equals("gal(UK)/lbm"))
			return data*99.77637266;
		if(unit.equals("ft3/lbm"))
			return data*16.01846337;
		
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("m3/kg"))
			return data;
		if(unit.equals("kg/m3"))
			return 1/data;
		if(unit.equals("in3/lbm"))
			return data/27679.90471;
		if(unit.equals("gal(US)/lbm"))
			return data/119.8264273;
		if(unit.equals("gal(UK)/lbm"))
			return data/99.77637266;
		if(unit.equals("ft3/lbm"))
			return data/16.01846337;
		return 0;
	}
}
