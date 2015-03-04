package com.example.boiler.asme;

import java.io.Serializable;

public class ASMESorbent implements Serializable, Cloneable {
    /**
     * ¯���Ƿ�������ı�־�����û���������ֵΪFALSE
     */
    public boolean trueObject = true;
    /**
     * ������ɷ֣�����CaCO3,MgCO3,CaO2H2,MgO2H2,Ash,Mosit,�����ٷֱ�(%),��Դ����
     */
    public double CaCO3, MgCO3, CaO2H2, MgO2H2, Ash, Moist;
    /**
     * ÿǧ��ȼ�����������������CO2�����ȣ�����׼�������������������ˮ�ְ�������������ˮ����
     * Ca(HO)2��Mg(HO)2���������ˮ��
     */
    public double MFrCO2Sb, MoCO2Sb, MqCO2Sb, MqWSb, MoWSb, MoFrCaS;
    /**
     * ������Ҫ�ļ���ֵ��CaCO3�����շݶ�������ʣ�%������������ָ¯����������ϵͳ��¯��ʱֵΪ0
     */
    public double MoFrClhCc, MFrSc = 0;

    /**
     * ���캯������һ����������ĳɷ����������һ������һ���ٶ�������������ʱ
     */
    public ASMESorbent(double CaCO3, double MgCO3, double CaO2H2, double MgO2H2, double Ash, double Mosit) {
    }

    public ASMESorbent(Object ob) {
        this.MFrCO2Sb = 0;
        trueObject = false;
    }

    public double getMFrSb() {
        return MFrSb;
    }

    public double getMqCO2Sb(doubel HHVF) {
        return MFrCO2Sb / HHVF;
    }

    public double getMqWSb(double MrF, double HHVF) {
        return MFrWSb / HHVF;
    }

    public double getMFrWSb() {
        return MFrWSb;
    }

    public double getMoFrCaS() {
        return MoFrCaS;
    }//����Ħ����

    public double getMFrSsb() {
        return MFrSsb;
    }//ȼ����������kg/s

    /**
     * �øɻ������ɷֵȲ������������ʵļ��㣬�ó���ν��мٶ�Cb��֪������µ������ʼ������ȼú�ݶ�ļ��㣬��ju
     * �˼��������MFrSsb��
     */
    public double getMFrScDry(ASMEGas gas, ASMEResidue[] rs, double MrSb, double MrF, double MoFrClhCc) {
        MFrSc = 0.95;//����һ�����շݶ�ĳ�ֵ
        ASMECoal c = gas.getCoal();
        if (c.MpCb == 0) return -1;
        MFrSb = MrSb / MrF;
        MFrCcSb = MFrSb * CaCO3 / 100;
        MFrMcSb = MFrSb * MgCO3 / 100;
        MFrChSb = MFrSb * CaO2H2 / 100;
        MFrMhSb = MFrSb * MgO2H2 / 100;
        MFrWSb = MFrSb * Moist / 100;
        MFrSsb = MFrSb * Ash / 100;
        double e = 0.001;
        do {
            double MoSO2 = c.MpSF / 3206.4;
            double MoThaPc = c.getMoThA();
            MoCO2Sb = MFrCcSb * MoFrClhCc / 100.089 + MFrMcSb / 84.3 + MFrChSb / 74.096 + MFrMhSb / 58.32;
            double MoDPc = c.MpCb / 1201 + c.MpSF / 3206. + c.MpN2F / 2801.3 + MoCO2Sb;
            double MFrScNew = (1 - gas.DVpSO2 * (MoDPc + MoThaPc * 0.7905) / 100 / (1 - gas.DVpO2 / 20.95) / MoSO2) /
                    (1 - 0.887 * gas.DVpO2 / 20.95 / (1 - gas.DVpO2 / 20.95));
            e = Math.abs(MFrScNew - MFrSc);
            MFrSc = 0.5 * (MFrScNew + MFrSc);
        } while (e > 0.001);
        double MFrSO3 = 0.025 * MFrSc * c.MpSF;
        MFrCO2Sb = 44.01 * MoCO2Sb;
        MoWSb = MFrWsb / 18.015 + MFrChSb / 74.83 + MFrMhSb / 58.23;//ÿǧ��ȼ�ϲ�������ˮ��Ħ����
        MFrWSb = 18.015 * MoWSb;//ÿǧ��ȼ�ϲ�������ˮ��������
        MoFrCaS = MFrSb * 32.064 * (CaCO3 * MFrSb / 100.098 + CaO2H2 / 74.096) / MpSF;
        return MFrSc;
    }

