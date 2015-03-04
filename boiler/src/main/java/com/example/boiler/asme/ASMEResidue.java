package com.example.boiler.asme;

public class ASMEResidue {
    /**
     * �����ݶ��ȫ�⣬���ֲ⼰�������֣����³����ȷ�������ݶ���ι���
     */
    static public final int TEST = 100, ESTIMATE = 101, CALC = 102;
    ;
    /**
     * ÿһ�����������ƣ���ɻһ��Ǵ�������������
     */
    public NameValue name;
    /**
     * ÿһ�ݻ����ж����������ؼ��Ĳ��������л�����Եó����ǰ���CO2���ڵ��ܵĺ�̼��MpToCRs�������е�CO2
     * �е�CO2�еĺ�̼��MPCO2RS�������ɵõĲ������¶�T�����ָô�����������MrRs��������Ϊÿһ�ݻ����ķݶ�MpRs
     * δȼ��̼�ĺ���MpCRs.ĳһλ�õĻ���������������������Ļ���������ֵ������Ϊ0.���ֻ��һ����Ϊ0������Ҫ
     * ͨ������ó�ÿһ���ҿڵĻ����ݶ�MpRs
     */
    public double MpCRs, MpToCRs, MpCO2Rs, MpRs, T, MrRs;
    /**
     * ���²�����ȫ¯�������������ԣ�������λ����ȼ���еĻҷ�������MFrRs��kg/kg),ƽ���ɻҺ�̼����ƽ��δȼ��
     * ̼���������������
     */
    public double MFrRs, MnMpCRs, MnMpUbc;

    /**
     * @param gas
     * @param rs
     * @param MrSb
     * @param MrF
     */
    public static getMnMpUbCDry(ASMEGas gas, ASMEResidue[] rs, double MrSb, double MrF) {
        ASMESorbent sb = gas.getSorbent();
        ASMECoal c = gas.getCoal();
        if (!sb.trueObject) {
            double MpCRs = getMnMpCRs(rs);
            c.MpCb = c.MpCF - MpCRs * c.MpAsF / (100 - MpCRs);
            MFrRs = (c.MpAsF + c.MpCF - c.MpCb) / 100;
            return getMnMpCRs(rs) * c.MpAsF;
        }
        double e = 0;
        double MnMpUbC = 2;
        do {
            c.MpCb = c.MpCF - MnMpUbC * MFrRs;
            sb.getMoFrClhCc(gas, rs, MrSb, MrF);
            MFrRs = (c.MpAsF + 100 * sb.getMFrSsb()) / (100 - MnMpUbC);
            double MnMpUbCNew = getMnMpCRs(rs) * MFrRs;
            e = Math.abs(MnMpUbCNew - MnMpUbC);
            MnMpUbC = 0.5 * (MnMpUbC + MnMpUbCNew);

        } while (e > 0.001);
        return MnMpUbC;
    }

    static int MpRsWork(ASMEResidue[] rs) {
        int hasRatioNum = 0;
        for (int i = 0; i < rs.length; i++) {
            if (rs[i].MrRs > 0)
                hasRatioNum++;
        }
        if (rs.length - hasRatioNum > 1)
            return ESTIMATE;
        else if (rs.length - hasRatioNum == 0) {
            double SmMrRs = 0;
            //�ȰѴ�����������ܻ�������Ҫ����Ļҷ�������ҳ�����������X��
            for (int i = 0; i < rs.length; i++) {
                SmMrRs += rs[i].MrRs;
                if (rs[i].MrRs == 0) X = i;
            }
            //������еĻ����ڶ��������������������ǳ����������õ������ݶ�
            for (int i = 0; i < rs.length; i++)
                rs[i].MpRs = rs[i].MrRs / SmMrRs * 100;
            return TEST;
        }
        return CALC;
    }

    /**
     * ��ȡ����ȼ���еĻ����������������������ɻ���ȼ�ϵĻ�������
     */
    static public double getMFrRs(ASMECoal c, ASMESorbent Sb) {
        return MFrRS;
    }

    /**
     * ����ҷ��е�ƽ����̼��
     */
    static public double getMnMpCRs(ASMEResidue[] ashz) {
        double MpCRs = 0;
        for (int i = 0; i < ashz.length; i++)
            MpCRs += ashz[i].getMpCRs() * ashz[i].MpRs / 100;
        return MpCRs;

    }

