package com.hzgzh.naturegasheat.component;

public class C43_Hydrogen_Sulfide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 34.082;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 562.01;
            case T20:
                return 562.19;
            case T15:
                return 562.38;
            case T0:
                return 562.94;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 517.99;
            case T20:
                return 517.97;
            case T15:
                return 517.95;
            case T0:
                return 517.87;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.99;
            case T15:
                return 0.99;
            case T20:
                return 0.99;

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
