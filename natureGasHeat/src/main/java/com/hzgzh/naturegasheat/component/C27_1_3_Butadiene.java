package com.hzgzh.naturegasheat.component;

public class C27_1_3_Butadiene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 54.092;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2540.77;
            case T20:
                return 2541.43;
            case T15:
                return 2542.10;
            case T0:
                return 2544.13;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2408.72;
            case T20:
                return 2408.76;
            case T15:
                return 2408.80;
            case T0:
                return 2408.91;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.966;
            case T15:
                return 0.971;
            case T20:
                return 0.973;

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
