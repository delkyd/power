package com.hzgzh.naturegasheat.component;

public class C10_2_Methylpentane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 86.177;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4187.32;
            case T20:
                return 4188.95;
            case T15:
                return 4190.62;
            case T0:
                return 4195.61;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3879.21;
            case T20:
                return 3879.38;
            case T15:
                return 3879.59;
            case T0:
                return 3880.09;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.898;
            case T15:
                return 0.914;
            case T20:
                return 0.926;

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
