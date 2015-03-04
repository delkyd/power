package com.hzgzh.naturegasheat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import cn.domob.android.ads.DomobAdView;

public class ResultActivity extends Activity {
    final static String inline1 = "16TLmKmlApEmYNUffBqzLnqk";
    final static String Publish_ID = "56OJzR54uNJFDhtR7p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        LinearLayout l1 = (LinearLayout) findViewById(R.id.linearlayout_result1);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.linearlayout_result2);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.linearlayout_result3);
        DataDisplayControl control = null;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<ValueHolder> vh = bundle.getParcelableArrayList("component");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView t = new TextView(this);
        t.setLayoutParams(lp);
        t.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        t.setText(this.getResources().getString(R.string.test_condition));
        t.setGravity(Gravity.CENTER);
        l1.addView(t);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.comb_temp));
        control.setSymbol("T1=");
        control.setTextValue(bundle.getString("ctemp"));
        control.setUnit("��");
        l1.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.meas_press));
        control.setSymbol("P2=");
        control.setTextValue(bundle.getString("mPress"));
        control.setUnit("kPa");
        l1.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.meas_temp));
        control.setSymbol("T2=");
        control.setTextValue(bundle.getString("mTemp"));
        control.setUnit("��");
        l1.addView(control);

        t = new TextView(this);
        t.setLayoutParams(lp);
        t.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        t.setText(this.getResources().getString(R.string.components));
        t.setGravity(Gravity.CENTER);
        l2.addView(t);

        ValueHolder value;
        for (int i = 0; i < vh.size(); i++) {
            value = vh.get(i);
            if (value.value.equals(""))
                continue;
            control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
            control.setName(value.name);
            control.setTextValue(value.value);
            control.setUnit("%");
            l2.addView(control);
        }
        t = new TextView(this);
        t.setLayoutParams(lp);
        t.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        t.setText(this.getResources().getString(R.string.test_result));
        t.setGravity(Gravity.CENTER);
        l3.addView(t);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.###");

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.molmass));
        control.setTextValue(df.format(bundle.getDouble("molmass")));
        control.setUnit("g/mol");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.molhhv));
        control.setTextValue(df.format(bundle.getDouble("molHHV")));
        control.setUnit("kJ/mol");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.mollhv));
        control.setTextValue(df.format(bundle.getDouble("molLHV")));
        control.setUnit("kJ/mol");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.masshhv));
        control.setTextValue(df.format(bundle.getDouble("massHHV")));
        control.setUnit("MJ/kg");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.masslhv));
        control.setTextValue(df.format(bundle.getDouble("massLHV")));
        control.setUnit("MJ/kg");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.igvolhhv));
        control.setTextValue(df.format(bundle.getDouble("idealVolHHV")));
        control.setUnit("MJ/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.igvollhv));
        control.setTextValue(df.format(bundle.getDouble("idealVolLHV")));
        control.setUnit("MJ/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.igdenrel));
        control.setTextValue(df.format(bundle.getDouble("idealRelDen")));
        control.setUnit("-");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.igden));
        control.setTextValue(df.format(bundle.getDouble("idealDen")));
        control.setUnit("kg/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.igwebb));
        control.setTextValue(df.format(bundle.getDouble("idealWebbIndex")));
        control.setUnit("-");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.rgvolhhv));
        control.setTextValue(df.format(bundle.getDouble("realVolHHV")));
        control.setUnit("MJ/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.rgvollhv));
        control.setTextValue(df.format(bundle.getDouble("realVolLHV")));
        control.setUnit("MJ/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.rgdenrel));
        control.setTextValue(df.format(bundle.getDouble("realRelDen")));
        control.setUnit("-");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.rgden));
        control.setTextValue(df.format(bundle.getDouble("realDen")));
        control.setUnit("kg/m3");
        l3.addView(control);

        control = (DataDisplayControl) getLayoutInflater().inflate(R.layout.displayitem, null);
        control.setName(this.getResources().getString(R.string.rgwebb));
        control.setTextValue(df.format(bundle.getDouble("realWebbIndex")));
        control.setUnit("MJ/m3");
        l3.addView(control);


        LinearLayout mAdContainer = (LinearLayout) findViewById(R.id.adward);
        DomobAdView mAdview320x50 = new DomobAdView(this, ResultActivity.Publish_ID, ResultActivity.inline1, DomobAdView.INLINE_SIZE_FLEXIBLE);
        mAdContainer.addView(mAdview320x50);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
