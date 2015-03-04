package com.example.boiler.asme;

import java.io.Serializable;

public class ASMEGas implements Serializable, Cloneable {
    /**
     * ʪ�����ɷ��ݻ����ݻ�������%�����������ɼ���ó�
     */
    public double DVpO2, DVpCO2, DVpCO, DVpNOx, DVpSO2, DVpSO2, DVpN2F, DVpN2a, DVpH2O;
    /**
     * �������ۿ�������ʪ��������ʪ�������ܶȣ�ʪ������Ħ��������������Ħ��������������ϵ��
     */
    public double MqThACr, MqFg, DnFg, MoFg, MoDFg, XpA;
    /**
     * �������õ�ȼ��ת��Ϊʪ�����������������������CO2��������д����ˮ�֣������д����ˮ�ֵ�
     */
    public doubel MqFgF, MqCO2Sb, MqWSb, MqWA, MqDA, MqWF, MqWvF, MqWH2F, MqWFg;
    /**
     * ���幹�������ļ�������������Դ�����ǵĴ�ȡ����
     */
    private ASMECoal coal;
    private ASMEWetAir air;
    private ASMESorbent sb;
    /**
     * ���������е���������Դ���紵��������(t/h)����Ҫ����
     */
    private double[] MrWAdz;

    public void setCoal(ASMECoal c) {
        coal = c;
    }

    public void setAir(ASMEWetAir a) {
        air = a;
    }

    public void setSorbent(ASMESorbent s) {
        sb = s;
    }

    public void setMrWAdz(double[] wadz) {
        MrWAdz = wadz;
    }

    /**
     * ÿǧ��ȼ�ϲ�����ʪ������t�¶��µ���ֵ���ɸ�ʪ������Ȩƽ������λkj/kg
     */
    public double getHWet(double t) {
        double dryGasHKJperKg = getHDry(t);
        double waterInGasKJperKg = ASMEH.HWvTCToKJPerKg(t);
        double hWetGas = MqWFg * waterInGasKJperKg + getMqDFg() * dryGasHKJperKg;
        return hWetGas / MqFg;

    }

    /**
     * ÿǧ��ȼ�ϲ������������¶�t�µ���ֵ��������������Ȩƽ����kJ/kg(ȼ�ϣ�
     */
    public double getHDry(double t) {
        return (44.01 * DVpCO2 * ASMEH.HCO2TCToKJPerKg(t)
                + 28.158 * DVpN2a * ASMEH.HN2SteamTCToKJPerKg(t)
                + 28.013 * DVpN2F * ASMEH.HN2TCToKJPerKg(t)
                + 32.00 * DVpO2 * ASMEH.HO2TCToKJPerKg(t)
                + 64.064 * DVpSO2 * ASMEH.HSO2TCToKJPerKg(t)
                + 28.01 * DVpCO2 * ASMEH.HCOTCToKJPerKg(t)) / getMwDFg();
    }

    /**
     * ��ʪ������Ħ����������λkg/mol
     */
    public double getMwDFg() {
        return (4401 * DVpCO2 + 2815.8 * DVpN2a + 2801.3 * DVpN2F +
                3200 * DVpO2 + 6406.4 * DVpSO2 + 2801 * DVpCO) / 100;
    }

    /**
     * ����ֵ���¶ȣ��Ǹ���������
     */
    public double getT(double enthalpy) {
    }

    /**
     * ��ȡ����ˮ��zhongliang
     */
    public double getMFrWAd(double MrF) {
        double MFrWad = 0;
        if (MrWAdz != null) {
            for (int i = 0; i < MrWAdz.length; i++)
                MFrWad += MrWAdz[i] / MrF;
        }
        return MFrWad;
    }

    /**
     * ʪ������kg/s
     */
    public double getMrFg(double MrF) {
        return MqFg * MrF * coal.HHVF;
    }

    /**
     * ʪ������ kg/kg
     */
    public double getMFrFg() {
        return MqFg * coal.HHVF;
    }

    /**
     * ������������λ����������kg/J
     */
    public double getMqDFg() {
        return MqFg - MqWFg;
    }

    /**
     * �����е�ˮ������
     */
    public double getMpWFg() {
        return MqWFg / MqFg;
    }

