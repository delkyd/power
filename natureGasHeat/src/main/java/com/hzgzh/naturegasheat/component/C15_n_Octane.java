package com.hzgzh.naturegasheat.component;

public class C15_n_Octane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 114.231;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 5511.8;
            case T20:
                return 5513.88;
            case T15:
                return 5516.01;
            case T0:
                return 5520.4;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 5115.66;
            case T20:
                return 5115.87;
            case T15:
                return 5116.11;
            case T0:
                return 5116.73;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.742;
            case T15:
                return 0.802;
            case T20:
                return 0.817;

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
