package com.hzgzh.naturegasheat;

import android.app.ActionBar;
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
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE_PREFERENCES = 1;
    DataDisplayControl cTemp;
    DataDisplayControl mPress;
    DataDisplayControl mTemp;
    ListView lv;
    TextView total;
    Button btn_calc;
    MyAdapter mAdapter;
    TextView tv_total;
    int type = 1;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getActionBar();
        UmengUpdateAgent.update(this);
        MobclickAgent.updateOnlineConfig(this);
        actionBar.setHomeButtonEnabled(true);

        InitView();

        btn_calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                GasHeatCalculation ghc = new GasHeatCalculation();
                ArrayList<ValueHolder> vh_list = mAdapter.getValue();
                try {
                    int total = Math.round(Float.parseFloat(tv_total.getText()
                            .toString()));
                    if (total != 100) {
                        Toast.makeText(v.getContext().getApplicationContext(),
                                R.string.total_error, Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }

                    int t1 = Integer.parseInt(cTemp.getSpinnerValue());
                    double p2 = Double.parseDouble(mPress.getEditValue());
                    int t2 = Integer.parseInt(mTemp.getSpinnerValue());

                    double[] value = new double[57];

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

                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList("component", vh_list);
                bundle.putString("ctemp", cTemp.getSpinnerValue());
                bundle.putString("mPress", mPress.getEditValue());
                bundle.putString("mTemp", mTemp.getSpinnerValue());
                bundle.putDouble("molmass", ghc.getMolMass());
                bundle.putDouble("molHHV", ghc.getMolHHV());
                bundle.putDouble("molLHV", ghc.getMolLHV());
                bundle.putDouble("massHHV", ghc.getMassHHV());
                bundle.putDouble("massLHV", ghc.getMassLHV());
                bundle.putDouble("idealVolHHV", ghc.getIdealGasVolumeHHV());
                bundle.putDouble("idealVolLHV", ghc.getIdealGasVolumeLHV());
                bundle.putDouble("idealRelDen",
                        ghc.getIdeaGaslRelativeDensity());
                bundle.putDouble("idealDen", ghc.getIdealGasDensity());
                bundle.putDouble("idealWebbIndex", ghc.getIdealGasWebbIndex());
                bundle.putDouble("realVolHHV", ghc.getRealGasVolumeHHV());
                bundle.putDouble("realVolLHV", ghc.getRealGasVolumeLHV());
                bundle.putDouble("realRelDen", ghc.getRealGasRelativeDensity());
                bundle.putDouble("realDen", ghc.getRealGasDensity());
                bundle.putDouble("realWebbIndex", ghc.getRealGasWebbIndex());

                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    void InitView() {
        total = (TextView) findViewById(R.id.total);
        cTemp = (DataDisplayControl) findViewById(R.id.combustion_temp);
        mPress = (DataDisplayControl) findViewById(R.id.measure_press);
        mTemp = (DataDisplayControl) findViewById(R.id.measure_temp);

        cTemp.setName(this.getResources().getString(R.string.comb_temp));
        cTemp.setUnit("��");
        mPress.setName(getResources().getString(R.string.meas_press));
        mPress.setUnit("kPa");
        mPress.setEditValue("101.325");

        mTemp.setName(getResources().getString(R.string.meas_temp));
        mTemp.setUnit("��");
        String[] str = {"25", "20", "15", "0"};
        cTemp.setSpinnerValue(str);
        String[] str1 = {"20", "15", "0"};
        mTemp.setSpinnerValue(str1);
        tv_total = (TextView) findViewById(R.id.total);

        lv = (ListView) findViewById(R.id.lv_component);
        btn_calc = (Button) findViewById(R.id.btn_calc);
        mAdapter = new MyAdapter(this);
        lv.setAdapter(mAdapter);
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
                startActivityForResult(i, MainActivity.REQUEST_CODE_PREFERENCES);
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

}
