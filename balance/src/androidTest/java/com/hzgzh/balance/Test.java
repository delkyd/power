package com.hzgzh.balance;

import android.test.InstrumentationTestCase;

import com.hzgzh.balance.model.Global;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

/**
 * Created by Administrator on 2015/2/11.
 */
public class Test extends InstrumentationTestCase {

    public void testResolveString() throws Exception {
        Global g = new Global();

        Global.Parameter p = g.new Parameter();

        g.resolveString("230/240", p);
        assertEquals(p.vib, 230.0);
        assertEquals(p.phase, 240.0);


    }

    public void testSolve() throws Exception {
        RealMatrix m = MatrixUtils.createRealMatrix(new double[][]{{1, 1, -1}, {2, 1, 0}, {1, -1, 0}, {-1, 2, 1}});
        RealVector v = MatrixUtils.createRealVector(new double[]{2, -3, 1, 4});
        DecompositionSolver qrsolve = new QRDecomposition(m).getSolver();

        RealVector r_qr = qrsolve.solve(v);

        double[] x = r_qr.toArray();
        assertEquals(x[0], -0.71756, 0.001);
        assertEquals(x[1], 0.715, 0.001);
        assertEquals(x[2], 0.988, 0.001);

    }

    public void testSvdSolve() throws Exception {
        RealMatrix m = MatrixUtils.createRealMatrix(new double[][]{{1, 1, -1}, {2, 1, 0}, {1, -1, 0}, {-1, 2, 1}});
        RealVector v = MatrixUtils.createRealVector(new double[]{2, -3, 1, 4});
        DecompositionSolver svdsolver = new SingularValueDecomposition(m).getSolver();
        RealVector r_svd = svdsolver.solve(v);
        double[] x = r_svd.toArray();
        assertEquals(x[0], -0.71756, 0.001);
        assertEquals(x[1], 0.715, 0.001);
        assertEquals(x[2], 0.988, 0.001);

    }

}
