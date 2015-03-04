package com.hzgzh.naturegasheat.component;

public class C29_Cyclopentane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 70.134;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3319.59;
            case T20:
                return 3320.88;
            case T15:
                return 3322.19;
            case T0:
                return 3326.14;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3099.51;
            case T20:
                return 3099.76;
            case T15:
                return 3100.03;
            case T0:
                return 3100.77;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.935;
            case T15:
                return 0.947;
            case T20:
                return 0.950;

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
