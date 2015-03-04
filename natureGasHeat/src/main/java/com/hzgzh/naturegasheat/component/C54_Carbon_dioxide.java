package com.hzgzh.naturegasheat.component;

public class C54_Carbon_dioxide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 44.010;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9933;
            case T15:
                return 0.9944;
            case T20:
                return 0.9944;

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
