package com.example.boiler;

public class Cp {
    private static final double[] H2O = {
            1.4943, 1.5052, 1.5223, 1.5424, 1.5654, 1.5897, 1.6148,
            1.6412, 1.6680, 1.6956, 1.7229, 1.7501, 1.7769, 1.8028,
            1.8280, 1.8527, 1.8761, 1.8996, 1.9213, 1.9423, 1.9628,
            1.9825, 2.0009, 2.0189, 2.0364, 2.0528};
    private static final double[] CpO2 = {
            1.3059, 1.3176, 1.3352, 1.3561, 1.3775, 1.3980, 1.4168,
            1.4344, 1.4499, 1.4645, 1.4775, 1.4893, 1.5005, 1.5106,
            1.5202, 1.5294, 1.5378, 1.5462, 1.5541, 1.5617, 1.5692,
            1.5759, 1.5830, 1.5897, 1.5964, 1.6027};
    private static final double[] CpCO2 = {
            1.5998, 1.7002, 1.7873, 1.8627, 1.9297, 1.9887, 2.0411,
            2.0884, 2.1311, 2.1692, 2.2035, 2.2349, 2.2628, 2.2898,
            2.3136, 2.3354, 2.3555, 2.3743, 2.3915, 2.4074, 2.4221,
            2.4359, 2.4484, 2.4602, 2.4710, 2.4811};
    private static final double[] CpN2 = {
            1.2946, 1.2958, 1.2996, 1.3067, 1.3163, 1.3276, 1.3402,
            1.3536, 1.3670, 1.3795, 1.3917, 1.4034, 1.4134, 1.4252,
            1.4348, 1.4440, 1.4528, 1.4612, 1.4687, 1.4759, 1.4825,
            1.4893, 1.4951, 1.5010, 1.5064, 1.5114};
    private static final double[] CpFA = {
            0.7955, 0.8374, 0.8667, 0.8918, 0.9211, 0.9240, 0.9504,
            0.9630, 0.9797, 1.0048, 1.0258, 1.0509, 1.0969, 1.1304,
            1.1849, 1.2228, 1.2979, 1.3398, 1.3816, 1.4235};

    private static double interplate(double[] a, double t) {
        int min_t = (int) (Math.floor(t / 100));
        if (min_t == a.length - 1) return a[a.length - 2];
        return a[min_t] + (a[min_t + 1] - a[min_t]) * (t / 100 - min_t);
    }

    public static double O2(double t) {
        return interplate(CpO2, t);
    }

    public static double N2(double t) {
        return interplate(CpN2, t);
    }

    public static double CO(double t) {
        if (t < 500)
            return 1.29929 - 8.66407 * 1e-7 * t + 2.27936 * 1e-7 * t * t - 1.04629 * 1e-10 * t * t * t;
        return 0;
    }

    static public double CO2(double t) {
        return interplate(CpCO2, t);
    }

    static public double H2O(double t) {
        return interplate(H2O, t);
    }

    static public double DryAir(double t) {
        return 0.79 * N2(t) + 0.21 * O2(t);
    }

    static public double Ash(double t) {
        return interplate(CpFA, t);
    }

    /**
     * @param c  ú��Ӧ�û�
     * @param tr ��ȼ����¶�
     * @return
     */
    static public double coal(Coal c, double tr) {
        //��ȼ���ʱ���
        //Vr-ú�Ŀ�ȼ����ӷ���%
        double Cr = 0.84 + 37.68e-6 * (13 + c.Vr) * (130 + tr);
        double Cp_r_r = 0.84 + 37.68 * 1e-6 * (13 + c.getV()) * (130 + t);
        double Cp_h = Ash(t);
        double Ash_r = 100 * c.getA() / (100 - c.getM());
        double Cp_r_g = 0.01 * (Cp_h * Ash_r + Cp_r_r * (100 - Ash_r));
        double Cp_r = Cp_r_g * (100 - c.getM()) / 100 + 4.1868 * c.getM() / 100;
        return Cp_r;
    }
}
