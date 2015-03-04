package com.hzgzh.naturegasheat.component;

public class C14_n_Heptane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 100.204;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4853.43;
            case T20:
                return 4855.29;
            case T15:
                return 4857.18;
            case T0:
                return 4862.87;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4501.3;
            case T20:
                return 4501.49;
            case T15:
                return 4501.72;
            case T0:
                return 4502.28;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.83;
            case T15:
                return 0.866;
            case T20:
                return 0.876;

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
