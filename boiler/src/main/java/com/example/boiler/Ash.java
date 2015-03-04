package com.example.boiler;

import java.io.Serializable;

public class Ash implements Cloneable, Serializable {
    /**
     * C	-	¯�����ɻң������ң�©ú��̼��������
     * alfa	-	¯���л���ռȼú�ܻ��������ٷֱ�
     * T	-	�¶�
     */
    public double C, alfa, T;
    String name;

    public static double getQ4(Ash[] ash, double A) {
        double Q4 = 0;
        for (int i = 0; i < ash.length; i++)
            Q4 = Q4 + 337.73 * ash[i].C * ash[i].fraction / (100 - ash[i].C);
        return Q4 / A;
    }

    public static double getQ6(Ash[] ash, double A, double airT) {
        double Q6 = 0;
        for (int i = 0; i < ash.length; i++)
            Q6 = Q6 + Cp.Ash(ash[i].T) * (ash[i].T - airT) * ash[i].fraction / (100 - ash[i].C);
        return Q6 / A;
    }
}
