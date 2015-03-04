package com.example.boiler.asme;

import java.io.Serializable;

public class ASMECoal implements Serializable, Cloneable {
    //ú�ɷ��뷢����������MpCb,��ʾȼ�յ�ú������ASME��׼���ű�ʾ
    public double MpCF, MpH2F, MpSF, MpN2F, MpO2F, MpH2OF, MpAsF, MpCb, HHVF;

    public ASMECoal(double MpCF, double MPH2F, double MpSF, double MpO2F, double MpH2OF, double MpAsF, double MpCb, double HHVF) {

    }

    //������ȼ̼ռȼ�ϵ���
    public void calcMpCb(ASMEResidue[] r) {
        MpCb = MpCF - r[0].getMnMpUbC();
    }

    //����ȼ����
    public double calcMpCbo() {
        return MpCb / MpCF * 100;
    }
    //�����ۿ������������ֲ�ͬʵ�֣�������ͬҪ�����
    //1.��Fr��Ϊ�б�־�ı�ʾ�Ե�λ����ȼ��Ϊ��׼����λkg/kg;
    /**
     * 2��q����ǵı�ʾ�Ը�λ����Ϊ��׼����λkg/kj
     * 3��MoΪ��ǵı�ʾ��1molȼ��Ϊ��׼����λΪkg/mol
     */

    /**
     * ��ȫȼ��ʱ�ô˺��������ͣ���ȼ��
     */
    public double getMFrThA() {
        return MpCF * 0.1151 + MpH2F * 0.3430 + MpSF * 0.0431 - MpO2F * 0.0432;
    }

    public double getMqThA() {
        return getMFrThA() / HHVF;
    }

    public double getMoThACr() {
        return getMFrThACr(MFrSc) / 28.963;
    }

    public double getMqThACr() {
        return getMFrThACr(MFrSc) / HHVF;
    }

    public double getMFrThACr() {
        return MpCb * 0.1151 + MpH2F * 0.3430 + MpSF * 0.0431 - MpO2F * 0.0432;
    }

    /**
     * ������δ��ȫȼ��ʱ�ô˺���
     */
    public double getMFrThACr(double MFrSc) {
        return MpCb * 0.1151 + MpH2F * 0.3430 + MpSF * 0.0431 * (1 + 0.5 * MFrSc) - MpO2F * 0.0432;
    }

    public double getMqThACr(double MFrSc) {
        return getMFrThACr(MFrSc) / HHVF;
    }

    public double getMOThACr(double MFrSc) {
        return getMFrThaCr(MFrSc) / 28.963;
    }

    /**
     * �Ѷ��ݵ�λ����ת��Ϊ��ѹ��λ����
     */
    public double getHHVFFromHHVFcv(double HHVFcv) {
        return HHVFcv + 2.6 * MpH2F;
    }

    /**
     * ú�ı���
     */
    public double getH(double tc) {
        double tf = 1.8 * tc + 32;
        double MFrVm2, MFrVm1 = 0;
        double HFc = 0.153 * tf + 1.95e-4 * tf * tf - 12.860;//�̶�̼����
        double HVm1 = 0.38 * tf + 2.25e-4 * tf * tf - 30.594;//һ��ӷ��ֵ���
        double HVm2 = 0.7 * tf + 1.7e-4 * tf * tf - 54.908;//����ӷ��ֵ���
        double HRs = 0.17 * tf + 0.8e-4 * tf * tf - 13.564;//��������
        double HW = tf - 77;//ú��ˮ����
        double MFrVm = coal.getV() / 100;
        double MFrVmCr = MFrVm / (1 - MpAsF / 100 - MpH2OF / 100);//�ӷ��ֵ���
        if (MFrVmCr <= 0.1)
            MFrVm2 = MFrVm;//����úʱһ��ӷ���Ϊ0
        else
            MFrVm2 = 0.1 * (1 - MpAsF / 100 - MpH2OF / 100);
        MFrVm1 = MFrVm - MFrVm2;
        return (coal.getFC() * HFc / 100 + MFrVm1 * HVm1 + MFrVm2 * HVm2 + MpH2OF * HW / 100 + MpAsF * HRs / 100)
                * 1.05506 / 0.45359237;


    }
}
