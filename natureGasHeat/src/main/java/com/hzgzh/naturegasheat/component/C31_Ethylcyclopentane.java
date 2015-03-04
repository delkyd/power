package com.hzgzh.naturegasheat.component;

public class C31_Ethylcyclopentane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 98.188;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4628.47;
            case T20:
                return 4630.19;
            case T15:
                return 4631.95;
            case T0:
                return 4637.27;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4320.36;
            case T20:
                return 4320.63;
            case T15:
                return 4320.92;
            case T0:
                return 4321.75;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.841;
            case T15:
                return 0.876;
            case T20:
                return 0.885;

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