    /**
     * �ٶ�Cb��֪���������շݶ�ļ��㣬�ú���ͨ������getMFrScDry,�������ʵļ���Ƕ��
     */
    public double getMoFrClhCc(ASMEGas gas, ASMEResidue[] rs, doubel MrSb, double MrF) {
        double MoFrClhCc = 0.95;// ����һ�����շݶ�ĳ�ֵ
        double e = 0.001;
        do {
            getMFrScDry(gas, rs, MrSb, MrF, MoFrClhCc);
            MFrRs = (c.MpAsF + 100 * getMFrSsb()) / (100 - MnMpUbC);
            double MpCO2Rs = ASMEResidue.getMnMpCO2Rs(rs);
            double MFrClhCcNew = (1 - MFrRs * MpCO2Rs * 100 / 44.01 / MFrSb / CaCO3);
            e = Math.abs(MoFrClhCcNew - MoFrClhCc);
            MoFrClCc = 0.5 * (MoFrClhCcNew + MoFrClhCc);
        } while (e > 0.001);
        return MoFrClhCc;
    }

    /**
     * ��ʪ�����ɷֵȲ������������ʵļ��㣬ԭ��Ŀ�ķ�����ɻ�һ��
     */
    public double getMFrScWet(ASMEGas gas, ASMEResidue[] rs, double MrSb, double MrF) {
        MoFrClhCc = 0.95;
        ASMECoal c = gas.getCoal();
        ASMEWetAir a = gas.getAir();
        if (c.MpCb == 0) return -1;
        MFrSb = MrSb / MrF;
        MFrSb = MFrSb * CaCO3 / 100;
        MFrMcSb = MFrSb * MgCO3 / 100;
        MFrChSb = MFrSb * CaO2H2 / 100;
        MFrMhSb = MFrSb * MgO2H2 / 100;
        MFrWSb = MFrSb * Moist / 100;
        double e = 0.001;
        do {
            double MoSO2 = c.MpSF / 3206.4;
            //ÿǧ��ȼ�ϵ��������������ˮ��Ħ����
            MoWSb = MFrWSb / 18.015 + MFrChSb / 74.83 + MFrMhSb / 58.32;
            //����ʪ��mol��ʾ����Ħ��ˮ��/Ħ���ɿ���
            double MoWA = 1.608 * a.getMFrWA();
            double K = 2.387 * (0.7905 + MoWA) - 1.0;
            //���۸ɿ�������Ħ����
            double MoThaPc = (c.MpCb / 1201 + c.MpH2F / 403.2 + c.MpSF / 3206.4 - c.MpO2F / 3200) / 0.2095;
            //���۸���������Ħ����
            MoCO2Sb = MFrCcSb * MoFrClhCc / 100.098 + MFrMcSb / 84.3 + MFrChSb / 74.1 + MFrMhSb / 58.32;
            double MoDPc = c.MpCb / 1201 + c.MpSF / 3206.4 + c.MpN2F / 2801.3 + MoCO2SB;//5.9-12
            //����ʪ��������Ħ����
            double MoWPc = MoDPc + c.MpH2F / 201.6 + c.MoH2OF / 1802 + gas.getMFrWAd() / 18.02 + MoWSb;
            MFrSc = (1 - gas.VpSO2 * (MoWPc + MoThaPc * (0.79 + MoWA)) / 100 * (1 - (1 + MoWA) * gas.VpO2 / 21) /
                    (1 + K * MoSO2 / 100 / (1 - (1 + MoWA) * gas.VpO2 / 21));
            double MoFrClhCcNew = (1 - ASMEResidue.getMnMpCO2Rs(rs) / 44.01 * 100.089 / MFrSb);
            e = Math.abs(MoFrClhCcNew - MoFrClhCc);
            MoFrClhCc = 0.5 * (MoFrClhCcNew + MoFrClhCc);
        } while (e > 0.001);
        double MFrSO3 = 0.025 * MFrSc * c.MpSF;
        MFrSsb = MFrSb - MFrCO2Sb - MFrWSb + MFrSO3;
        MFrCO2Sb = 44.01 * MoCO2Sb;//5.9-4
        MoWSb = MFrWSb / 18.015 + MFrChSb / 74.83 + MFrMhSb / 58.32;//ÿǧ��ȼ�ϲ�����ˮ�ֵ�Ħ����
        MFrWSb = 18.015 * MoWSb;//ÿǧ��ȼ�ϲ�������ˮ��������
        MoFrCaS = MFrSb * 32.064 * (CaCO3 * MFrSb / 100.089 + CaO2H2 / 74.096) / MpSF;
        return MFrSc;
    }

    /**
     * 1kg������ļ���
     */
    public double getH(double tc) {
        double tf = 1.8 * tc - 32;
        double HCc = 0.179 * tf + 0.1128e-3 * tf * tf - 14.45;//5.19-25
        double HW = tf - 77;
        return ((1 - MFrWSb) * HCc + MFrWSb * HW) * 1.05506 / 0.45359237;
    }

}
