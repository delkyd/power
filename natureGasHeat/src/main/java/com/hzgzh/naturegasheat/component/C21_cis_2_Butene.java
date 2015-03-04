package com.hzgzh.naturegasheat.component;

public class C21_cis_2_Butene implements Component {

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
                return 2710.0;
            case T20:
                return 2711.0;
            case T15:
                return 2711.9;
            case T0:
                return 2714.9;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2533.9;
            case T20:
                return 2534.1;
            case T15:
                return 2534.2;
            case T0:
                return 2534.6;
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
                return 0.967;
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
