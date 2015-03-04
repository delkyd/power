package com.hzgzh.balance.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.hzgzh.balance.R;

/**
 * Created by Administrator on 2015/2/25.
 */
public class RootFragment extends Fragment {
    WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_root, container, false);
        initButton(rootView);
        mWebView = (WebView) rootView.findViewById(R.id.webview);
        return rootView;
    }

    private void initButton(View view) {
        Button btn_coeff = (Button) view.findViewById(R.id.btn_coeff);
        Button btn_harmonic = (Button) view.findViewById(R.id.btn_harmonic);
        Button btn_my = (Button) view.findViewById(R.id.btn_my);

        btn_coeff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment fragment = new MainFragment();
                changeFragment(fragment, "method_coeff");
            }
        });
        btn_harmonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HarmonicFragment fragment = new HarmonicFragment();
                changeFragment(fragment, "method_harmonic");
            }
        });
        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntroFragment fragment = new IntroFragment();
                changeFragment(fragment, "my");
            }
        });

    }

    private void changeFragment(Fragment fragment, String fragmentname) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(fragmentname);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
