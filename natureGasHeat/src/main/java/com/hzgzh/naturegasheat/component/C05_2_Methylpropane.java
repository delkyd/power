package com.hzgzh.naturegasheat.component;

public class C05_2_Methylpropane
        implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 58.123;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2868.20;
            case T20:
                return 2869.38;
            case T15:
                return 2870.58;
            case T0:
                return 2874.20;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2648.12;
            case T20:
                return 2648.26;
            case T15:
                return 2648.42;
            case T0:
                return 2648.83;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.958;
            case T15:
                return 0.968;
            case T20:
                return 0.971;

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
