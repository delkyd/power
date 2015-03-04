package com.example.boiler;

import java.io.Serializable;

public class WetAir implements Serializable, Cloneable {
    private double P;
    private double T;
    private double absoluteHumidity = 0.01;
    private double relevativeHumidity;

    public WetAir() {
    }

    public WetAir(double P, double T, double absoluteHumidity) {
        super();
        this.P = P;
        this.T = T;
        this.absoluteHumidity = absoluteHumidity;
    }

    public WetAir(double P, double T, double relvHumidity, Object fake) {
        super();
        this.P = P;
        this.T = T;
        this.absoluteHumidity = this.setRelevativeHumidity(relvHumidity);
    }

    public double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public double getT() {
        return T;
    }

    public void setT(double p) {
        T = t;
    }

    public double setRelevativeHumidity(double rh) {
        relevativeHumidity = rh;
        if ((T > 50) || (T < 0)) return -1;
        double ps = 611.7927 + 42.7809 * T + 1.6883 * T * T + 0.012079 * T * T * T + 6.1637 * 1e-4 * T * T * T * T;
        return 0.622 * rh * ps / (ps * 100 - rh * ps);
    }

    public double Cp(double t) {
        double uDriedAir = Cp.DryAir(t);
        double massWater = 1.2982 * absoluteHumidity; /*1Nm3->kg*/
        return uDriedAir + massWater * Cp.H2O(t);
    }

    public double getDensity() {
        return 1.2928 / (T + 273.15) * 273.15;
    }
}
