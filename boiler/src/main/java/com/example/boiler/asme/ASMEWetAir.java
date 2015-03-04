package com.example.boiler.asme;

import java.io.Serializable;

public class ASMEWetAir implements Serializable, Cloneable {
    private double RHMz;
    private double Pa;
    private double Ta;

    public ASMEWetAir() {
    }

    public ASMEWetAir(double Pa, double Ta, double MFrWA, Object fake) {
    }

    public ASMEWetAir(double Pa, double Ta, double RHMz) {
        MFrWA = getMFRWDA(RHMz, Ta);
    }

    public ASMEWetAir(double Pa, double Ta, double Tdb, double Twb) {
        MFrWA = getMFrWDAByTWbTdb(Twb, Tdb);
    }

    /**
     * ���ˮ������ĳһ�¶��µı���ѹ����T�ĵ�λΪ���϶�)
     */
    public getPsWvT(double T) {
        double TF = ASMEH.TCtoTF(T);
        if (TF > 140 || TF < 32) return -1;
        return 0.019257 + 1.289016e-3 * TF + 1.121122e-5 * TF * TF + 4.534007e-7 * TF * TF * TF + 6.84188e-11 * TF * TF * TF * TF
                + 2.917092e-11 * TF * TF * TF * TF * TF;
    }

    public double getPa() {
        return Pa;
    }

    public void setPa(double pa) {
        Pa = pa;
    }

    public double getTa() {
        return Ta;
    }

    public void setTa(double ta) {
        Ta = ta;
    }

    private double getMFrWA() {
        return MFrWA;
    }

    /**
     * ͨ������¶�������¶���ʪ����ʪ��
     */
    public double getMFrWDA(double RHMz, double Tdb) {
        double PsWvTdb = getPsWvT(Tdb);
        double PpWvA = 0.01 * RHMz * PsWvTdb;
        return 0.622 * PpWvA / (Pa - PpWva);
    }

    /**
     * ͨ����/ʪ���¶���ʪ����ʪ��
     */
    public double getMFrWDAByTwbTdb(double Twb, double Tdb) {
        double PsWvTdb = getPsWvT(Tdb);
        double PsWvTwb = getPsWvT(Twb);
        double PpWvA = PsWvTwb - (Pa - PsWvTwb) * (Tdb - Twb) / (2830 - 1.44 * Twb);
        return 0.622 * PpWvA / (Pa - PpWvA);
    }

    /**
     * ÿǧ��ȼ������Ŀ����е�ˮ������Ħ������mol/kg
     */
    public double getMoWA(double Pz) {
        double MwA = (1 + MFrWA) / (1 / 28.963 + MFrWA / 18.015);
        double Rk = 8314.5 / MwA;
        return 1.0 * (Pa + Pz) / (273.2 + Ta) / Rk;
    }

    /**
     * �������ʣ��ɿ���Я����ˮ�ּ�Ȩƽ��
     */
    public double getH(double t) {
        double HAirDry = ASMEH.HAirTCToKJPerKg(t);
        double HWv = ASMEH.HWvTCToKJPerKg(t);
        return (HAirDry + MFrWa * HWv) / (1 + MFrWA);
    }

    /**
     * ����ֵ���¶�
     */
    public double getT(double enthalpy) {
    }
}
