package com.hzgzh.naturegasheat.component;

public class C37_Ethylbenzene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 106.167;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4607.15;
            case T20:
                return 4608.32;
            case T15:
                return 4609.53;
            case T0:
                return 4613.14;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4387.07;
            case T20:
                return 4387.20;
            case T15:
                return 4387.37;
            case T0:
                return 4387.77;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.764;
            case T15:
                return 0.823;
            case T20:
                return 0.837;

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
