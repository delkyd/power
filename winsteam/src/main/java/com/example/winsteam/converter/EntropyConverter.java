package com.example.winsteam.converter;

public class EntropyConverter implements Converter {

	
	@Override
	public double convertTo(double data, String unit) {
		// TODO Auto-generated method stub
		if(unit.equals("kJ/kg/K"))
			return data;
		if(unit.equals("BTU/lb/R"))
			return data*0.2388459;
		if(unit.equals("kcal/kg/K"))
			return data*0.2388459;
		if(unit.equals("ft-lbf/lbm/R"))
			return data*185.8625378;
		if(unit.equals("psi/lbm/fg3/R"))
			return data*185.8625352;
		if(unit.equals("kp_m/g/K"))
			return data*0.10197162;
		if(unit.equals("bar-cm3/g/K"))
			return data*10;
		if(unit.equals("hp-hr/lbm/R"))
			return data*9.3869967e-5;
		return 0;
	}

	@Override
	public double convertFrom(double data, String unit) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(unit.equals("kJ/kg/K"))
					return data;
				if(unit.equals("BTU/lb/R"))
					return data/0.2388459;
				if(unit.equals("kcal/kg/K"))
					return data/0.2388459;
				if(unit.equals("ft-lbf/lbm/R"))
					return data/185.8625378;
				if(unit.equals("psi/lbm/fg3/R"))
					return data/185.8625352;
				if(unit.equals("kp_m/g/K"))
					return data/0.10197162;
				if(unit.equals("bar-cm3/g/K"))
					return data/10;
				if(unit.equals("hp-hr/lbm/R"))
					return data/9.3869967e-5;
		return 0;
	}
}
