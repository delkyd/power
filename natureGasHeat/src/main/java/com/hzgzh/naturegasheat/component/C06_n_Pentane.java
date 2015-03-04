package com.hzgzh.naturegasheat.component;

public class C06_n_Pentane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 72.15;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3535.77;
            case T20:
                return 3537.17;
            case T15:
                return 3538.6;
            case T0:
                return 3542.89;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3271.67;
            case T20:
                return 3271.83;
            case T15:
                return 3272;
            case T0:
                return 3272.45;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.918;
            case T15:
                return 0.937;
            case T20:
                return 0.945;

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
