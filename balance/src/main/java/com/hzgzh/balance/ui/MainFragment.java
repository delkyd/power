package com.hzgzh.balance.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hzgzh.balance.R;
import com.hzgzh.balance.model.Global;
import com.hzgzh.balance.model.GlobalProvider;
import com.hzgzh.balance.model.Model;
import com.hzgzh.balance.ui.validation.VibPhaseValidate;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;


/**
 * Created by Administrator on 2015/2/7.
 */
public class MainFragment extends android.support.v4.app.Fragment {
    final float mTextSize = 14;
    String digits = "+-01234567890./";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Global global = GlobalProvider.getInstance();
        global.firstIter = true;

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initSpinner(rootView);
        initHead(rootView);
        initMassContainer(rootView);
        initBody(rootView);
        initFooter(rootView);
        initCoeffButton(rootView);

        if (global.updated) {
            initLoadData(rootView);
            global.updated = false;
        }
        return rootView;
    }

    private void initLoadData(View view) {
        Global g = GlobalProvider.getInstance();

        JSONObject model = g.model;
        try {
            EditText et = (EditText) view.findViewWithTag("name");
            et.setText(model.get("name").toString());

            et = (EditText) view.findViewWithTag("model");
            et.setText(model.get("model").toString());

            for (int i = 0; i < g.num_mass; i++) {
                et = (EditText) view.findViewWithTag("mass" + i);
                et.setText(model.get("mass" + i).toString());
            }

            for (int i = 0; i < g.num_vp; i++) {
                et = (EditText) view.findViewWithTag("vp_name" + i);
                et.setText(model.get("vp_name" + i).toString());
                for (int j = 0; j < g.num_mass; j++) {
                    et = (EditText) view.findViewWithTag("vp" + i + "coeff" + j);
                    et.setText(model.get("vp" + i + "coeff" + j).toString());
                }
            }
        } catch (Exception e) {

        }
    }

    private void initHead(View view) {
        MaterialEditText et = (MaterialEditText) view.findViewById(R.id.unit_name);
        et.setTag("name");
        et = (MaterialEditText) view.findViewById(R.id.unit_model);
        et.setTag("model");
    }

    private void initFooter(final View view) {
        initCalcButton(view);
        initIterButton(view);
        initUploadButton(view);

    }

    void initCalcButton(final View view) {
        Button calcButton = (Button) view.findViewById(R.id.btn_calc);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Global g = GlobalProvider.getInstance();
                    g.solve(view);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void initIterButton(final View view) {
        Button button = (Button) view.findViewById(R.id.btn_iter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Global g = GlobalProvider.getInstance();
                    g.iter(view);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void initUploadButton(final View view) {
        Button button = (Button) view.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.upload(getActivity(), view);
                //Global g=GlobalProvider.getInstance();
                //g.setModel(view);
                //Model.saveModel(getActivity());
            }
        });
    }

    void initCoeffButton(final View view) {
        Button button = (Button) view.findViewById(R.id.btn_coeff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("main_fragment");
                transaction.replace(R.id.fragment_container, new InfluenceCoeffFragment());
                transaction.commit();
            }
        });
    }

    private void initSpinner(final View view) {
        Global g = GlobalProvider.getInstance();
        Spinner mSpNumVp = (Spinner) view.findViewById(R.id.sp_vp);
        Spinner mSpNumMass = (Spinner) view.findViewById(R.id.sp_mass);
        mSpNumVp.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sp_vp)));
        mSpNumMass.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sp_mass)));
        mSpNumVp.setSelection(g.num_vp - 1);
        mSpNumMass.setSelection(g.num_mass - 1);
        mSpNumVp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                Global g = GlobalProvider.getInstance();
                if (g.num_mass > position + 1) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_vp_less_mass), Toast.LENGTH_SHORT).show();
                    parent.setSelection(g.num_vp - 1);
                    return;
                }
                if (g.num_vp == position + 1)
                    return;
                changeMeasurePoint(view, position + 1);
                g.num_vp = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpNumMass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                Global g = GlobalProvider.getInstance();
                if (g.num_vp < position + 1) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_vp_less_mass), Toast.LENGTH_SHORT).show();
                    parent.setSelection(g.num_mass - 1);
                    return;
                }
                if (g.num_mass == position + 1)
                    return;
                changeMassContainer(view, position + 1);
                g.num_mass = position + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initMassContainer(View view) {
        LinearLayout container = (LinearLayout) view.findViewById(R.id.masscontain);
        Global global = GlobalProvider.getInstance();
        for (int i = 0; i < global.num_mass; i++) {
            LinearLayout met = newMassLinearLayout(getActivity(), getResources().getString(R.string.mass_position) + (i + 1),
                    getResources().getString(R.string.mass_result), getResources().getString(R.string.mass_help), i);
            container.addView(met);
        }

    }

    private void changeMeasurePoint(View view, int num_vp) {
        Global g = GlobalProvider.getInstance();
        LinearLayout body = (LinearLayout) view.findViewById(R.id.body);
        int count = body.getChildCount();
        if (count > num_vp) {
            for (int i = count - 1; i >= num_vp; i--) {
                body.removeViewAt(i);
            }
        }
        if (count < num_vp) {
            for (int i = count; i < num_vp; i++) {
                LinearLayout bd = newMearusrePoint(getActivity(), i, g.num_mass);
                body.addView(bd);
            }
        }
    }

    private void changeMassContainer(View view, int num_mass) {

        LinearLayout container = (LinearLayout) view.findViewById(R.id.masscontain);
        LinearLayout body = (LinearLayout) view.findViewById(R.id.body);
        int count = container.getChildCount();

        if (num_mass < count) {
            for (int i = count - 1; i >= num_mass; i--) {
                container.removeViewAt(i);
            }
            for (int i = 0; i < body.getChildCount(); i++) {
                ViewGroup temp = (ViewGroup) body.findViewWithTag("coeff_container" + i);
                int c = temp.getChildCount();
                for (int j = c - 1; j >= num_mass; j--) {
                    temp.removeViewAt(j);
                }
            }
        }

        if (num_mass > count) {
            for (int i = count; i < num_mass; i++) {
                LinearLayout met = newMassLinearLayout(getActivity(), getResources().getString(R.string.mass_position) + (i + 1),
                        getResources().getString(R.string.mass_result), getResources().getString(R.string.mass_help), i);
                container.addView(met);

            }
            for (int i = 0; i < body.getChildCount(); i++) {
                ViewGroup temp = (ViewGroup) body.findViewWithTag("coeff_container" + i);
                for (int j = count; j < num_mass; j++) {
                    LinearLayout.LayoutParams sameweight = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                    sameweight.leftMargin = 8;
                    sameweight.rightMargin = 8;
                    MaterialEditText metvib = newMaterialEditTextMode(view.getContext(), getResources().getString(R.string.coeff) + (j + 1),
                            "50/200 um/°");

                    metvib.setLayoutParams(sameweight);
                    metvib.setTag("vp" + i + "coeff" + Integer.toString(j));
                    metvib.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                    metvib.setKeyListener(DigitsKeyListener.getInstance(digits));
                    metvib.setAutoValidate(true);
                    metvib.addValidator(new VibPhaseValidate("25/(0-360)"));
                    temp.addView(metvib);

                }
            }
        }
    }

    private LinearLayout newMassLinearLayout(Context context, String floatLabelText, String floatLabelText1, String helpText, int numMass) {
        LinearLayout l = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l.setLayoutParams(lp);
        l.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams sameweight = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        sameweight.leftMargin = 8;
        sameweight.rightMargin = 8;
        MaterialEditText met = new MaterialEditText(context);

        met.setLayoutParams(sameweight);
        met.setFloatingLabel(2);
        met.setFloatingLabelAlwaysShown(true);
        met.setFloatingLabelText(floatLabelText);
        met.setHelperText(helpText);
        met.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);

        met.setTag("mass" + Integer.toString(numMass));

        l.addView(met);

        met = new MaterialEditText(context);

        met.setLayoutParams(sameweight);
        met.setFloatingLabel(2);
        met.setFloatingLabelAlwaysShown(true);
        met.setFloatingLabelText(floatLabelText1);
        met.setEnabled(false);
        met.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        met.setTag("result" + numMass);

        l.addView(met);
        return l;
    }

    private void initBody(View view) {
        LinearLayout body = (LinearLayout) view.findViewById(R.id.body);

        Global global = GlobalProvider.getInstance();
        for (int i = 0; i < global.num_vp; i++) {
            LinearLayout bd = newMearusrePoint(getActivity(), i, global.num_mass);
            body.addView(bd);
        }
    }

    private LinearLayout newMearusrePoint(Context context, int numVp, int numMass) {
        LinearLayout vp = new LinearLayout(context);
        vp.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vp.setLayoutParams(lp);

        vp.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams sameweight = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);


        LinearLayout header = new LinearLayout(context);
        header.setLayoutParams(lp);
        header.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        MaterialEditText mName = newMaterialEditTextMode(context, getResources().getString(R.string.vp_name)
                , getResources().getString(R.string.vp_help));

        mName.setLayoutParams(sameweight);
        mName.setHelperText(false);
        mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);

        mName.setTag("vp_name" + numVp);

        MaterialEditText mOrigVib = newMaterialEditTextMode(context, getResources().getString(R.string.orig_vib),
                "50/200 um/°");

        sameweight.leftMargin = 8;
        sameweight.rightMargin = 8;

        mOrigVib.setLayoutParams(sameweight);

        mOrigVib.setAutoValidate(true);
        mOrigVib.addValidator(new VibPhaseValidate("25/(0-360)"));
        mOrigVib.setTag("vp_vib" + Integer.toString(numVp));

        mOrigVib.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        mOrigVib.setKeyListener(DigitsKeyListener.getInstance(digits));

        MaterialEditText mResidual = newMaterialEditTextMode(context, getResources().getString(R.string.residual_vib),
                "um/°");
        mResidual.setLayoutParams(sameweight);

        mResidual.setTag("residual" + Integer.toString(numVp));
        mResidual.setEnabled(false);
        header.addView(mName);
        header.addView(mOrigVib);
        header.addView(mResidual);
        vp.addView(header);

        LinearLayout temp = new LinearLayout(context);
        temp.setLayoutParams(lp);
        temp.setOrientation(LinearLayout.HORIZONTAL);
        temp.setTag("coeff_container" + numVp);
        for (int i = 0; i < numMass; i++) {
            MaterialEditText metvib = newMaterialEditTextMode(context, getResources().getString(R.string.coeff) + (i + 1),
                    "50/200 um/°");

            metvib.setLayoutParams(sameweight);
            metvib.setTag("vp" + numVp + "coeff" + Integer.toString(i));
            metvib.setKeyListener(DigitsKeyListener.getInstance(digits));
            metvib.setAutoValidate(true);
            metvib.addValidator(new VibPhaseValidate("25/(0-360)"));

            temp.addView(metvib);
        }
        vp.addView(temp);
        return vp;
    }

    private MaterialEditText newMaterialEditTextMode(Context context, String name, String helptext) {
        LinearLayout.LayoutParams sameweight = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        MaterialEditText met = new MaterialEditText(context);

        met.setLayoutParams(sameweight);
        met.setFloatingLabel(2);
        met.setFloatingLabelAlwaysShown(true);
        met.setFloatingLabelText(name);
        met.setHelperText(helptext);
        met.setAutoValidate(true);
        met.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);

        return met;
    }

    String getRandom(double mag) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
        String vib = df.format(Math.abs(Math.random() * mag));
        String phase = df.format(Math.abs(Math.random() * 360));

        return vib + "/" + phase;
    }
}
