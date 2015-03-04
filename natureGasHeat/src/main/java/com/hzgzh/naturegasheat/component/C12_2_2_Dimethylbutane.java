package com.hzgzh.naturegasheat.component;

public class C12_2_2_Dimethylbutane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 86.177;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4177.52;
            case T20:
                return 4179.15;
            case T15:
                return 4180.83;
            case T0:
                return 4185.84;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3869.41;
            case T20:
                return 3869.59;
            case T15:
                return 3869.8;
            case T0:
                return 3870.32;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.916;
            case T15:
                return 0.931;
            case T20:
                return 0.935;

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
