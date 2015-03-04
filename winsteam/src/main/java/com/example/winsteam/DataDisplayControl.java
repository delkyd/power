package com.example.winsteam;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.winsteam.converter.Converter;
import com.example.winsteam.converter.DVisConverter;
import com.example.winsteam.converter.EnthalpyConverter;
import com.example.winsteam.converter.EntropyConverter;
import com.example.winsteam.converter.KVisConverter;
import com.example.winsteam.converter.OtherConverter;
import com.example.winsteam.converter.RamdConverter;
import com.example.winsteam.converter.SoundConverter;

public class DataDisplayControl extends LinearLayout {
	private TextView tv_name;
	private TextView tv_symbol;
	private TextView tv_value;
	private Spinner  sp_unit;
	private Converter converter;
	private String myType;
	private String mUnit;
	private int mUnit_id;
	private double mValue=-1;
	
	public DataDisplayControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.data_display,this,true);
		tv_name=(TextView)this.findViewById(R.id.tv_name);
		tv_symbol=(TextView)findViewById(R.id.tv_sym);
		tv_value=(TextView)findViewById(R.id.tv_value);
		sp_unit=(Spinner)findViewById(R.id.sp_unit);

		//�Զ�������		
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Custom);

        final int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
            case R.styleable.Custom_type:
                //��ȡmyText����ֵ
                myType = a.getString(attr);
                String[] mItems=null;
                //�õ�ֵ��Ϳ��԰�����Ҫ��ʹ���ˣ��������Ǹ�Button���ð�ť��ʾ����
               if(myType.equals("ens")){
            	   converter=new EntropyConverter();
            	   mItems=context.getResources().getStringArray(R.array.ens);
               }
               if(myType.equals("enh")){
            	   converter=new EnthalpyConverter();
            	   mItems=context.getResources().getStringArray(R.array.enh);
               }
               if(myType.equals("ramd")){
            	   converter=new RamdConverter();
            	   mItems=context.getResources().getStringArray(R.array.ramd);
               }
               if(myType.equals("speed")){
            	   converter=new SoundConverter();
            	   mItems=context.getResources().getStringArray(R.array.speed);
               }
               if(myType.equals("other")){
            	   converter=new OtherConverter();
            	   mItems=context.getResources().getStringArray(R.array.other);
               }
               if(myType.equals("dvis")){
            	   converter=new DVisConverter();
            	   mItems=context.getResources().getStringArray(R.array.dvis);
               }
               if(myType.equals("kvis")){
            	   converter=new KVisConverter();
            	   mItems=context.getResources().getStringArray(R.array.kvis);
               }
               if(mItems!=null){
            	   ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
       		   //�� Adapter���ؼ�
            	   sp_unit.setAdapter(_Adapter);
               }
                break;
            case R.styleable.Custom_text:
            	tv_name.setText(a.getString(attr));
            	break;
            case R.styleable.Custom_symbol:
            	tv_symbol.setText(a.getString(attr));
            	break;
             }
        }
        a.recycle();
        if(this.loadUnitPreference()>0)
        	sp_unit.setSelection(loadUnitPreference());
        else
        	sp_unit.setSelection(0);
        mUnit=(String)sp_unit.getSelectedItem();
        mUnit_id=sp_unit.getSelectedItemPosition();
     // TODO Auto-generated constructor stub
     		sp_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
     		{

     			@Override
     			public void onItemSelected(AdapterView<?> parent, View view,
     					int position, long id) {
     				// TODO Auto-generated method stub
     				mUnit=parent.getItemAtPosition(position).toString();
     				mUnit_id=position;
     				
     				if(mValue<0)
     					return;
       				unitChange(mUnit);
     			}

     			@Override
     			public void onNothingSelected(AdapterView<?> parent) {
     				// TODO Auto-generated method stub
     				
     			}
     		});
     		
		
	}
	public void setParamValue(double value){
		mValue=value;
		unitChange(mUnit);
	}
	
	public void unitChange(String unit){
		double temp=converter.convertTo(mValue, unit);
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.####");
		java.text.DecimalFormat df1=new java.text.DecimalFormat("#.###E0");
		java.text.DecimalFormat df2=new java.text.DecimalFormat("#.##");
		String s;
		if(temp>0 && temp<0.01){
			s=df1.format(temp);
		}else if(temp>0.01 && temp<100000){
			s=df.format(temp);
		}else if(temp>1000)
			s=df2.format(temp);
		else
			s=getResources().getString(R.string.error);
		tv_value.setText(s);
	}
	public void saveUnitPreference(){
		try{
			SharedPreferences settings=getContext().getSharedPreferences("setting", 0);
			SharedPreferences.Editor editor=settings.edit();
			editor.putInt(Integer.toString(getId()), mUnit_id);
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
			e.printStackTrace();
		}
		return -1;
	}
}