    /**
     * �������ɷַ������㣬��������ASMEGas��ĺ��ģ����ñ������󣬾Ϳ����������ʵȱ���
     */
    public void calcXpADry(double MrF) {
        double MoThACr = coal.getMOThACr(Sb.MFrSc);
        double MoDPc = coal.MpCb / 1201 + (1 - Sb.MFrSc) * coal.MpSF / 3206.4
                + coal.MpN2F / 2801.3 + Sb.MoCO2Sb;
        double MoWPC = MoDPc + coal.MpH2F / 201.6 + coal.MpH2OF / 1801.5 + getMFrWAd(MrF) / 18.015;
        XpA = 100 * (DVpO2 * (MoDPc + MoThACr * 0.7905) / (MoThACr * (20.95 - DVpO2)));
        MqThACr = coal.getMqThACr(Sb.MFrSc);
        MqWF = coal.MpH2OF / 100 / coal.HHVF;
        MqWH2F = 8.937 * coal.MpH2F / 100 / coal.HHVF;
        MqCO2Sb = Sb.MFrCO2Sb / coal.HHVF;
        MqWSb = Sb.MFrWSb / coal.HHVF;
        MqWA = air.getMFrWA() * MqDA / 100;
        MqDA = (100 + XpA) * coal.getMqThACr(Sb.MFrSc) / 100;
        MqFgF = (100 - coal.MpAsF - coal.MpCF + coal.MpCb - Sb.MFrSc * coal.MpSF) / 100 / coal.HHVF;
        MqWFg = MqWF + MqWvF + MqWH2F + MqWSb + MqWA;
        MqFg = MqFgF + MqDA + MqWA + MqCO2Sb + MqWSb + MqWvF;
        MoFg = MoWPC + MoThACr * (0.7905 + air.getMoWA() + XpA * (1 + air.getMoWA()) / 100);
        MoDFg = MoDPc + MoThACr * (0.7905 + XpA / 100);
        VpO2 = XpA * MoThACr * 0.2095 / MoFg;
        DVpO2 = XpA * MoThACr * 0.2095 / MoDFg;
        VpCO2 = (coal.MpCb / 12.01 + 100 * Sb.MoCO2Sb) / MoFg;
        DVpCO2 = (coal.MpCb / 12.01 + 100 * Sb.MoCO2Sb) / MoDFg;
        VpSO2 = coal.MpSF / 32.064 * (1 - Sb.MFrSc) / MoFg;
        DVpSO2 = coal.MpSF / 32.064 * (1 - Sb.MFrSc) / MoDFg;
        VpN2F = coal.MpN2F / 28.013 / MoFg;
        DVpN2F = coal.MpN2F / 28.013 / MoDFg;
        VpN2a = 100 - VpO2 - VpCO2 - VpSO2 - VpH2O - VpN2F;
        DVpN2a = 100 - DVpO2 - DVpCO2 - DVpSO2 - DVpN2F;
        VpH2O = (Sb.MoWSb * 100 + coal.MpH2F / 2.016 + coal.MpH2OF / 18.015 + getMFrWAd(MrF) / 18.015)
                + (100 + XpA) * MoThACr * air.getMoWA())/MoFg;
        DVpH2O = 100 * (MoFg - MoDFg) / MoDFg;
    }

    /**
     * ʪ������������������calcXpADryһ����������ʪ��������ʼ�ɷֽ��м���
     */
    public void calcXpAWet(double MrF) {
        double MoWA = air.getMoWA();
        double MoThACr = coal.getMOThACr(Sb.MFrSc);
        double MoDPc = coal.MpCb / 1201 + (1 - Sb.MFrSc) * coal.MpSF / 3206.4
                + coal.MpN2F / 2801.3 + Sb.MoCO2Sb;
        double MoWPc = MoDPc + coal.MpH2F / 201.6 + coal.MpH2OF / 1801.5 + getMFrWAd(MrF) / 18.015;
        XpA = 100 * (VpO2 * (MoWPc + MoThACr * (0.7905 + MoWA)) / (MoThACr * (20.95 - VpO2 * (1 + MoWA))));
        MqThACr = coal.getMqThACr(Sb.MFrSc);
        MqWF = coal.MpH2OF / 100 / coal.HHVF;
        MqWH2F = 8.937 * coal.MpH2F / 100 / coal.HHVF;
        MqCO2Sb = Sb.MFrCO2Sb / coal.HHVF;
        MqWSb = Sb.MFrWSb / coal.HHVF;
        MqWA = air.getMFrWA() * MqDA / 100;
        MqDA = (100 + XpA) * coal.getMqThACr(Sb.MFrSc) / 100;
        MqFgF = (100 - coal.MpAsF - coal.MpCF + coal.MpCb - Sb.MFrSc * coalMpSF) / 100 / coal.HHVF;
        MqWFg = MqWF + MqWvF + MqWH2F + MqWSb + MqWA;
        MqFg = MqFgF + MqDA + MqWA + MqCO2Sb + MqWSb + MqWvF;
        MoFg = MoWPc + MoThACr * (0.7905 + MoWA + XpA * (1 + MoWA) / 100);
        MoDFg = MoDPc + MoThACr * (0.7905 + XpA / 100);
        VpO2 = XpA * MoThACr * 0.2095 / MoFg;
        DVpO2 = XpA * MoThACr * 0.2095 / MoDFg;
        VpCO2 = (coal.MpCb / 12.01 + 100 * Sb.MoCO2Sb) / MoFg;
        DVpCO2 = (coal.MpCb / 12.01 + 100 * SbMoCO2Sb) / MoDFg;
        VpSO2 = coal.MpSF / 32.064 * (1 - Sb.MFrSc) / MoFg;
        DVpSO2 = coal.MpSF / 32.064 * (1 - Sb.MFrSc) / MoDFg;
        VpN2F = coal.MpN2F / 28.013 / MoFg;
        DVpN2F = coal.MpN2F / 28.013 / MoDFg;
        VpN2a = 100 - VpO2 - VpCO2 - VpSO2 - VpH2O - VpN2F;
        DVpN2a = 100 - DVpO2 - DVpCO2 - DVpSO2 - DVpN2F;
        VpH2O = (Sb.MoWSb * 100 + coal.MpH2F / 2.016 + coalMpH2OF / 18.015) + getMFrWAd(MrF) / 18.015 + (100 + XpA) * MoThACr * air.getMoWA() / MoFg;
        DVpH2O = 100 * (MoFg - MoDFg) / MoDFg;
    }

    //�����¶ȼ���
    public double getTFgLvCr(double TFgLv, double TAEn, double O2En) {
        double TFgLvCr = TFgLv + 10;//��ֵ
        double dT = 0;
        double HATFgLv = air.getH(TFgLv);
        double HAEn = air.getH(TAEn);
        double MnCpA = (HATFgLv - HAEn) / (TFgLv - TAEn);
        ASMEGas gasEn = (ASMEGas) this.clone();
        gasEn.DVpO2 = O2En;
        gasEn.calcXpADry();
        do {
            double MqFgEn = gasEn.MqFg;
            double MqFgLv = MqFg;
            //�����¶ȱ�ΪF����ȡʪ�����¶���TFFgLv-TFFgLvCr֮���ƽ�������ݣ�
            double TFFgLv = 1.8 * TFgLv + 32;
            double TFFgLvCr = 1.8 * TFgLvCr + 32;
            double TMnFg = (TFFgLv + TFFgLvCr) / 2;
            double TMnFgCF = 2 * TMnFg - 77;
            double TMnFgCC = (TMnFgCF + 459.7) / 1.8 - 273.15;
            double HMnFg = getHWet(TMnFgCC);
            double MnCpFg = HMnFg / (TMnFgCC - 25);
            //����������������¶ȣ������ֵ�Ƚϣ����ж��ٲ�ֵ
            double TFgLvCrNew = TFgLv + MnCpA / MnCpFg * (MqFgLv / MqFgEn - 1) * (TFgLv - TAEn);
            dT = Math.abs(TFgLvCr - TFgLvCrNew);
            TFgLvCr = (TFgLvCr + TFgLvCrNew) / 2;

        } while (dT > 0.1);
        return TFgLvCr;
    }
}
