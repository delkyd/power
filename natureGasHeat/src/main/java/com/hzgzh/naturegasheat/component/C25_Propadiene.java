package com.hzgzh.naturegasheat.component;

public class C25_Propadiene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 40.065;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1943.11;
            case T20:
                return 1943.53;
            case T15:
                return 1943.96;
            case T0:
                return 1945.25;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1855.08;
            case T20:
                return 1855.08;
            case T15:
                return 1855.09;
            case T0:
                return 1855.10;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.98;
            case T15:
                return 0.983;
            case T20:
                return 0.984;

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
