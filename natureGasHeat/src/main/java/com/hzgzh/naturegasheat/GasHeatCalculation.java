package com.hzgzh.naturegasheat;

import com.hzgzh.naturegasheat.component.Component;

public class GasHeatCalculation {
    final int Ratio_Mole = 0;
    final int Ratio_Vol = 1;
    final double R = 8.314410;
    final double Mair = 28.9626;

    Component[] cps = {new com.hzgzh.naturegasheat.component.C01_Methane(),
            new com.hzgzh.naturegasheat.component.C02_Ethane(),
            new com.hzgzh.naturegasheat.component.C03_Propane(),
            new com.hzgzh.naturegasheat.component.C04_nButane(),
            new com.hzgzh.naturegasheat.component.C05_2_Methylpropane(),
            new com.hzgzh.naturegasheat.component.C06_n_Pentane(),
            new com.hzgzh.naturegasheat.component.C07_2_Methylbutane(),
            new com.hzgzh.naturegasheat.component.C08_Dimethylpropane(),
            new com.hzgzh.naturegasheat.component.C09_n_Hexane(),
            new com.hzgzh.naturegasheat.component.C10_2_Methylpentane(),
            new com.hzgzh.naturegasheat.component.C11_3_Methylpentane(),
            new com.hzgzh.naturegasheat.component.C12_2_2_Dimethylbutane(),
            new com.hzgzh.naturegasheat.component.C13_2_3_Dimethylbutane(),
            new com.hzgzh.naturegasheat.component.C14_n_Heptane(),
            new com.hzgzh.naturegasheat.component.C15_n_Octane(),
            new com.hzgzh.naturegasheat.component.C16_n_Nonane(),
            new com.hzgzh.naturegasheat.component.C17_n_Decane(),
            new com.hzgzh.naturegasheat.component.C18_Ethylene(),
            new com.hzgzh.naturegasheat.component.C19_Propylene(),
            new com.hzgzh.naturegasheat.component.C20_1_Butene(),
            new com.hzgzh.naturegasheat.component.C21_cis_2_Butene(),
            new com.hzgzh.naturegasheat.component.C22_trans_2_Butene(),
            new com.hzgzh.naturegasheat.component.C23_2_Methylpropene(),
            new com.hzgzh.naturegasheat.component.C24_1_Pentene(),
            new com.hzgzh.naturegasheat.component.C25_Propadiene(),
            new com.hzgzh.naturegasheat.component.C26_1_2_Butadiene(),
            new com.hzgzh.naturegasheat.component.C27_1_3_Butadiene(),
            new com.hzgzh.naturegasheat.component.C28_Acetylene(),
            new com.hzgzh.naturegasheat.component.C29_Cyclopentane(),
            new com.hzgzh.naturegasheat.component.C30_Methylcyclopentane(),
            new com.hzgzh.naturegasheat.component.C31_Ethylcyclopentane(),
            new com.hzgzh.naturegasheat.component.C32_Cyclohexane(),
            new com.hzgzh.naturegasheat.component.C33_Methylcyclohexane(),
            new com.hzgzh.naturegasheat.component.C34_Ethylcyclohexane(),
            new com.hzgzh.naturegasheat.component.C35_Benzene(),
            new com.hzgzh.naturegasheat.component.C36_Toluene(),
            new com.hzgzh.naturegasheat.component.C37_Ethylbenzene(),
            new com.hzgzh.naturegasheat.component.C38_o_Xylene(),
            new com.hzgzh.naturegasheat.component.C39_Methanol(),
            new com.hzgzh.naturegasheat.component.C40_Methanethiol(),
            new com.hzgzh.naturegasheat.component.C41_Hydrogen(),
            new com.hzgzh.naturegasheat.component.C42_Water(),
            new com.hzgzh.naturegasheat.component.C43_Hydrogen_Sulfide(),
            new com.hzgzh.naturegasheat.component.C44_Ammonia(),
            new com.hzgzh.naturegasheat.component.C45_Hydrogen_cyanide(),
            new com.hzgzh.naturegasheat.component.C46_Carbon_monoxide(),
            new com.hzgzh.naturegasheat.component.C47_Carbonyl_sulfide(),
            new com.hzgzh.naturegasheat.component.C48_Carbon_disulfide(),
            new com.hzgzh.naturegasheat.component.C49_Helium(),
            new com.hzgzh.naturegasheat.component.C50_Neon(),
            new com.hzgzh.naturegasheat.component.C51_Argon(),
            new com.hzgzh.naturegasheat.component.C52_Nitrogen(),
            new com.hzgzh.naturegasheat.component.C53_Oxygen(),
            new com.hzgzh.naturegasheat.component.C54_Carbon_dioxide(),
            new com.hzgzh.naturegasheat.component.C55_Sulfur_dioxide(),
            new com.hzgzh.naturegasheat.component.C56_Air()
    };

