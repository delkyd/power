package com.example.boiler;

import java.io.Serializable;

public class Coal implements Serializable, Cloneable {

    /**
     * Cf,Cy-̼�ķ�������Ӧ�û���������
     * Hf,Hy-��
     * Sf,Sy-��
     * Of,Oy-��
     * Nf,Ny-��
     * Af,Ay-��
     * Wf,Wy-ˮ��
     * Ag-ȼ�ϸ�����ҷ�
     * Cy_r-ʵ��ȼ�յ���̼��Ӧ�û�̼��ȥ�����е�̼
     */
    public double Cf, Cy, Hf, Hy, Sf, Sy, Of, Oy, Nf, Ny, Af, Ay, Wf, Wy, Ag, Cy_r;
    /**
     * Vr-ȼ�Ͽ�ȼ���ӷ���
     */
    public double Vr;

    /**
     * alfa_lz	-	¯���л���ռȼú�ܻ��������ٷֱ�
     * alfa_fh	-	�ɻ�	�л���ռȼú�ܻ��������ٷֱ�
     * alfa_cjh	-	�������л���ռȼú�ܻ��������ٷֱ�
     * alfa_lm	-	©ú�л���ռȼú�ܻ��������ٷֱ�
     * Cc_lz	-	¯���к�̼�� %
     * Cc_fh	-	�ɻ��к�̼��%
     * Cc_cjh	-	�������к�̼�� %
     * Cc_lm	-	©ú�к�̼�� %
     */
    public double alfa_lz, alfa_fh, alfa_cjh, a_lm, Cc_lz, Cc_fh, Cc_cjh, Cc_lm;


    public double c, h, o, n, s;
    public double q, v, fc;
    /**
     * Mar:�յ���ˮ��%
     */
    public double Mar;
    /**
     * Mad:�����ˮ��%
     */
    public double Mad;
    /**
     * Aar:�յ����ҷ�%
     */
    public double Aar;
    /**
     * Aad:������ҷ� Aad
     */
    public double Aad;

    public String name;
    public String base = "�յ���";
    /**
     * δȼ̼
     */
    public double cUb;
    /**
     *��1���յ���,��ʾ����ar(as received)
     //(2) ���������,ad,�����ʪ�ȴﵽƽ��״̬,��ʧȥ��ˮ�ֵ�úΪ��׼
     //��3�������,ˮ��ȫ��ʧȥ���Լ�����ˮ״̬��úΪ��׼����ʾ����Ϊd(dry basis)
     //4.�����޻һ�,�Լ�����ˮ�޻�״̬��úΪ��׼����ʾ����Ϊdaf(dry ash fress)�����ڲ���ˮ�֣��ҷ�Ӱ�죬���
     //�����ڱȽ�����ú�е�̼���⣬����������ɷֺ���.
     */


    /**
     * Mar:ú���յ���ˮ�� %
     * Mad:ú�п��������ˮ��%
     * /**
     * ���Ž��з�ʽ���㻯ѧ��������׼ȷ�ԣ���׼Ϊ�յ���
     *
     * @return
     */
    public boolean CheckQdw() {
        Coal tc = this.ChangeBase(this.base);
        double QdwFromElements = 339 * tc.c + 1028 * tc.h + 109 * tc.s - 109 * tc.o;
        if (Math.abs(tc.q - QdwFromElements) >= 800)
            return false;
        return true;
    }

    public void report() {
    }

    public Coal ChangeBase(String base) {
        double mcs2a = 1;
        Coal newc = (Coal) this.clone();
        if (base.equals("�յ���")) mcs2a = 1;
        if (base.equals("���������")) mcs2a = (100 - Mad) / (100 - Mad - Aad);
        if (base.equals("�����")) mcs2a = (100 - Mar) / 100;
        if (base.equals("�����޻һ�")) mcs2a = (100 - Mar - Aar) / 100;
        newc.a = mcs2a * mca2d * a;
        newc.c = mcs2a * mca2d * c;
        newc.v = mcs2a * mca2d * v;
        newc.base = base;
        return newc;
    }

    public Object clone() {
        try {
            return (Coal) super.clone();
        } catch (Exception e) {

        }
        return null;
    }

    public boolean check() {
        return c + h + o + n + s + a + m - 100 < 0.0001;
    }

    public double getDryAirVolume() {
        return (0.01866 * (c - cUb) + 0.0556 * h + 0.007 * s - 0.007 * o) / 0.21;
    }

    public double getDryGasVolume() {
        return getRO2Volume() + getN2Volume();
    }

    public double getRO2Volume() {
        return 0.01866 * (c - cUb + 0.375 * s);
    }

    public double getCO2Volume() {
        return 0.01866 * (c - cUb);
    }

    public double getSO2Volume() {
        return 0.79 * getDryAirVolume() + 0.008 * n;
    }

    public double getN2Volume() {
        return 0.79 * getDryAirVolume() + 0.008 * n;
    }

    public double getH2OVolume() {
        return 1.24 * (9 * h + Mar) / 100 + 0.0161 * getDryGasVolume();
    }

    public double getH2OVolumeFromAir() {
        return 0.0161 * getDryGasVolume();
    }

    /**
     * ������δȼ��̼����
     *
     * @param ash ����
     */
    public double getUnBurnedCarbon(Ash[] ash) {
        cUb = 0;
        for (int i = 0; i < ash.length; i++)
            cUb = cUb + ash[i].C * ash[i].alfa / (100 - ash[i].C);
        return Ay * cUb;
    }

    public double getBurndCarbon(Ash[] ash) {
        Cy_r = Cy - getUnBurnedCarbon(ash);
        return Cy_r;
    }

    /**
     * @return V0_gk-��Ӧ�û�ȼ�ϳɷ֣���ʵ��ȼ�յ���̼���������ȼ������ĸɿ�������m3/kg
     */
    public double getTheoryAirVolume() {
        return 0.089 * (Cy_r + 0.375 * Sy) + 0.265 * Hy - 0.0333 * Oy;
    }
}
