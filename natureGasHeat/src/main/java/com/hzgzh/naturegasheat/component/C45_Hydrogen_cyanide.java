package com.hzgzh.naturegasheat.component;

public class C45_Hydrogen_cyanide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 27.026;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 671.5;
            case T20:
                return 671.6;
            case T15:
                return 671.7;
            case T0:
                return 671.9;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 649.5;
            case T20:
                return 649.5;
            case T15:
                return 649.5;
            case T0:
                return 649.4;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.887;
            case T15:
                return 0.912;
            case T20:
                return 0.920;

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
