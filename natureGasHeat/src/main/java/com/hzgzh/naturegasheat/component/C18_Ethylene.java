package com.hzgzh.naturegasheat.component;

public class C18_Ethylene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 28.054;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1411.18;
            case T20:
                return 1411.65;
            case T15:
                return 1412.11;
            case T0:
                return 1413.51;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1323.15;
            case T20:
                return 1323.2;
            case T15:
                return 1323.24;
            case T0:
                return 1323.36;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9925;
            case T15:
                return 0.9936;
            case T20:
                return 0.994;

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
