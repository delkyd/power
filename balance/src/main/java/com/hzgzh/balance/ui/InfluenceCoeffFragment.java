package com.hzgzh.balance.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hzgzh.balance.R;
import com.hzgzh.balance.model.Global;
import com.hzgzh.balance.model.GlobalProvider;
import com.hzgzh.balance.ui.validation.VibPhaseValidate;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by Administrator on 2015/2/16.
 */
public class InfluenceCoeffFragment extends Fragment {

    MaterialEditText mOrig, mAfter, mMass;
    TextView mResult;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_influence_coeff, container, false);
        initButton(view);
        initView(view);
        return view;
    }

    public void initView(View view) {
        String key = "+-0123456789/.";
        mOrig = (MaterialEditText) view.findViewById(R.id.coeff_origin_vib);
        mOrig.setAutoValidate(true);
        mOrig.setKeyListener(DigitsKeyListener.getInstance(key));
        mOrig.addValidator(new VibPhaseValidate("20/(0-360)"));
        mAfter = (MaterialEditText) view.findViewById(R.id.coeff_after_vib);
        mAfter.setAutoValidate(true);
        mAfter.setKeyListener(DigitsKeyListener.getInstance(key));
        mAfter.addValidator(new VibPhaseValidate("20/(0-360)"));
        mMass = (MaterialEditText) view.findViewById(R.id.coeff_mass);
        mMass.setAutoValidate(true);
        mMass.setKeyListener(DigitsKeyListener.getInstance(key));
        mMass.addValidator(new VibPhaseValidate("20/(0-360)"));
        mResult = (TextView) view.findViewById(R.id.coeff_result);

    }

    public void initButton(final View rootView) {
        final Global g = GlobalProvider.getInstance();
        ImageButton add = (ImageButton) rootView.findViewById(R.id.coeff_add);
        ImageButton sub = (ImageButton) rootView.findViewById(R.id.coeff_substract);
        ImageButton mul = (ImageButton) rootView.findViewById(R.id.coeff_multiply);
        ImageButton div = (ImageButton) rootView.findViewById(R.id.coeff_divide);
        Button calc = (Button) rootView.findViewById(R.id.coeff_calc);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Complex a = g.resolveString(mOrig.getText().toString());
                    Complex b = g.resolveString(mAfter.getText().toString());
                    Complex c = a.add(b);
                    mResult.setText(getResources().getString(R.string.svg_add) + "\n" + g.complexToPolar(c));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Complex a = g.resolveString(mOrig.getText().toString());
                    Complex b = g.resolveString(mAfter.getText().toString());
                    Complex c = a.subtract(b);
                    mResult.setText(getResources().getString(R.string.svg_sub) + "\n" + g.complexToPolar(c));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Complex a = g.resolveString(mOrig.getText().toString());
                    Complex b = g.resolveString(mAfter.getText().toString());
                    Complex c = a.multiply(b);
                    mResult.setText(getResources().getString(R.string.svg_mul) + "\n" + g.complexToPolar(c));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Complex a = g.resolveString(mOrig.getText().toString());
                    Complex b = g.resolveString(mAfter.getText().toString());
                    Complex c = a.divide(b);
                    mResult.setText(getResources().getString(R.string.svg_div) + "\n" + g.complexToPolar(c));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Complex a = g.resolveString(mOrig.getText().toString());
                    Complex b = g.resolveString(mAfter.getText().toString());
                    Complex c = g.resolveString(mMass.getText().toString());
                    Complex d = (b.subtract(a)).divide(c);
                    mResult.setText(getResources().getString(R.string.coeff) + "\n" + g.complexToPolar(d));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
