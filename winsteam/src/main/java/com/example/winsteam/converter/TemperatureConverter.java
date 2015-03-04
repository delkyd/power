package com.example.winsteam.converter;


public class TemperatureConverter implements Converter {
	
	@Override
	public double convertTo(double data,String unit) {
		// �ӿ������±�ת��Ϊ�����±�
		if(unit.equals("K"))
			return data;
		if(unit.equals("��"))
			return data-273.16;
		if(unit.equals("�H"))
			return (data-273.16)*1.8+32;
		if(unit.equals("R"))
			return data*1.8;
		return -1;
	}
	//�������±�ת��Ϊ�������±�
	@Override
	public double convertFrom(double data,String unit) {
		// TODO Auto-generated method stub
		
		if(unit.equals("K"))
			return data;
		if(unit.equals("��"))
			return data+273.16;
		if(unit.equals("�H"))
			return (data-32)/1.8+273.16;
		if(unit.equals("R"))
			return data/1.8;
		return -1;
	}
}
