package com.hzgzh.naturegasheat.component;

public class C30_Methylcyclopentane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 84.761;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3969.44;
            case T20:
                return 3970.93;
            case T15:
                return 3972.46;
            case T0:
                return 3977.04;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3705.34;
            case T20:
                return 3705.59;
            case T15:
                return 3705.86;
            case T0:
                return 3706.60;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.902;
            case T15:
                return 0.921;
            case T20:
                return 0.927;

        }
        return -1;
    }

    @Override
    public double sFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return Math.sqrt(1 - zFactor(T0));
            case T15:
                return Math.sqrt(1 - zFactor(T15));
            case T20:
                return Math.sqrt(1 - zFactor(T20));

        }
        return -1;
    }
}
