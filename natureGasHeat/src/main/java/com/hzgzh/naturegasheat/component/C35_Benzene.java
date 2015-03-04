package com.hzgzh.naturegasheat.component;

public class C35_Benzene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 78.114;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3301.43;
            case T20:
                return 3302.15;
            case T15:
                return 3302.86;
            case T0:
                return 3305.03;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3169.38;
            case T20:
                return 3169.48;
            case T15:
                return 3169.56;
            case T0:
                return 3169.81;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.909;
            case T15:
                return 0.926;
            case T20:
                return 0.936;

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
