package com.example.boiler.asme;

import java.io.Serializable;

public class ASMEPTC41998 implements Cloneable, Serializable {
    //���������
    public String name;
    //�ɻҴ�����һ���������������coalһ�����������ⲿ
    public ASMEResidue[] residues;
    //ȼ��ʪ��������������ѹ�������ʪ�ȣ������¶Ȼ��ʪ���¶�
    public ASMEWetAir air;
    //¯������������û���������ʹ��null�������������Ҫ������صĳɷֵȲ���
    public ASMESorbent sorbent;
    //¯���������������(t/h),��ȼ��������t/h),���û���������MrSb�ɲ�������
    public double MrSb = 0, MrF;
    //���������е�����ˮ�֣��紵��������(t/h)
    public double[] MrWAdz;
    //�����¶ȣ��ɷֵ���������
    public double TFgLv, TAEn, O2FgLv, COFgLv, NoxFgLv, SO2FgLv, O2AirHeaterIn;
    //����Ϊ�����м���
    public double TFgLvCr;
    public double MqFgLv;
    public double HFgLvCr;
    public double QpLDFgCr;
    public double HStLvCr;
    public double HWRe = ASMEH.HWTCToKJPerKg(25);
    public double QpLH2F;
    public double QpLWF;
    public double MqWvF;
    public double HWvLvCr;
    public double QpLWvF;
    public double QpLWA;
    public double HHVCRs = 33700;
    public double MpUbC;
    public double QpLUbc;
    public double QpLH2Rs;
    public double MrRs;
    public double MpH2Rs;
    public double HHVH2 = 142120;
    public double DVpCO;
    public double MoDFg;
    public double MwCO = 28.01;
    public double QpLCO;
    public double[] MrPr;
    public double[] TPr;
    public double[] HHVPr;
    public double QpLPr;
    public double HPr;
    public double MqPr;
    public double VpHC = 0;
    public double HHVHC, MwHC;
    public double QpLUbHc;
    public double ApAf = 0;
    public double QpLRs;
    public double HrNOx = 89850;
    public double QpLNOx;
    public double QpLSrc = 0.3;
    public double QpLClh;
    public double QpLWSb;
    public double QrAp = 31.5;
    public double QpLAp;
    //���²���Ϊ��������
    public double HDAEn;
    public double QpBDA;
    public double HWvEnAir;
    public double QpBWA;
    public double TFEn;
    public double HFEn;
    public double QpBF;
    public double QpBSlf;
    public double HrSlf = 15660;
    public double TSbEn;
    public double HSbEn;
    public double QpBSb;
    public double TMnStvEn;
    public double HWvEnAd;
    public double QpBWvAd;
    public double EFF;
    //���������е�ȼ��ú����ASMECoal,���������ⲿ������Լ�һʱ����̫������
    private ASMECoal coal;
    //��������
    private ASMEGas gas = new ASMEGas();

    {
        ASMEPTC41998 test = (ASMEPTC41998) this.clone();
        double newGasT = (airTemDesign * (gasTemIn - TFgLv) + gasTemIn * (TFgLv - TAEn)) / (gasTemIn - TAEn);
        test.TAEn = airTemDesign;
        test.TFgLv = newGasT;
        for (int i = 0; i < test.residues.length; i++)
            if (test.residues[i].name.equals(ASMEResidue.NameValue.flyingAsh))
                test.residues[i].T = newGasT;
        test.calc();
        return test;
    }

