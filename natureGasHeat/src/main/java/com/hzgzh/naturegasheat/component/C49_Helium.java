package com.hzgzh.naturegasheat.component;

public class C49_Helium implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 4.0026;
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
                return 1.0005;
            case T15:
                return 1.0005;
            case T20:
                return 1.0005;

        }
        return -1;
    }

    @Override
    public double sFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.0006;
            case T15:
                return 0.0002;
            case T20:
                return 0;

        }
        return -1;
    }

}
