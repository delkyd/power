package com.hzgzh.naturegasheat.component;

public class C48_Carbon_disulfide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 76.143;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1104.49;
            case T20:
                return 1104.41;
            case T15:
                return 1104.32;
            case T0:
                return 1104.06;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1104.49;
            case T20:
                return 1104.41;
            case T15:
                return 1104.32;
            case T0:
                return 1104.06;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.935;
            case T15:
                return 0.947;
            case T20:
                return 0.950;

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
