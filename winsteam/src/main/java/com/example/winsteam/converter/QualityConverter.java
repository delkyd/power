package com.example.winsteam.converter;

public class QualityConverter implements Converter {
	
	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("-")){
			if(data>1)
				return 1;
			if(data<0)
				return 0;
			return data;
		}
		if(unit.equals("%")){
			if(data*100>100)
				return 100;
			if(data*100<0)
				return 0;
			return data*100;
		}
		return -1;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("-")){
			if(data>1)
				return 1;
			if(data<0)
				return 0;
			return data;
		}
		if(unit.equals("%")){
			if(data/100>100)
				return 100;
			if(data/100<0)
				return 0;
			return data/100;
		}
		return -1;
	}

}
