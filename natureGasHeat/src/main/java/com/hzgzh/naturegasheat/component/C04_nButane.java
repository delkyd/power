package com.hzgzh.naturegasheat.component;

public class C04_nButane implements Component {

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
                return 2877.40;
            case T20:
                return 2878.57;
            case T15:
                return 2879.76;
            case T0:
                return 2883.82;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2657.32;
            case T20:
                return 2657.45;
            case T15:
                return 2657.60;
            case T0:
                return 2658.45;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9572;
            case T15:
                return 0.965;
            case T20:
                return 0.9682;

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
