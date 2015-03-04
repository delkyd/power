package com.hzgzh.naturegasheat.component;

public class C46_Carbon_monoxide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 28.01;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 282.98;
            case T20:
                return 282.95;
            case T15:
                return 282.91;
            case T0:
                return 282.80;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 282.98;
            case T20:
                return 282.95;
            case T15:
                return 282.91;
            case T0:
                return 282.80;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9993;
            case T15:
                return 0.9995;
            case T20:
                return 0.9996;

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
