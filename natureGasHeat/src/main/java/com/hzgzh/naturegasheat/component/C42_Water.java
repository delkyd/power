package com.hzgzh.naturegasheat.component;

public class C42_Water implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 18.0153;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 44.016;
            case T20:
                return 44.224;
            case T15:
                return 44.433;
            case T0:
                return 45.074;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 0;
            case T20:
                return 0;
            case T15:
                return 0;
            case T0:
                return 0;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.93;
            case T15:
                return 0.945;
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
