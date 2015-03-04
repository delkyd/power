package com.hzgzh.balance;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hzgzh.balance.ui.AboutFragment;
import com.hzgzh.balance.ui.RootFragment;
import com.hzgzh.balance.util.SessionsHelper;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import cn.bmob.v3.Bmob;


public class MainActivity extends ActionBarActivity implements
        ActionBar.OnNavigationListener {

    final static String BMOB_API = "7ff7c9b865680053a5746b5f74270ffe";
    final static String UMENG_API = "54ee7ea9fd98c5990c0001f9";
    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundColor(getResources().getColor(R.color.teal_500));

        setSupportActionBar(toolbar);
        if (findViewById(R.id.fragment_container) != null) {

            Fragment fragment = new RootFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        Bmob.initialize(this, BMOB_API);
        AnalyticsConfig.setAppkey(UMENG_API);
        AnalyticsConfig.setChannel("BAIDU");
        MobclickAgent.updateOnlineConfig(this);
        UmengUpdateAgent.update(this);
        UmengUpdateAgent.silentUpdate(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //      BusProvider.getInstance().register(this);
        com.umeng.analytics.MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //      BusProvider.getInstance().unregister(this);
        com.umeng.analytics.MobclickAgent.onPause(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SessionsHelper helper = new SessionsHelper(this);
        switch (item.getItemId()) {
            case R.id.menu_share:
                // On ICS+ devices, we normally won't reach this as ShareActionProvider will handle
                // sharing.
                helper.shareSession(this, R.string.share_template, "",
                        "", "");
                return true;
            case R.id.action_about:
                Fragment fragment = new AboutFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(int position, long id) {

        return true;
    }


}
