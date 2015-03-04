package com.hzgzh.balance.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzgzh.balance.R;

/**
 * Created by Administrator on 2015/2/16.
 */
public class AccountStorageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_account_storage, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
