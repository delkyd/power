package com.hzgzh.naturegasheat.component;

public class C13_2_3_Dimethylbutane implements Component {

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
                return 4185.28;
            case T20:
                return 4186.93;
            case T15:
                return 4188.6;
            case T0:
                return 4193.63;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3877.17;
            case T20:
                return 3877.36;
            case T15:
                return 3877.57;
            case T0:
                return 3878.11;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.91;
            case T15:
                return 0.925;
            case T20:
                return 0.934;

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
