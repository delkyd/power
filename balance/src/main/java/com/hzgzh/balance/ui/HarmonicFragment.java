package com.hzgzh.balance.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hzgzh.balance.R;
import com.hzgzh.balance.model.Global;
import com.hzgzh.balance.model.GlobalProvider;
import com.hzgzh.balance.ui.validation.VibPhaseValidate;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by Administrator on 2015/2/22.
 */
public class HarmonicFragment extends Fragment {
    String digits = "+-01234567890./";
    MaterialEditText mA_vib, mA_after, mA_mass, mB_vib, mB_after, mB_mass;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_harmonic, container, false);

        initBody(view);
        initButton(view);
        return view;
    }

    private void initBody(View view) {
        mA_vib = setMedFormat(view, R.id.a_vib);
        mA_after = setMedFormat(view, R.id.a_after);
        mA_mass = setMedFormat(view, R.id.a_mass);
        mB_vib = setMedFormat(view, R.id.b_vib);
        mB_after = setMedFormat(view, R.id.b_after);
        mB_mass = setMedFormat(view, R.id.b_mass);

    }

    private MaterialEditText setMedFormat(View view, int res) {
        MaterialEditText med = (MaterialEditText) view.findViewById(res);
        med.setAutoValidate(true);
        med.addValidator(new VibPhaseValidate("25/(0-360)"));
        med.setKeyListener(DigitsKeyListener.getInstance(digits));
        return med;
    }

    private void initButton(final View view) {
        Button calc = (Button) view.findViewById(R.id.btn_calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc(view);
            }
        });
    }

    private void calc(View view) {
        Global g = GlobalProvider.getInstance();
        try {

            Complex a_vib = g.resolveString(mA_vib.getText().toString());
            Complex a_aft = g.resolveString(mA_after.getText().toString());
            Complex a_mass = g.resolveString(mA_mass.getText().toString());
            Complex b_vib = g.resolveString(mB_vib.getText().toString());
            Complex b_aft = g.resolveString(mB_after.getText().toString());
            Complex b_mass = g.resolveString(mB_mass.getText().toString());
            Complex apb_vib = a_vib.add(b_vib);
            Complex apb_aft = a_aft.add(b_aft);
            Complex apb_mass = a_mass.add(b_mass);
            Complex apb_coe = (apb_aft.subtract(apb_vib)).divide(apb_mass);

            Complex asb_vib = a_vib.subtract(b_vib);
            Complex asb_aft = a_aft.subtract(b_aft);
            Complex asb_mass = a_mass.subtract(b_mass);
            Complex asb_coe = (asb_aft.subtract(asb_vib)).divide(asb_mass);
            Complex zero = new Complex(0, 0);
            Complex apb_result = apb_mass.multiply(apb_vib.divide(apb_aft.subtract(apb_vib)));
            Complex asb_result = asb_mass.multiply(asb_vib.divide(asb_aft.subtract(asb_vib)));
            apb_result = zero.subtract(apb_result.divide(2));
            asb_result = zero.subtract(asb_result.divide(2));
            Complex a_result = apb_result.add(asb_result);
            Complex b_result = apb_result.subtract(asb_result);


            setText(view, R.id.apb_vib, apb_vib);
            setText(view, R.id.apb_aft, apb_aft);
            setText(view, R.id.apb_mass, apb_mass);
            setText(view, R.id.apb_coe, apb_coe);
            setText(view, R.id.apb_result, apb_result);

            setText(view, R.id.asb_vib, asb_vib);
            setText(view, R.id.asb_aft, asb_aft);
            setText(view, R.id.asb_mass, asb_mass);
            setText(view, R.id.asb_coe, asb_coe);
            setText(view, R.id.asb_result, asb_result);

            setText(view, R.id.a_result, a_result);
            setText(view, R.id.b_result, b_result);
        } catch (Exception e) {

        }
    }

    private void setText(View view, int res, Complex c) {
        Global g = GlobalProvider.getInstance();
        EditText et = (EditText) view.findViewById(res);
        et.setText(g.complexToPolar(c));
    }
}
