package com.hzgzh.naturegasheat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

public class CompressFactorActivity extends Activity {
    private static final int REQUEST_CODE_PREFERENCES = 1;
    int type = 0;
    TextView mTotal, mCompressFactor;
    DataDisplayControl mPress, mTemp;
    Button btn_calc;
    MyAdapter mAdapter;
    ListView lv;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initView();
        btn_calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                NatureGasCompressFactor ngcf = new NatureGasCompressFactor();
                ArrayList<ValueHolder> vh_list = mAdapter.getValue();
                try {
                    int total = Math.round(Float.parseFloat(mTotal.getText()
                            .toString()));
                    if (total != 100) {
                        Toast.makeText(v.getContext().getApplicationContext(),
                                R.string.total_error, Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }

                    double t = Double.parseDouble(mTemp.getEditValue());
                    double p = Double.parseDouble(mPress.getEditValue());


                    double[] value = new double[21];

                    for (int i = 0; i < vh_list.size(); i++) {
                        ValueHolder vh = vh_list.get(i);
                        if (vh.value.equals("")) {
                            value[i] = 0;
                            continue;
                        }
                        value[i] = Double.parseDouble(vh.value);
                    }

                    ghc.calc(value, p2, t1, t2, type);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(v.getContext().getApplicationContext(),
                            R.string.input_error, Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // The preferences returned if the request code is what we had given
        // earlier in startSubActivity
        if (requestCode == REQUEST_CODE_PREFERENCES) {
            // Read a sample value they have set
            SharedPreferences sharedPref = PreferenceManager
                    .getDefaultSharedPreferences(this);
            String pref = sharedPref.getString("ratio", "");
            if (pref.equals("vol%")) {
                type = 1;
                mAdapter.setUnit(pref);
            }
            if (pref.equals("mol%")) {
                type = 0;
                mAdapter.setUnit(pref);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.action_settings:
                i = new Intent(this, FragmentPreferences.class);
                // ͨ��Intent������������������Main���Activity
                startActivityForResult(i, CompressFactorActivity.REQUEST_CODE_PREFERENCES);
                // ����Main����
                return true;
            case R.id.action_about:
                i = new Intent(this, AboutActivity.class);
                // ͨ��Intent������������������Main���Activity
                this.startActivity(i);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "�ٰ�һ�κ��˼��˳�����",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // �˳�����
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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


    void initView() {
        mTotal = (TextView) findViewById(R.id.total_compress_factor);
        mCompressFactor = (TextView) findViewById(R.id.compress_factor);
        mPress = (DataDisplayControl) findViewById(R.id.pressure_compress_factor);
        mTemp = (DataDisplayControl) findViewById(R.id.temperature_compress_factor);

        mTemp.setName(this.getResources().getString(R.string.comb_temp));
        mTemp.setUnit("��");
        mPress.setName(getResources().getString(R.string.meas_press));
        mPress.setUnit("kPa");

        lv = (ListView) findViewById(R.id.lv_component_compress_factor);
        btn_calc = (Button) findViewById(R.id.btn_calc_compress_factor);
        mAdapter = new MyAdapter(this);
        lv.setAdapter(mAdapter);
    }
}
