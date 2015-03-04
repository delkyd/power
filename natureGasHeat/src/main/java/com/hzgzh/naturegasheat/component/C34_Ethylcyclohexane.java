package com.hzgzh.naturegasheat.component;

public class C34_Ethylcyclohexane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 112.215;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 5263.05;
            case T20:
                return 5264.98;
            case T15:
                return 5266.95;
            case T0:
                return 5272.88;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4910.92;
            case T20:
                return 4911.19;
            case T15:
                return 4911.49;
            case T0:
                return 4912.29;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.77;
            case T15:
                return 0.824;
            case T20:
                return 0.838;

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
