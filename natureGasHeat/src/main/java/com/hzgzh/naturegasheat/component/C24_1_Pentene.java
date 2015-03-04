package com.hzgzh.naturegasheat.component;

public class C24_1_Pentene implements Component {

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
                return 3375.42;
            case T20:
                return 3376.57;
            case T15:
                return 3377.75;
            case T0:
                return 3381.29;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3155.34;
            case T20:
                return 3155.45;
            case T15:
                return 3155.59;
            case T0:
                return 3155.92;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.938;
            case T15:
                return 0.949;
            case T20:
                return 0.952;

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