    double Mg = 0; //Ħ������
    double HHV = 0;//������λ������
    double LHV = 0;//������λ������
    double sF = 0; //���
    double[] r_mol;
    double vHHV = 0;//���������λ������
    double vLHV = 0;//���������λ������
    double rHHV = 0;//��ʵ�����λ������
    double rLHV = 0;//��ʵ�����λ������
    double density_rel = 0;//������������ܶ�
    double rDensity_rel = 0;//��ʵ��������ܶ�
    double density = 0;
    double rDensity = 0;
    double Web_Index = 0;//��������Τ��ָ��
    double rWeb_Index = 0;//��ʵ����Τ��ָ��

    public GasHeatCalculation() {
        r_mol = new double[56];
    }

    //ratio ΪĦ������
    public void calc(double[] ratio, double p2, int t1, int t2, int type) {
        if (type == Ratio_Vol)
            vol2mol(ratio, t2);
        else
            for (int i = 0; i < 56; i++)
                r_mol[i] = ratio[i] / 100;

        for (int i = 0; i < 56; i++) {
            Mg += cps[i].getMoleMass() * r_mol[i];
            HHV += cps[i].getHHV(t1) * r_mol[i];
            LHV += cps[i].getLHV(t1) * r_mol[i];
            sF += cps[i].sFactor(t2) * r_mol[i];
        }

        vHHV = HHV * p2 / R / (t2 + 273.15);
        vLHV = LHV * p2 / R / (273.15 + t2);

        rHHV = vHHV / (1 - sF * sF);
        rLHV = vLHV / (1 - sF * sF);

        for (int i = 0; i < 56; i++) {
            density_rel += r_mol[i] * cps[i].getMoleMass() / Mair;
        }
        density = density_rel * Mair * p2 / R / (t2 + 273.15);

        rDensity_rel = density_rel * cps[55].zFactor(t2) / (1 - sF * sF);
        rDensity = density / (1 - sF * sF);
        Web_Index = vHHV / Math.sqrt(density_rel);
        rWeb_Index = vHHV / Math.sqrt(rDensity_rel);

    }

    public double getMolMass() {
        return Mg;
    }

    public double getMolHHV() {
        return HHV;
    }

    public double getMolLHV() {
        return LHV;
    }

    public double getMassHHV() {
        return HHV / Mg;
    }

    public double getMassLHV() {
        return LHV / Mg;
    }

    public double getIdealGasWebbIndex() {
        return Web_Index;
    }

    public double getRealGasWebbIndex() {
        return rWeb_Index;
    }

    public double getIdeaGaslRelativeDensity() {
        return density_rel;
    }

    public double getIdealGasDensity() {
        return density;
    }

    public double getRealGasRelativeDensity() {
        return rDensity_rel;
    }

    public double getRealGasDensity() {
        return rDensity;
    }

    public double getIdealGasVolumeHHV() {
        return vHHV;
    }

    public double getIdealGasVolumeLHV() {
        return vLHV;
    }

    public double getRealGasVolumeHHV() {
        return rHHV;
    }

    public double getRealGasVolumeLHV() {
        return rLHV;
    }

    public void vol2mol(double[] vol_r, int t2) {
        double ztotal = 0;
        for (int i = 0; i < 56; i++)
            ztotal += vol_r[i] / cps[i].zFactor(t2);
        for (int i = 0; i < 56; i++)
            r_mol[i] = vol_r[i] / ztotal;
    }
}
