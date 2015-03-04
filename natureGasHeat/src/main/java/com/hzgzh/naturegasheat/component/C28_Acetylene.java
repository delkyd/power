package com.hzgzh.naturegasheat.component;

public class C28_Acetylene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 26.038;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1301.05;
            case T20:
                return 1301.21;
            case T15:
                return 1301.37;
            case T0:
                return 1301.86;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1257.03;
            case T20:
                return 1256.98;
            case T15:
                return 1256.94;
            case T0:
                return 1256.79;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.99;
            case T15:
                return 0.993;
            case T20:
                return 0.993;

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
