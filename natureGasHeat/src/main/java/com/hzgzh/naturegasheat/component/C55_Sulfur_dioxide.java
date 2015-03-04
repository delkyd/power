package com.hzgzh.naturegasheat.component;

public class C55_Sulfur_dioxide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 64.065;
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
                return 0.976;
            case T15:
                return 0.979;
            case T20:
                return 0.98;

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
