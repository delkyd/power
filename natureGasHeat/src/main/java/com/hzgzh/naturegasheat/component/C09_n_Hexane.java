package com.hzgzh.naturegasheat.component;

public class C09_n_Hexane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 86.177;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4194.95;
            case T20:
                return 4196.58;
            case T15:
                return 4198.24;
            case T0:
                return 4203.23;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3886.84;
            case T20:
                return 3887.01;
            case T15:
                return 3887.21;
            case T0:
                return 3887.71;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.892;
            case T15:
                return 0.913;
            case T20:
                return 0.919;

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
