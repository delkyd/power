package com.hzgzh.naturegasheat.component;

public class C22_trans_2_Butene implements Component {

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
                return 2706.4;
            case T20:
                return 2707.4;
            case T15:
                return 2708.3;
            case T0:
                return 2711.1;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2530.3;
            case T20:
                return 2530.5;
            case T15:
                return 2530.5;
            case T0:
                return 2530.8;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.961;
            case T15:
                return 0.968;
            case T20:
                return 0.969;

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
