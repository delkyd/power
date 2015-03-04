package com.hzgzh.balance.model;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hzgzh.balance.R;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/2/7.
 */
public class Global {
    public int num_vp = 4;
    public int num_mass = 1;
    public boolean updated = false;

    public JSONObject model = new JSONObject();
    public double[] delta;

    public boolean firstIter = true;

    public RealMatrix getMatrix() {

        RealMatrix matrix = MatrixUtils.createRealMatrix(2 * num_vp, 2 * num_mass);
        for (int i = 0; i < num_vp; i++) {
            for (int j = 0; j < num_mass; j++) {
                String s = null;
                try {
                    s = (String) model.get("vp" + i + "coeff" + j);
                } catch (JSONException e) {

                }
                Complex c = resolveString(s);
                matrix.setEntry(2 * i, 2 * j, c.getReal());
                matrix.setEntry(2 * i, 2 * j + 1, -c.getImaginary());
                matrix.setEntry(2 * i + 1, 2 * j, c.getImaginary());
                matrix.setEntry(2 * i + 1, 2 * j + 1, c.getReal());
            }
        }
        return matrix;
    }

    public RealVector getVector() {
        double[] vector = new double[2 * num_vp];
        for (int i = 0; i < num_vp; i++) {
            String s = null;
            try {
                s = (String) model.get("vp_vib" + i);
            } catch (JSONException e) {

            }
            Complex c = resolveString(s);
            vector[2 * i] = -c.getReal();
            vector[2 * i + 1] = -c.getImaginary();
        }
        return MatrixUtils.createRealVector(vector);
    }

    public double[] getDelta(RealVector r) {
        double s = Math.sqrt(r.dotProduct(r) / num_vp);
        Complex[] delta = new Complex[num_vp];
        for (int i = 0; i < num_vp; i++) {
            delta[i] = new Complex(r.getEntry(2 * i), r.getEntry(2 * i + 1));
        }
        double[] d = new double[num_vp];
        for (int i = 0; i < num_vp; i++) {
            d[i] = Math.sqrt(delta[i].abs() / s);
        }
        return d;
    }

    public void iter(View view) {

        if (firstIter) {
            delta = new double[num_vp];
            for (int i = 0; i < num_vp; i++) {
                delta[i] = 1;
            }
            firstIter = false;
        }

        RealVector x, r;
        setModel(view);
        RealMatrix a = getMatrix();
        RealVector b = getVector();

        for (int i = 0; i < num_vp; i++) {
            RealVector t = a.getRowVector(2 * i).mapMultiply(delta[i]);
            a.setRowVector(2 * i, t);
            t = a.getRowVector(2 * i + 1).mapMultiply(delta[i]);
            a.setRowVector(2 * i + 1, t);
        }
        for (int i = 0; i < num_vp; i++) {
            b.setEntry(2 * i, b.getEntry(2 * i) * delta[i]);
            b.setEntry(2 * i + 1, b.getEntry(2 * i + 1) * delta[i]);
        }
        DecompositionSolver solve = new QRDecomposition(a).getSolver();
        x = solve.solve(b);
        r = residues(a, b, x);
        delta = getDelta(r);
        for (int i = 0; i < num_vp; i++) {
            r.setEntry(2 * i, r.getEntry(2 * i) / delta[i]);
            r.setEntry(2 * i + 1, r.getEntry(2 * i + 1) / delta[i]);
        }
        setResult(view, x, r);
    }

    public String complexToPolar(Complex c) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
        double mag = Math.sqrt(c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
        double phase = 180.0 / Math.PI * Math.atan2(c.getImaginary(), c.getReal());
        if (phase < 0)
            phase = 360 + phase;
        return df.format(mag) + "/" + df.format(phase);
    }

    public void setResult(View view, RealVector x, RealVector res) {
        Global g = GlobalProvider.getInstance();

        for (int i = 0; i < g.num_mass; i++) {
            TextView tv = (TextView) view.findViewWithTag("result" + i);
            Complex c = new Complex(x.getEntry(2 * i), x.getEntry(2 * i + 1));
            tv.setText(complexToPolar(c));
            tv.setTextColor(view.getResources().getColor(R.color.black));
        }
        for (int i = 0; i < g.num_vp; i++) {
            TextView tv = (TextView) view.findViewWithTag("residual" + i);
            Complex c = new Complex(res.getEntry(2 * i), res.getEntry(2 * i + 1));
            tv.setText(complexToPolar(c));
            tv.setTextColor(view.getResources().getColor(R.color.black));
        }
    }

    public void solve(View view) {

        setModel(view);
        RealMatrix a = getMatrix();
        RealVector b = getVector();
        DecompositionSolver solve = new QRDecomposition(a).getSolver();
        RealVector x = solve.solve(b);
        RealVector r = residues(a, b, x);
        setResult(view, x, r);
        firstIter = true;
    }

    public RealVector residues(RealMatrix a, RealVector b, RealVector x) {
        RealMatrix x1 = MatrixUtils.createColumnRealMatrix(x.toArray());
        RealMatrix b1 = MatrixUtils.createColumnRealMatrix(b.toArray());
        RealMatrix r = a.multiply(x1).subtract(b1);
        return r.getColumnVector(0);
    }

    public Complex resolveString(String s) {
        int index = s.indexOf('/');
        String radius = s.substring(0, index).trim();
        String angle = s.substring(index + 1, s.length());
        return ComplexUtils.polar2Complex(Double.parseDouble(radius), Double.parseDouble(angle) * Math.PI / 180.0);
    }

    public void setModel(View view) {

        setModelData(view, "name");
        setModelData(view, "model");

        for (int i = 0; i < num_mass; i++) {
            setModelData(view, "mass" + i);
        }
        for (int i = 0; i < num_vp; i++) {
            setModelData(view, "vp_name" + i);
            setModelData(view, "vp_vib" + i);
            for (int j = 0; j < num_mass; j++) {
                setModelData(view, "vp" + i + "coeff" + j);
            }
        }

    }

    private void setModelData(View view, String name) {
        EditText et = (EditText) view.findViewWithTag(name);
        try {
            model.put(name, et.getText().toString());
        } catch (JSONException e) {
            Log.e("JSON", "error");
        }
    }
}
