package com.hzgzh.naturegasheat.component;

public class C40_Methanethiol implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 48.109;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1239.39;
            case T20:
                return 1239.83;
            case T15:
                return 1240.28;
            case T0:
                return 1241.63;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1151.36;
            case T20:
                return 1151.39;
            case T15:
                return 1151.41;
            case T0:
                return 1151.48;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.972;
            case T15:
                return 0.977;
            case T20:
                return 0.978;

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
