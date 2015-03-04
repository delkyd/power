package com.example.winsteam;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.winsteam.converter.Converter;
import com.example.winsteam.converter.EnthalpyConverter;
import com.example.winsteam.converter.EntropyConverter;
import com.example.winsteam.converter.PressureConverter;
import com.example.winsteam.converter.QualityConverter;
import com.example.winsteam.converter.TemperatureConverter;
import com.example.winsteam.converter.VolumeConverter;

public class DataInputControl extends LinearLayout {
	private TextView tv_param;
	private EditText et_param;
	private Spinner  sp_unit;
	private Converter converter;
	private String myType;
	private String unit;
	private int unit_id;
	private double mValue=-1;
	
	public DataInputControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.data_input,this,true);
		tv_param=(TextView)findViewById(R.id.tv_input);
		et_param=(EditText)findViewById(R.id.et_input);
		sp_unit=(Spinner)findViewById(R.id.sp_input);
	
			
		//�Զ�������		
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Custom);

        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
            case R.styleable.Custom_type:
                //��ȡmyText����ֵ
                myType = a.getString(attr);
                String[] mItems=null;
                //�õ�ֵ��Ϳ��԰�����Ҫ��ʹ���ˣ��������Ǹ�Button���ð�ť��ʾ����
               if(myType.equals("press")){
            	   converter=new PressureConverter();
            	   mItems=getResources().getStringArray(R.array.press);
                }
               if(myType.equals("temp")){
            	   converter=new TemperatureConverter();
            	   mItems=context.getResources().getStringArray(R.array.temp);
               }
               if(myType.equals("quality")){
            	   converter=new QualityConverter();
            	   mItems=context.getResources().getStringArray(R.array.quality);
               }
               if(myType.equals("enh")){
            	   converter=new EnthalpyConverter();
            	   mItems=context.getResources().getStringArray(R.array.enh);
               }
               if(myType.equals("ens")){
            	   converter=new EntropyConverter();
            	   mItems=context.getResources().getStringArray(R.array.ens);
               }
               if(myType.equals("vol")){
            	   converter=new VolumeConverter();
            	   mItems=context.getResources().getStringArray(R.array.vol);
               }
               if(mItems!=null){
            	   ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
       		   //�� Adapter���ؼ�
            	   sp_unit.setAdapter(_Adapter);
               }
                break;
            case R.styleable.Custom_text:
            	 tv_param.setText(a.getString(attr));	
            	break;
             }
        }
        a.recycle();
        if(this.loadUnitPreference()>0)
        	sp_unit.setSelection(loadUnitPreference());
        else
        	sp_unit.setSelection(0);
        unit=(String)sp_unit.getSelectedItem();
        unit_id=sp_unit.getSelectedItemPosition();
     // TODO Auto-generated constructor stub
     		sp_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
     		{

     			@Override
     			public void onItemSelected(AdapterView<?> parent, View view,
     					int position, long id) {
     				// TODO Auto-generated method stub
     				
     				String s=et_param.getText().toString();
     				if(s.equals("")){
     					unit=parent.getItemAtPosition(position).toString();
         				unit_id=position;
     					return;
     				}     					
     				else{
     					mValue=converter.convertFrom(Double.parseDouble(s), unit);
     					unit=parent.getItemAtPosition(position).toString();
     					unit_id=position;
     				}
     				unitChange(unit);
     			}

     			@Override
     			public void onNothingSelected(AdapterView<?> parent) {
     				// TODO Auto-generated method stub
     			}
     		});
		
	}
	private void unitChange(String unit){
		double temp=converter.convertTo(getParamValue(), unit);
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.####");
		java.text.DecimalFormat df1=new java.text.DecimalFormat("#.###E0");
		java.text.DecimalFormat df2=new java.text.DecimalFormat("#.##");
		String s;
		if(temp>-100 && temp<0.01){
			s=df1.format(temp);
		}else if(temp>=0.01 && temp<1000){
			s=df.format(temp);
		}else if(temp>=1000)
			s=df2.format(temp);
		else{
			s=getResources().getString(R.string.error);
		}
		et_param.setText(s);
	}
	public void setParamValue(double value){
		mValue=value;
		unitChange(unit);
	}
	public double getParamValue(){
		return mValue;
	}
	public double getParamValueinSI(){
		if(et_param.getText().toString()==null)
			return -1;
		Double temp=Double.parseDouble(et_param.getText().toString());
		mValue=converter.convertFrom(temp, unit);
		return mValue;
	}
	public void saveUnitPreference(){
		try{
			SharedPreferences settings=getContext().getSharedPreferences("setting", 0);
			SharedPreferences.Editor editor=settings.edit();
			editor.putInt(Integer.toString(getId()), unit_id);
			editor.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int loadUnitPreference(){
		try{
			SharedPreferences settings=getContext().getSharedPreferences("setting", 0);
			return settings.getInt(Integer.toString(getId()),0);
		}catch(Exception e){
			
		}
		return -1;
	}
	
}
