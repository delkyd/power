package com.example.boiler.asme;

public class ASMEH {
    //Enthalpy of Ash
    static public double HAshTCToBtuPerlbm(double t) {
        double tk = TCtoTK(t);
        if (tk <= 1000)
            return HbtuPerlbm(AshC225K1000K, tk);
        else
            return HBtuPerlbm(UpAshC1000K, tk);
    }

    static public double HAshTFToBtuPerlbm(double t) {
        double tk = TFtoTK(t);
        if (tk <= 1000)
            return HBtuPerlbm(AshC225K1000K, tk);
        else
            return HBtuPerlbm(UpAshC1000K, tk);
    }

    public static double HAshTCToKJPerKg(double t) {
        double tk = TCtoTK(t);
        if (tk <= 1000)
            return HKJPerKg(AshC225K1000K, tk);
        else
            return HKJPerKg(UpAshC1000K, tk);
    }

    public static double HAshTFToKJPerKg(double t) {
        double tk = TFtoTK(t);
        if (tk <= 1000)
            return HKJPerKg(Ash225K1000K, tk);
        else
            return HKJPerKg(UpAshC1000K, tk);
    }

    //������
    public static double HAirTCToBtuPerlbm(double t);

    public static double HAirTFToBtuPerlbm(double t);

    public static double HAirTCToKJPerKg(double t);

    public static double HAirTFToKJPerKg(double t);
    //
}
