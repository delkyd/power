package com.hzgzh.naturegasheat.component;

public class C07_2_Methylbutane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 72.15;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3528.83;
            case T20:
                return 3530.24;
            case T15:
                return 3531.68;
            case T0:
                return 3535.98;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3264.73;
            case T20:
                return 3264.89;
            case T15:
                return 3265.08;
            case T0:
                return 3265.54;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9377;
            case T15:
                return 0.948;
            case T20:
                return 0.953;

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