    public double calcEF(double MrF) {
        double HHVF = coal.HHVF;
        //��������ʧ
        TFgLvCr = gas.getTFgLvCr(TFgLv, TAEn, O2AirHeaterIn);
        MqDFgLv = gas.MqDFg();
        ASMEGas gasAhEn = (ASMEGas) gas.clone();
        gasAhEn.DVpO2 = O2AirHeaterIn;
        HFgLvCr = gasAhEn.getH(TFgLvCr);//Enthalpy of dry flue gas leaving,excluding leakage(j/kg)
        QpLDFgCr = 100 * MqDFgLv * HFgLvCr;
        //ȼ��ˮ���������ʧ
        HStLvCr = ASMEH.HStTCToKJPerKg(TFgLvCr);//ˮ��������
        QpLH2F = 100 * gas.MqWH2F * (HStLvCr - HWRe);//H2ȼ��ˮ��ʧ
        QpLWF = 100 * gas.MqWF * (HStLvCr - HWRe);//ȼ���е�ˮ����ʧ
        QpLWvF = 100 * ASMEH.HWvTCToKJPerKg(TFgLvCr) * gas.MqWvF;//����ˮ�����������ʧ
        //������ˮ���������ʧ
        QpLWA = 100 * air.getMFrWA() * gas.MqDA * HWvLvCr;
        //δ��ȫȼ����ʧ
        QpLUbc = (coal.MpCF - coal.MpCb) * HHVCRs / HHVF;//������δȼ̼��ɵ���ʧ
        QpLH2Rs = MrRs * MpH2Rs * HHVH2 / MrF / HHVF;//������δȼ������ʧ
        QpLCO = gas.DVpCO * gas.MoDFg * MwCO * 10111 / HHVF;//������CO����ʧ
        QpLUbHc = DVpCO * MoDFg * MwCO * HHVHC / HHVF;//������δȼ̼�⻯�������ʧ
        //ĥú��ʯ��ú�������ʧ
        QpLPr = 0;
        if (MrPr != null) {
            for (int i = 0; i < MrPr.length; i++) {
                HPr = coal.getH(TPr[i]);//Enthalpy of pulverizer rejects
                MqPr = MrPr[i] / HHVF;
                QpLPr += 100 * MqPr * (HHVPr[i] + HPr) / MrF;
            }
        }
        //ʪ�����������ʧ��һ����Ϊ��������ˮ���������ʧQpLRs;��һ����Ϊ��¯����ײ��ķ��������ʧ
        QpRs = 0;
        for (int i = 0; i < residues.length; i++) {
            QpLRs += 100 * residues[i].MpCRs / HHVF * residues[i].getH();
        }
        //�������������豸����ѭ���������������������ʧ��һ��û��
        //NOx��������ɵ���ʧ
        QpLNOx = gas.DVpNOx * gas.MoDFg * HrNOx / HHVF;//�γ�NO�ķ�����
        //����������������ʧ��һ���ͬ�涨Ϊ0.3
        QpLSrc = 0.3;
        //��������պ���ˮ�������ʧ
        if (sorbent.trueObject) {
            QpLClh = (sorbent.MFrCO2Sb * 1782 * sorbent.MoFrClhCc + sorbent.MFrChSb * 1480 + sorbent.MFrMcSb * 1517
                    + sorbent.MFrMhSb * 1455);
            QpLWSb = sorbent.MqWSb * (HStLvCr - HWRe);//�����ˮ���������ʧ
        }
        //��¯��ʪ���صķ��������ʧ
        QpLAp = 3.6 * 100 * QrAp * ApAf / MrF / HHVF;
        //����ϵͳ�ĸɿ�������
        HDAEn = ASMEH.HAirTCToKJPerKg(TAEn);
        QpBDA = 100 * gas.MqDA * HDAEn;
        //����ϵͳ������ˮ�ִ��������
        MFrWA = gas.MqWA;
        HWvEnAir = 100 * gas.MqDA * HWvEnAir * MFrWDA;
        //ȼ�ϴ���������
        HFEn = coal.getH(TFEn);
        QpBF = 100 * HFEn / HHVF;
        //����Ӧ���������
        if (sorbent.trueObject) {
            QpBSlf = sorbent.MFrSc * coal.MpSF / HHVF * HrSlf;
        }
        //������������������͵ĸ����������ĥú���ȣ�ʡ�ԡ�����㣬����ѭ����������¶�ȡ���ͷ����ڣ���Ҫ��������������
        //���ȡ�Կ���Ԥ������ڣ��򲻼�
        //��������������
        QpBSb = 100 * MrSb * sorbent.getH(TSbEn) / HHVF / MrF;
        //����ˮ�ִ��������������ȼ���е�ˮ�����������������봵�������������ʧ
        QpBWvAd = 100 * MqWvF * (ASMEH.HStTCToKJPerKg(TMnStvEn) - HWRe);
        //��¯Ч��
        EFF = 100 + QpBWvAd + QpBSb + QpBXE + QpBSlf + QpBWA + QpBDA - QpLDFgCr - QpLH2F - QpLWA - QpLUbc - QpLH2Rs - QpLCO - QpLPr -
                QpLUbHc - QpLRs - QpLAq - QpLALg - QpLNOx - QpLClh - QpLWSb - QpCw - QpLAc - QpLAp - QpLRyFg;
        return EFF;

    }

    //��¯ȼ�����ĵ�������
    public void calcMrF() {
        //׼��������ΪASMEGAS����gas����ȼ�ϣ�������������������ɷֵ�
        gas.setCoal(coal);
        gas.setAir(air);
        gas.setSorbent(sorbent);
        gas.setMrWAdz(MrWAdz);
        gas.DVpO2 = O2FgLv;
        gas.DVpSO2 = SO2FgLv;
        gas.DVpCO = COFgLv;
        gas.DVpNOx = NOxFgLv;
        //׼�������������¯����Ч���
        double Qr0 = getOr0();
        double e = 0;
        do {
            ASMEResidue.calcMpRs(gas, residues, MrSb, MrF);//����ƽ�����
            gas.calcXpADry();//�����ɷּ���
            EFF = calcEFF(MrF);//��¯Ч�ʼ���
            double MrFNew = 100 * Qr0 / gas.getCoal().HHVF / EFF;
            e = Math.abs(1 - MrFNew / MrF) * 100;
            MrF = 0.5 * (MrFNew + MrF);
        } while (e > 0.2);//������߲�ֵ����0.2%�������¼���

    }

    throw Exception

    /**
     * �����¶ȵ���������GB10184-1988��ȫ��ͬ
     *
     * @param airTemperature
     * @param gasTemIn
     * @return
     */
    public ASMEPTC41998 correctByAirTemperature(double airTemperature, double gasTemIn)

    public ASMEPTC41998 correctedGasTemperature(double gasTemDesign, double gasTemIn) throws Exception {
        ASMEPTC41998 test = (ASMEPTC41998) this.clone();
        double newGasT = (gasTemDesign * (TFgLv - TAEn) + TAEn * (gasTemIn - TFgLv)) / (gasTemIn - TAEn);
        test.TAEn = airTemDesign;
        test.TFgLv = newGasT;
        for (int i = 0; i < test.residues.length; i++)
            if (test.residues[i].name.equals(ASMEResidue.NameValue.flyingAsh))
                test.residues[i].T = newGasT;
        test.calc();
        return test;
    }

}
