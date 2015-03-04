package com.hzgzh.naturegasheat.component;

public class C44_Ammonia implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 17.0306;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 382.81;
            case T20:
                return 383.16;
            case T15:
                return 383.51;
            case T0:
                return 384.57;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 316.79;
            case T20:
                return 316.82;
            case T15:
                return 316.86;
            case T0:
                return 316.96;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.985;
            case T15:
                return 0.988;
            case T20:
                return 0.989;

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
