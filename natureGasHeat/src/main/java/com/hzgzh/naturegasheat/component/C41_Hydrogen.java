package com.hzgzh.naturegasheat.component;

public class C41_Hydrogen implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 2.0159;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 285.83;
            case T20:
                return 285.99;
            case T15:
                return 286.15;
            case T0:
                return 286.63;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 241.81;
            case T20:
                return 241.76;
            case T15:
                return 241.72;
            case T0:
                return 241.56;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 1.0006;
            case T15:
                return 1.0006;
            case T20:
                return 1.0006;

        }
        return -1;
    }

    @Override
    public double sFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return -0.004;
            case T15:
                return -0.0048;
            case T20:
                return -0.0051;

        }
        return -1;
    }
}
