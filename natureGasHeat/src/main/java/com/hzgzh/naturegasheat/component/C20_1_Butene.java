package com.hzgzh.naturegasheat.component;

public class C20_1_Butene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 56.108;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2716.82;
            case T20:
                return 2717.75;
            case T15:
                return 2718.70;
            case T0:
                return 2721.55;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2540.76;
            case T20:
                return 2540.86;
            case T15:
                return 2540.97;
            case T0:
                return 2541.25;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.965;
            case T15:
                return 0.970;
            case T20:
                return 0.972;

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
