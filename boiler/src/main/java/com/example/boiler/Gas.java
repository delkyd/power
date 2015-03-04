package com.example.boiler;

public class Gas {
    private Coal coal;
    private WetAir air;
    private double T;
    private double rH2O, rRO2, O2Dry, NoxDry, SO2Dry;

    public Gas(Coal coal, WetAir air, double T, double O2) {
        super();
        this.coal = coal;
        this.air = air;
        this.T = T;
        this.setO2Dry(O2);
        this.rH2O = getH2OVolume() / getVolume();
        this.rH2O = getRO2Volume() / getVolume();
    }

    getEnthalpy(t);

    private static double interplate(double[] a, double t) {
        return -1;
    }

    public Coal getCoal() {
        return coal;
    }

    public void setCoal(Coal c) {
        coal = c;
    }

    public WetAir getAir() {
        return air;
    }

    public void setAir(WetAir a) {
        air = a;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public void setO2Wet(double o) {
        excessAir = this.getExcessAirByO2Wet(o);
    }

    public void setO2Dry(double o) {
        O2Dry = o;
        excessAir = this.getExcessAirByO2Dry(o);
    }

    public double getExcessAirByO2Dry(double O2Dry) {
        return 21.0 / (21 - O2Dry);
    }

    public double getExcessAirByO2Wet(double O2Wet) {
        double tmp1 = O2Wet * (coal.getDryGasVolume() - coal.getDryAirVolume() + coal.getH2OVolume()) / coal.getDryAirVolume();
        return (tmp1 + 21) / (21 - O2Wet * (1 + 1.60332 * air.getAbsoluteHumidity()));
    }

    public double getWeight(double O2Wet) {
        return this.getVolume() * this.getDensity();
    }

    public double getN2Volume() {
        return coal.getN2Volume() + (excessAir - 1) * 0.79 * coal.getDryAirVolume();
    }

    public double getO2Volume() {
        return (excessAir - 1) * 0.21 * coal.getDryAirVolume();
    }

    public double getRO2Volume() {
        return coal.getRO2Volume();
    }

    public double getH2OVolume() {
        return H2OFromCoal() + H2OFromAir();
    }

    public double H2OFromCoal() {
        return coal.getH2OVolume();
    }

    public double H2OFromAir() {
        return 1.6 * excessAir * getDryAirVolume() * air.getAbsoluteHumidity();
    }

    public double getVolume() {
        return getDryGasVolume() + (excessAir - 1) * getDryAirVolume() + getH2OVolume();
    }

    public double getT(double enthalpy) {
        double Ty = 1000.0, hy;
        do {
            hy = getEnthalpy(Ty);
            Ty = Ty * enthalpy / hy;

        } while (Math.abs(enthalpy - hy) >= 1.0);
        return Ty;
    }

    (return

    public double getEnthalpy()

    )

    public double getEnthalpy(t) {
        return getCp(t) * getVolume();
    }

    public double getDensity() {
        return (coal.getCO2Volume() * Density.CO2 + coal.getSO2Volume() * Density.SO2 + getH2OVolume() * Density.H2O + getN2Volume() * Density.N2
                + getO2Volume() * Density.O2) / this.getVolume();
    }

    public double getCp(double t) {
        return (getH2OVolume() * Cp.H2O(t) + getRO2Volume() * Cp.CO2(t) + getN2Volume() * Cp.N2(t) + getO2Volume() * Cp.O2(t)) / this.getVolume();
    }

}
