package com.hzgzh.naturegasheat.component;

public class C16_n_Nonane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 128.258;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 6171.75;
            case T20:
                return 6173.46;
            case T15:
                return 6175.82;
            case T0:
                return 6182.91;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 5730.99;
            case T20:
                return 5731.22;
            case T15:
                return 5731.49;
            case T0:
                return 5732.17;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.613;
            case T15:
                return 0.710;
            case T20:
                return 0.735;

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
