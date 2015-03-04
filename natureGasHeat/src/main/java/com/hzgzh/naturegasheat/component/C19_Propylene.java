package com.hzgzh.naturegasheat.component;

public class C19_Propylene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 042.081;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2058.02;
            case T20:
                return 2058.72;
            case T15:
                return 2059.43;
            case T0:
                return 2061.57;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1925.97;
            case T20:
                return 1926.05;
            case T15:
                return 1926.13;
            case T0:
                return 1926.35;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.981;
            case T15:
                return 0.984;
            case T20:
                return 0.985;

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
