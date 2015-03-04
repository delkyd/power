package com.example.valve;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.R.color;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText et_p,et_t,et_l,et_tw,et_d0,et_d1,et_d2,et_k,et_num,et_t0;
	Button btn_calc;
	TextView tv_flow;
	Leakage leakage;
	LinearLayout graph1,graph2,graph3,graph4;
	GraphicalView view1;
	int width;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		width=this.getWindowManager().getDefaultDisplay().getWidth();
		findview();
		et_num.setText("10");
		btn_calc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(et_p.getText().toString().isEmpty()) return;
				if(et_t.getText().toString().isEmpty()) return;
				if(et_l.getText().toString().isEmpty()) return;
				if(et_tw.getText().toString().isEmpty()) return;
				if(et_d0.getText().toString().isEmpty()) return;
				if(et_d1.getText().toString().isEmpty()) return;
				if(et_d2.getText().toString().isEmpty()) return;
				if(et_k.getText().toString().isEmpty()){
					et_k.setText("0.116");
				}
				if(et_num.getText().toString().isEmpty()) return;
				double p,t,t0,l,tw,d0,d1,d2,k,flow;int num;
				p=Double.parseDouble(et_p.getText().toString());
				t=Double.parseDouble(et_t.getText().toString());
				l=Double.parseDouble(et_l.getText().toString());
				tw=Double.parseDouble(et_tw.getText().toString());
				d0=Double.parseDouble(et_d0.getText().toString());
				d1=Double.parseDouble(et_d1.getText().toString());
				d2=Double.parseDouble(et_d2.getText().toString());
				k=Double.parseDouble(et_k.getText().toString());
				t0=Double.parseDouble(et_t0.getText().toString());
				num=Integer.parseInt(et_num.getText().toString());
				leakage=new Leakage();
				leakage.init(p*10, t, t0, tw, d0/1000, d1/1000, d2/1000, l, num);