    /**
     * ����ҷ��е�ƽ��CO2��
     */
    static public double getMnMpCO2Rs(ASMEResidue[] ashz) {
        double MpCO2Rs = 0;
        for (int i = 0; i < ashz.length; i++)
            MpCO2Rs += ashz[i].MpCO2Rs * ashz[i].MpRs / 100;
        return MpCO2Rs;
    }

    /**
     * ���㣬����ҷַݶ
     */
    static public void calcMpRs(ASMEGas gas, ASMEResidue[] rs, double MrSb, double MrF) {
        int X = 0;
        doubl e = 0, SmMrRs = 0;
        for (int i = 0; i < rs.length; i++) {
            SmMrRs += rs[i].MrRs;
            if (rs[i].MrRs == 0) X = i;
        }
        //������еĻ����ڶ��������������������ǳ����������õ������ݶ�
        if (ASMEResidue.MpRsWork(rs) != ASMEResidue.TEST) {
            for (int i = 0; i < rs.length; i++)
                rs[i].MpRs = rs[i].MrRs / SmMrRs * 100;
            return;
        }
        do {
            for (int i = 0; i < rs.length; i++)
                rs[i].MpRs = rs[i].MrRs / SmMrRs * 100;
            Ubc = ASMEResidue.getMnMpUbCDry(gas, rs, MrSb, MrF);
            double SmMrRsNew = MFrRs * MrF;
            e = Math.abs(SmMrRsNew / SmMrRs - 1) * 100;
            SmMrRs = SmMrRsNew;
        } while (e > 0.1);
    }

    /**
     * @param gas
     * @param rs
     * @param MrSb
     * @param MrF
     * @return
     */
    public static double getMnMpUbCWet(ASMEGas gas, ASMEResidue[] rs, double MrSb, double MrF) {
        ASMESorbent sb = gas.getSorbent();
        ASMECoal c = gas.getCoal();
        if (!sb.trueObject) {
            double MpCRs = getMnMpCRs(rs);
            c.MpCb = c.MpCF - MpCRs * c.MpAsF / (100 - MpCRs);
            return getMnMpCRs(rs) * c.MpAsF;
        }
        double e = 0;
        MFrRs = c.MpAsF;
        do {
            c.MpCb = c.MpCF - MnMpUbC * MFrRs;
            sb.getMFrScWet(gas, rs, MrSb, MrF);
            double MnMpUbCNew = getMnMpCRs(rs) * sb.getMFrSb();
            e = Math.abs(MnMpUbCNew - MnMpUbC);
            MnMpUbC = 0.5 * (MnMpUbC + MnMpUbCNew);
            MFrRs = (c.MpAsF + 100 * sb.getMFrSsb()) / (100 - MnMpUbC);
        } while (e > 0.001);
        return MnMpUbC;
    }

    /**
     * ��ĳ������ɻң��Ļ����������㵽��λ���Ȼ��ϣ���λkg/J
     */
    public double getMqRs(ASMECoal c, ASMESorbent Sb) {
        if (!Sb.trueObject)
            return MpRs * c.MpAsF / 100 / c.HHVF;//���������ֱ�ӷ��������MpCRs
        return MpRs * MFrRs / 100 / c.HHVF;
    }

    /**
     * δȼ���ļ��㣬һ�㻯��ɵ��ܺ�̼����Ҳ������δȼ��̼��������������ܺ�̼�������ñ�ʽ����
     */
    public double getMpCRs() {
        if (MpCO2Rs != 0)
            MpCRs = MpToCRs - 12.01 / 44.01 * MpCO2Rs;
        return MpCRs;
    }

    /**
     * ����һϵ�лҷ���Ʒ�ܵ�δȼ̼ռȼ�ϵ�������λ��%������������Ҫ�ķ���֮һ�������ȶ�GAS
     * ��VpO2/VpSO2�����з�ֵ���÷����ֻ����ASMESORBENT�����getMFrScDry���������������
     * �����շݶƽ���ɻҺ�̼���Ķ���Ƕ�׵���
     */

    public double getH(double t) {
        return ASMEH.HAshTCToKJPerKg(t);
    }

    /**
     * ����һϵ�лҷ���Ʒ�ܵ�δȼ̼ռȼ�ϵ�������λ%����getMnMpUbCDry��������ͬ��ֻ��������������ʪ���ĳɷ�
     * ���м��㣬ʹ��ʱ�����ȶ�gas��VpO2/VpSO2�����з�ֵ
     */
    static public enum NameValue {
        flyingAsh, slag, falloutAsh, otherAsh;
    }

}
