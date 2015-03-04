package com.hzgzh.naturegasheat.component;


public class C03_Propane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 44.097;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2219.17;
            case T20:
                return 2220.13;
            case T15:
                return 2221.1;
            case T0:
                return 2224.01;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2043.11;
            case T20:
                return 2043.23;
            case T15:
                return 2043.37;
            case T0:
                return 2043.71;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9789;
            case T15:
                return 0.9821;
            case T20:
                return 0.9834;

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
