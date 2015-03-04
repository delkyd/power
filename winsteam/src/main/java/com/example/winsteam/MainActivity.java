package com.example.winsteam;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import cn.domob.android.ads.DomobAdView;

public class MainActivity extends FragmentActivity implements TabListener {
	private static final int REQUEST_CODE_PREFERENCES = 1;
	static final String PUBLISHER_ID="56OJzR54uNVC+oJTko";
	static final String inline1="16TLmKmlApFo2Y2lAbeVC9As";
	static final String inline2="16TLmKmlApFo2NU-qCOCC7Hk";
	/**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		MainActivity.getDeviceInfo(this);
		UmengUpdateAgent.update(this);	
		
		
		 // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        mAppSectionsPagerAdapter.setPageTitle(0, getString(R.string.water_title));
        mAppSectionsPagerAdapter.setPageTitle(1, getString(R.string.eff_title));
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

		/*cb_p.setOnClickListener(this);
		
		cb_t1.setOnClickListener(this);
		cb_x.setOnClickListener(this);
		cb_h.setOnClickListener(this);
		cb_s.setOnClickListener(this);*/
	} 
	 public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
		 	String[] mTitle=new String[2];
	        public AppSectionsPagerAdapter(FragmentManager fm) {
	        	
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int i) {
	            switch (i) {
	                case 0:
	                    // The first section of the app is the most interesting -- it offers
	                    // a launchpad into the other demonstrations in this example application.
	                    return new WaterFragment();

	                default:
	                    // The other sections of the app are dummy placeholders.
	                	
	                    Fragment fragment = new EfficienceFragment();
	                    //Bundle args = new Bundle();
	                    //args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
	                    //fragment.setArguments(args);
	                    return fragment;
	            }
	        }

	        @Override
	        public int getCount() {
	            return 2;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	        	
	            return mTitle[position];
	        }
	        public void setPageTitle(int position,String s){
	        	mTitle[position]=s;
	        }
	    }
	public static class EfficienceFragment extends Fragment {
		DataInputControl ip,it,op,ot;
		android.widget.TextView tv_result;
		HDouble IH=new HDouble(),IS=new HDouble(),OH=new HDouble(),RH=new HDouble();
		java.text.DecimalFormat nf1=new java.text.DecimalFormat("#.##");
		java.text.DecimalFormat nf2=new java.text.DecimalFormat("#.####");
		java.text.DecimalFormat nf3=new java.text.DecimalFormat("#.#######");
		HInteger Range=new HInteger();
		RelativeLayout mAdContainer;
		DomobAdView mAdview320x50;

		
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	MobclickAgent.onEvent(container.getContext(), "efficience");
        	
        	
            View rootView = inflater.inflate(R.layout.efficience, container, false);
            ip=(DataInputControl)rootView.findViewById(R.id.inlet_pressure);
            it=(DataInputControl)rootView.findViewById(R.id.inlet_temp);
            op=(DataInputControl)rootView.findViewById(R.id.outlet_pressure);
            ot=(DataInputControl)rootView.findViewById(R.id.outlet_temp);
            tv_result=(TextView)rootView.findViewById(R.id.tv_eff);
            mAdContainer=(RelativeLayout)rootView.findViewById(R.id.relative_ad1);
            mAdview320x50 = new DomobAdView(container.getContext(), MainActivity.PUBLISHER_ID, MainActivity.inline1, DomobAdView.INLINE_SIZE_FLEXIBLE);
            mAdContainer.addView(mAdview320x50);

            
            
            // Demonstration of a collection-browsing activity.
            rootView.findViewById(R.id.btn_ecalc)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        	try{
                        	double i_p,i_t,o_p,o_t;
            				i_p=ip.getParamValueinSI();
            				i_t=it.getParamValueinSI();
            				o_p=op.getParamValueinSI();
            				o_t=ot.getParamValueinSI();
            				IFC97.PT2H(i_p, i_t, IH, Range);
            				IFC97.PT2S(i_p, i_t, IS, Range);
            				IFC97.PT2H(o_p, o_t, OH, Range);
            				IFC97.PS2H(o_p,IS.d,RH, Range);
            				/*String so=this.getResources().getString(R.string.Efficience);*/
            				double temp=(IH.d-OH.d)/(IH.d-RH.d)*100;
            			            				
            				ValueAnimator animation = ValueAnimator.ofFloat(0f, (float)temp);
            				animation.setDuration(1000);
            				animation.addUpdateListener(new AnimatorUpdateListener() {
            					java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");
            					String s;
            				    @Override
            				    public void onAnimationUpdate(ValueAnimator animation) {
            				    	s=df.format(animation.getAnimatedValue());
            				        tv_result.setText(s+"%");
            				    }
            				});
            				//animation.setInterpolator(new );
            				animation.start();
            				
                        	}catch(java.lang.Exception e){
                        		return;
                         	}
                        }
                    });
            return rootView;
        }


		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			ip.saveUnitPreference();
			it.saveUnitPreference();
			op.saveUnitPreference();
			ot.saveUnitPreference();
			super.onDestroyView();
		}
    }

	public static class WaterFragment extends Fragment {
		DataInputControl press,temp,quality,enh,ens,vol;
		DataDisplayControl cp,cv,ks,ramd,dvis,kvis,sound,energy,prn,jdcs;
		CheckBox cb_p,cb_t1,cb_x,cb_s,cb_v,cb_h;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	MobclickAgent.onEvent(container.getContext(), "property");
        	
            View rootView = inflater.inflate(R.layout.waterprop, container, false);
            RelativeLayout mAdContainer;
    		DomobAdView mAdview320x50;
    		
            mAdContainer=(RelativeLayout)rootView.findViewById(R.id.relative_ad);
            mAdview320x50 = new DomobAdView(container.getContext(), MainActivity.PUBLISHER_ID, MainActivity.inline1, DomobAdView.INLINE_SIZE_FLEXIBLE);
            mAdContainer.addView(mAdview320x50);
            
            
            press=(DataInputControl)rootView.findViewById(R.id.input_pressure);
     		temp=(DataInputControl)rootView.findViewById(R.id.input_temp);
      		quality=(DataInputControl)rootView.findViewById(R.id.input_quality);
     		enh=(DataInputControl)rootView.findViewById(R.id.input_enh);
      		ens=(DataInputControl)rootView.findViewById(R.id.input_ens);
     		vol=(DataInputControl)rootView.findViewById(R.id.input_vol);
     		
     		cp=(DataDisplayControl)rootView.findViewById(R.id.Display_Cp);
     		cv=(DataDisplayControl)rootView.findViewById(R.id.Display_Cv);
     		ks=(DataDisplayControl)rootView.findViewById(R.id.Display_ks);
     		ramd=(DataDisplayControl)rootView.findViewById(R.id.Display_ramd);
     		dvis=(DataDisplayControl)rootView.findViewById(R.id.Display_dvis);
     		kvis=(DataDisplayControl)rootView.findViewById(R.id.Display_kvis);
     		sound=(DataDisplayControl)rootView.findViewById(R.id.Display_sound);
     		energy=(DataDisplayControl)rootView.findViewById(R.id.Display_Energy);
     		jdcs=(DataDisplayControl)rootView.findViewById(R.id.Display_jdcs);
     		prn=(DataDisplayControl)rootView.findViewById(R.id.Display_prn);
	
    		cb_p=(CheckBox)rootView.findViewById(R.id.cb_p);
    		cb_t1=(CheckBox)rootView.findViewById(R.id.cb_t1);
    		cb_x=(CheckBox)rootView.findViewById(R.id.cb_x);
    		cb_h=(CheckBox)rootView.findViewById(R.id.cb_h);
    		cb_s=(CheckBox)rootView.findViewById(R.id.cb_s);
    		cb_v=(CheckBox)rootView.findViewById(R.id.cb_v);
    		
            // Demonstration of a collection-browsing activity.
            rootView.findViewById(R.id.btnCalc)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        	// TODO Auto-generated method stub
                    		boolean b_cb_p,b_cb_t1,b_cb_x,b_cb_h,b_cb_s,b_cb_v;
                    		HDouble P=new HDouble(),T=new HDouble(),X=new HDouble(),V=new HDouble(),H=new HDouble(),S=new HDouble();
                       		java.text.DecimalFormat nf1=new java.text.DecimalFormat("#.##");
                    		java.text.DecimalFormat nf2=new java.text.DecimalFormat("#.####");
                    		java.text.DecimalFormat nf3=new java.text.DecimalFormat("#.#######");
                    		HInteger Range = new HInteger();

                    		b_cb_p = cb_p.isChecked();
                    		b_cb_t1 = cb_t1.isChecked();
                    		b_cb_x = cb_x.isChecked();
                    		b_cb_h = cb_h.isChecked();
                    		b_cb_s = cb_s.isChecked();
                    		b_cb_v = cb_v.isChecked();
                    		try{
                     		if ((b_cb_p && b_cb_x)
                    				&& (!b_cb_t1 && !b_cb_h && !b_cb_s && !b_cb_v)) {
                    			double p =press.getParamValueinSI();
                    			double x =quality.getParamValueinSI();
                    			IFC97.PX2H(p, x, H, Range);
                    			IFC97.PX2S(p, x, S, Range);
                    			IFC97.PX2T(p, x, T, Range);
                    			IFC97.PX2V(p, x, V, Range);
                    			enh.setParamValue(H.d);
                    			ens.setParamValue(S.d);
                    			temp.setParamValue(T.d);
                    			vol.setParamValue(V.d);
                    			this.calcparamter(view, p, T.d);

                    		}
                    		if ((b_cb_p && b_cb_t1)
                    				&& (!b_cb_x && !b_cb_h && !b_cb_s && !b_cb_v)) {
                    			double p = press.getParamValueinSI();
                    			double t = temp.getParamValueinSI();
                    					
                    			IFC97.PT2H(p, t, H, Range);
                    			IFC97.PT2S(p, t, S, Range);
                    			IFC97.PT2X_KK(p, t, X, Range);
                    			IFC97.PT2V(p, t, V, Range);

                    			enh.setParamValue((H.d));
                    			ens.setParamValue((S.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, p, t);
                    		}
                    		if ((b_cb_p && b_cb_h)
                    				&& (!b_cb_t1 && !b_cb_x && !b_cb_s && !b_cb_v)) {
                    			double p = press.getParamValueinSI();
                    					
                    			double h =enh.getParamValueinSI();
                    					
                    			IFC97.PH2T(p, h, T, Range);
                    			IFC97.PH2S(p, h, S, Range);
                    			IFC97.PH2X(p, h, X, Range);
                    			IFC97.PH2V(p, h, V, Range);
                    			temp.setParamValue((T.d));
                    			ens.setParamValue((S.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, p, T.d);
                    		}
                    		if ((b_cb_p && b_cb_s)
                    				&& (!b_cb_t1 && !b_cb_x && !b_cb_h && !b_cb_v)) {
                    			double p = press.getParamValueinSI();
                    					
                    			double s = ens.getParamValueinSI();
                    					
                    			IFC97.PS2T(p, s, T, Range);
                    			IFC97.PS2H(p, s, H, Range);
                    			IFC97.PS2X(p, s, X, Range);
                    			IFC97.PS2V(p, s, V, Range);

                    			temp.setParamValue((T.d));
                    			enh.setParamValue((H.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, p, T.d);
                    		}
                    		if ((b_cb_p && b_cb_v)
                    				&& (!b_cb_t1 && !b_cb_x && !b_cb_h && !b_cb_s)) {
                    			double p = press.getParamValueinSI();
                    					
                    			double v = vol.getParamValueinSI();
                    					
                    			IFC97.PV2T(p, v, T, Range);
                    			IFC97.PV2H(p, v, H, Range);
                    			IFC97.PV2X(p, v, X, Range);
                    			IFC97.PV2S(p, v, S, Range);
                    			temp.setParamValue((T.d));
                    			enh.setParamValue((H.d));
                    			quality.setParamValue((X.d));
                    			ens.setParamValue((S.d));
                    			this.calcparamter(view, p, T.d);
                    		}
                    		if ((b_cb_t1 && b_cb_x)
                    				&& (!b_cb_p && !b_cb_s && !b_cb_h && !b_cb_v)) {
                    			double t = temp.getParamValueinSI();
                    					
                    			double x = quality.getParamValueinSI();
                    					
                    			IFC97.TX2P(t, x, P, Range);
                    			IFC97.TX2H(t, x, H, Range);
                    			IFC97.TX2S(t, x, S, Range);
                    			IFC97.TX2V(t, x, V, Range);
                    			press.setParamValue((P.d));
                    			enh.setParamValue((H.d));
                    			ens.setParamValue((S.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, t
                    					);
                    		}
                    		if ((b_cb_t1 && b_cb_h)
                    				&& (!b_cb_p && !b_cb_s && !b_cb_x && !b_cb_v)) {
                    			double t = temp.getParamValueinSI();
                    					
                    			double h =enh.getParamValueinSI();
                    					
                    			IFC97.TH2P(t, h, P, Range);
                    			IFC97.TH2X(t, h, X, Range);
                    			IFC97.TH2S(t, h, S, Range);
                    			IFC97.TH2V(t, h, V, Range);
                    			press.setParamValue((P.d));
                    			ens.setParamValue((S.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, t);
                    		}
                    		if ((b_cb_t1 && b_cb_s)
                    				&& (!b_cb_p && !b_cb_h && !b_cb_x && !b_cb_v)) {
                    			double t = temp.getParamValueinSI();
                    					
                    			double s = ens.getParamValueinSI();
                    					
                    			IFC97.TS2P(t, s, P, Range);
                    			IFC97.TS2X(t, s, X, Range);
                    			IFC97.TS2H(t, s, H, Range);
                    			IFC97.TS2V(t, s, V, Range);
                    			press.setParamValue((P.d));
                    			enh.setParamValue((H.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, t);
                    		}
                    		if ((b_cb_t1 && b_cb_v)
                    				&& (!b_cb_p && !b_cb_h && !b_cb_x && !b_cb_s)) {
                    			double t = temp.getParamValueinSI();
                    					
                    			double v = vol.getParamValueinSI();
                    					
                    			IFC97.TV2P(t, v, P, Range);
                    			IFC97.TV2X(t, v, X, Range);
                    			IFC97.TV2H(t, v, H, Range);
                    			IFC97.TV2S(t, v, S, Range);
                    			press.setParamValue((P.d));
                    			enh.setParamValue((H.d));
                    			quality.setParamValue((X.d));
                    			ens.setParamValue((S.d));
                    			this.calcparamter(view, P.d, t);
                    		}
                    		if ((b_cb_h && b_cb_s)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_x && !b_cb_v)) {
                    			double h =enh.getParamValueinSI();
                    					
                    			double s = ens.getParamValueinSI();
                    					
                    			IFC97.HS2P(h, s, P, Range);
                    			IFC97.HS2T(h, s, T, Range);
                    			IFC97.HS2X(h, s, X, Range);
                    			IFC97.HS2V(h, s, V, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			quality.setParamValue((X.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		if ((b_cb_h && b_cb_x)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_s && !b_cb_v)) {
                    			double h =enh.getParamValueinSI();
                    					
                    			double x = quality.getParamValueinSI();
                    					
                    			IFC97.HX2P(h, x, P, Range);
                    			IFC97.HX2T(h, x, T, Range);
                    			IFC97.HX2S(h, x, S, Range);
                    			IFC97.HX2V(h, x, V, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			ens.setParamValue((S.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		if ((b_cb_h && b_cb_v)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_s && !b_cb_x)) {
                    			double h = enh.getParamValueinSI();
                    					
                    			double v = vol.getParamValueinSI();
                    					
                    			IFC97.HV2P(h, v, P, Range);
                    			IFC97.HV2T(h, v, T, Range);
                    			IFC97.HV2S(h, v, S, Range);
                    			IFC97.HV2X(h, v, X, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			ens.setParamValue((S.d));
                    			quality.setParamValue((X.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		if ((b_cb_s && b_cb_x)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_h && !b_cb_v)) {
                    			double s = ens.getParamValueinSI();
                    					
                    			double x = quality.getParamValueinSI();
                    					
                    			IFC97.SX2P(s, x, P, Range);
                    			IFC97.SX2T(s, x, T, Range);
                    			IFC97.SX2H(s, x, H, Range);
                    			IFC97.SX2V(s, x, V, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			enh.setParamValue((H.d));
                    			vol.setParamValue((V.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		if ((b_cb_s && b_cb_v)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_h && !b_cb_x)) {
                    			double s = ens.getParamValueinSI();
                     			double v = vol.getParamValueinSI();
                    					
                    			IFC97.SV2P(s, v, P, Range);
                    			IFC97.SV2T(s, v, T, Range);
                    			IFC97.SV2H(s, v, H, Range);
                    			IFC97.SV2X(s, v, X, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			enh.setParamValue((H.d));
                    			quality.setParamValue((X.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		if ((b_cb_v && b_cb_x)
                    				&& (!b_cb_p && !b_cb_t1 && !b_cb_h && !b_cb_s)) {
                    			double v = vol.getParamValueinSI();
                    					
                    			double x = quality.getParamValueinSI();
 	
                    			IFC97.VX2P(v, x, P, Range);
                    			IFC97.VX2T(v, x, T, Range);
                    			IFC97.VX2H(v, x, H, Range);
                    			IFC97.VX2S(v, x, S, Range);
                    			press.setParamValue((P.d));
                    			temp.setParamValue((T.d));
                    			enh.setParamValue((H.d));
                    			ens.setParamValue((S.d));
                    			this.calcparamter(view, P.d, T.d);
                    		}
                    		}catch(java.lang.Exception e){
                    			return;
                    		}
                        }

                        private void calcparamter(View view, double p,double t) {
                        	// TODO Auto-generated method stub
                        	HDouble temp = new HDouble(), t2 = new HDouble();
                        	HInteger Range = new HInteger();
                        	
                        	IFC97.PT2CP(p, t, temp, Range);
                        	cp.setParamValue(temp.d);
                        	
                        	IFC97.PT2CV(p, t, temp, Range);
                        	cv.setParamValue(temp.d);
                        	
                        	IFC97.PT2E(p, t, temp, Range);
                        	energy.setParamValue(temp.d);
                        	
                        	IFC97.PT2Eta(p, t, temp, Range);
                        	dvis.setParamValue(temp.d);
                        	
                        	IFC97.PT2U(p, t, temp, Range);
                        	kvis.setParamValue(temp.d);
                        	
                        	IFC97.PT2KS(p, t, temp, Range);
                        	ks.setParamValue(temp.d);
                        	
                        	IFC97.PT2RAMD(p, t, temp, Range);
                        	ramd.setParamValue(temp.d);
                        	
                        	IFC97.PT2PRN(p, t, temp, Range);
                        	prn.setParamValue(temp.d);
                        	
                        	IFC97.PT2EPS(p, t, temp, Range);
                        	jdcs.setParamValue(temp.d);
                        	
                        	IFC97.PT2SSP(p, t, temp, Range);
                        	sound.setParamValue(temp.d);
                        	
                        }
                    });
            return rootView;
        }
		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			cp.saveUnitPreference();
			cv.saveUnitPreference();
			dvis.saveUnitPreference();
			energy.saveUnitPreference();
			enh.saveUnitPreference();
			ens.saveUnitPreference();
			jdcs.saveUnitPreference();
			ks.saveUnitPreference();
			kvis.saveUnitPreference();
			press.saveUnitPreference();
			prn.saveUnitPreference();
			quality.saveUnitPreference();
			temp.saveUnitPreference();
			ramd.saveUnitPreference();
			sound.saveUnitPreference();
			vol.saveUnitPreference();
			super.onDestroyView();
		}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

		@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.action_settings:
			i = new Intent(this, FragmentPreferences.class);   
			//ͨ��Intent������������������Main���Activity
			this.startActivityForResult(i, MainActivity.REQUEST_CODE_PREFERENCES);
			//����Main����
			return true;
		case R.id.action_about:
			i  = new Intent(this,AboutActivity.class);   
			//ͨ��Intent������������������Main���Activity
			this.startActivity(i);
			return true;
		default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			// When the given tab is selected, switch to the corresponding page in the ViewPager.
	        mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		private long exitTime = 0;  
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if(keyCode == KeyEvent.KEYCODE_BACK  
		            && event.getAction() == KeyEvent.ACTION_DOWN){  
		        if((System.currentTimeMillis()-exitTime) > 2000){  
		            Toast.makeText(getApplicationContext(), "�ٰ�һ�κ��˼��˳�����", Toast.LENGTH_SHORT).show();  
		            exitTime = System.currentTimeMillis();  
		        } else {  
		            //�˳�����  
		        	finish();
		        }  
		        return true;  
		    }  
		    return super.onKeyDown(keyCode, event);  
		}

		@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        // The preferences returned if the request code is what we had given
	        // earlier in startSubActivity
	        if (requestCode == REQUEST_CODE_PREFERENCES) {
	            // Read a sample value they have set
	        	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	        	String syncConnPref = sharedPref.getString("key_ifc_preference","");
	        }
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
		public static String getDeviceInfo(Context context) {
		    try{
		      org.json.JSONObject json = new org.json.JSONObject();
		      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
		          .getSystemService(Context.TELEPHONY_SERVICE);
		  
		      String device_id = tm.getDeviceId();
		      
		      android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		          
		      String mac = wifi.getConnectionInfo().getMacAddress();
		      json.put("mac", mac);
		      
		      if( TextUtils.isEmpty(device_id) ){
		        device_id = mac;
		      }
		      
		      if( TextUtils.isEmpty(device_id) ){
		        device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
		      }
		      
		      json.put("device_id", device_id);
		      
		      return json.toString();
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		  return null;
		}
}