//				leakage.init(160,538,30,100,0.06,0.07,0.31,5,10);*/
				double flow1=leakage.calc(tw);
				String s="";
				s=s.format("泄漏流量=%f kg/h", flow1*3600);
				tv_flow.setText(s);
				
				android.view.ViewGroup.LayoutParams lp =graph1.getLayoutParams(); 
				lp.height=width*2/3;
				graph1.removeAllViews();
				graph1.addView(getTemperatureView(leakage), new LayoutParams(LayoutParams.MATCH_PARENT,  
				         LayoutParams.MATCH_PARENT));  
				
				lp =graph2.getLayoutParams(); 
				lp.height=width*2/3;
				graph2.removeAllViews();
				graph2.addView(getHeatCoefView(leakage), new LayoutParams(LayoutParams.MATCH_PARENT,  
				         LayoutParams.MATCH_PARENT));  
				
				lp =graph3.getLayoutParams(); 
				lp.height=width*2/3;
				graph3.removeAllViews();
				graph3.addView(getQualityView(leakage), new LayoutParams(LayoutParams.MATCH_PARENT,  
				         LayoutParams.MATCH_PARENT));  
				
				lp =graph4.getLayoutParams(); 
				lp.height=width*2/3;
				graph4.removeAllViews();
				graph4.addView(getHeatView(leakage), new LayoutParams(LayoutParams.MATCH_PARENT,  
				         LayoutParams.MATCH_PARENT));  
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	void findview(){
		et_p=(EditText)findViewById(R.id.et_p);
		et_t=(EditText)findViewById(R.id.et_t);
		et_l=(EditText)findViewById(R.id.et_l);
		et_tw=(EditText)findViewById(R.id.et_tw);
		et_d0=(EditText)findViewById(R.id.et_d0);
		et_d1=(EditText)findViewById(R.id.et_d1);
		et_d2=(EditText)findViewById(R.id.et_d2);
		et_k=(EditText)findViewById(R.id.et_k);
		et_t0=(EditText)findViewById(R.id.et_t0);
		et_num=(EditText)findViewById(R.id.et_num);
		btn_calc=(Button)findViewById(R.id.btn_calc);
		tv_flow=(TextView)findViewById(R.id.tv_flow);
		graph1=(LinearLayout)findViewById(R.id.graph1);
		graph2=(LinearLayout)findViewById(R.id.graph2);
		graph3=(LinearLayout)findViewById(R.id.graph3);
		graph4=(LinearLayout)findViewById(R.id.graph4);
		
	}
	public GraphicalView getTemperatureView(Leakage leakage) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = leakage.num;
	    XYSeries series = new XYSeries("蒸汽温度");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.T[k]);
	    }
	    dataset.addSeries(series);
	   
	    series = new XYSeries("管壁温度");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.T2[k]);
	    }
	    dataset.addSeries(series);
	    
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setAxisTitleTextSize(16);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0, Color.BLACK);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsColor(color.black);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setPointSize(5f);
	    renderer.setMargins(new int[] {20, 30, 15, 0});
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.SQUARE);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    
	    r = new XYSeriesRenderer();
	    r.setPointStyle(PointStyle.CIRCLE);
	    r.setColor(Color.GREEN);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setChartTitle("蒸汽及金属温度变化图");
	    renderer.setXTitle("管道长度(m)");
	    renderer.setYTitle("温度(度）");
	    renderer.setApplyBackgroundColor(true);
	    renderer.setMarginsColor(color.background_light);
	    renderer.setBackgroundColor(color.background_light);
	    
	    renderer.setXAxisMin(0);
	    renderer.setXAxisMax(leakage.num*leakage.length);
	    renderer.setYAxisMin(leakage.t_wall-50);
	    renderer.setYAxisMax(leakage.t_steam+50);
	    GraphicalView view=ChartFactory.getLineChartView(getApplicationContext(), dataset, renderer);
	    return view;
	  }
	public GraphicalView getHeatCoefView(Leakage leakage) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = leakage.num;
	    XYSeries series = new XYSeries("管内换热系数");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.HI[k]);
	    }
	    double s1max=series.getMaxY();
	    double s1min=series.getMinY();
	    dataset.addSeries(series);
	   
	    series = new XYSeries("管外换热系数");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.HO[k]);
	    }
	    double s2max=series.getMaxY();
	    double s2min=series.getMinY();
	    
	    dataset.addSeries(series);
	    
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setAxisTitleTextSize(16);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0, Color.BLACK);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setPointSize(5f);
	    renderer.setMargins(new int[] {30, 30, 15, 20});
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.SQUARE);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    
	    r = new XYSeriesRenderer();
	    r.setPointStyle(PointStyle.CIRCLE);
	    r.setColor(Color.GREEN);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setChartTitle("传热系数变化图");
	    renderer.setXTitle("管道长度(m)");
	    renderer.setYTitle("传热系数(W/m2*c）");
	    renderer.setApplyBackgroundColor(true);
	    renderer.setMarginsColor(color.background_light);
	    renderer.setBackgroundColor(color.background_light);
	    
	    renderer.setXAxisMin(0);
	    renderer.setXAxisMax(leakage.num*leakage.length);
	    if(s2min<s1min)
	    	renderer.setYAxisMin(s2min-1);
	    else
	    	renderer.setYAxisMin(s1min-1);
	    if(s2max<s1max)
	    	renderer.setYAxisMax(s1max+1);
	    else
	    	renderer.setYAxisMax(s2max+1);
	    GraphicalView view=ChartFactory.getLineChartView(getApplicationContext(), dataset, renderer);
	    return view;
	  }
	public GraphicalView getQualityView(Leakage leakage) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = leakage.num;
	    XYSeries series = new XYSeries("蒸汽湿度");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.X[k]);
	    }
	  
	    dataset.addSeries(series);

	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setAxisTitleTextSize(16);
	    renderer.setAxesColor(Color.BLACK);
	    
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setPointSize(5f);
	    renderer.setMargins(new int[] {20, 30, 15, 20});
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.SQUARE);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0, Color.BLACK);
	    
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setChartTitle("蒸汽湿度变化图");
	    renderer.setXTitle("管道长度(m)");
	    renderer.setYTitle("蒸汽湿度");
	    renderer.setApplyBackgroundColor(true);
	    renderer.setMarginsColor(color.background_light);
	    renderer.setBackgroundColor(color.background_light);
	    
	    renderer.setXAxisMin(0);
	    renderer.setXAxisMax(leakage.num*leakage.length);
	    renderer.setYAxisMin(-0.1);
	    renderer.setYAxisMax(1.1);
	    
	    GraphicalView view=ChartFactory.getLineChartView(getApplicationContext(), dataset, renderer);
	    return view;
	  }
	
	public GraphicalView getHeatView(Leakage leakage) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = leakage.num;
	    XYSeries series = new XYSeries("管道换热量");
	    for (int k = 0; k < nr; k++) {
	        series.add(k*leakage.length,leakage.Q[k]);
	    }
	    double s1max=series.getMaxY();
	    double s1min=series.getMinY();
	    dataset.addSeries(series);

	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setAxisTitleTextSize(16);
	    renderer.setAxesColor(Color.BLACK);
	    
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setPointSize(5f);
	    renderer.setMargins(new int[] {20, 30, 15, 20});
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.SQUARE);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
 	    
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0, Color.BLACK);
	    
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setChartTitle("管道换热量图");
	    renderer.setXTitle("管道长度(m)");
	    renderer.setYTitle("换热量（J)");
	    renderer.setApplyBackgroundColor(true);
	    renderer.setMarginsColor(color.background_light);
	    renderer.setBackgroundColor(color.background_light);
	    
	    renderer.setXAxisMin(0);
	    renderer.setXAxisMax(leakage.num*leakage.length);
	    renderer.setYAxisMin(s1min-1);
	    renderer.setYAxisMax(s1max+1);
	    
	    GraphicalView view=ChartFactory.getLineChartView(getApplicationContext(), dataset, renderer);
	    return view;
	  }
}
