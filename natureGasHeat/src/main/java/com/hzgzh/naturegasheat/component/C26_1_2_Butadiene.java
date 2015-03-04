package com.hzgzh.naturegasheat.component;

public class C26_1_2_Butadiene implements Component {

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
                return 2593.79;
            case T20:
                return 2594.45;
            case T15:
                return 2595.12;
            case T0:
                return 2597.13;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2461.74;
            case T20:
                return 2461.78;
            case T15:
                return 2461.82;
            case T0:
                return 2461.91;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.955;
            case T15:
                return 0.963;
            case T20:
                return 0.965;

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
