package com.hzgzh.naturegasheat.component;

public class C17_n_Decane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 142.285;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 6829.77;
            case T20:
                return 6832.31;
            case T15:
                return 6834.9;
            case T0:
                return 6842.69;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 6345.59;
            case T20:
                return 6345.89;
            case T15:
                return 6346.14;
            case T0:
                return 6346.88;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.434;
            case T15:
                return 0.584;
            case T20:
                return 0.623;

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
