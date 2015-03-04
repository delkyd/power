package com.example.boiler;

import java.io.Serializable;

public final class Density implements Serializable {
    final static double O2 = 1.42985;
    final static double N2 = 1.2505;
    final static double CO2 = 1.9768;
    final static double SO2 = 2.9263;
    final static double NO = 1.3402;
    final static double N2O = 1.9780;
    final static double H2O = 0.804;

    public static double O2() {
        return Density.O2;
    }

    public static double N2() {
        return Density.N2;
    }

    public static double CO2() {
        return Density.CO2;
    }

    public static double SO2() {
        return Density.SO2;
    }

    public static double NO() {
        return Density.NO;
    }

    public static double N2O() {
        return Density.N2O;
    }

    ;

    public static double H2O() {
        return Density.H2O;
    }
}
