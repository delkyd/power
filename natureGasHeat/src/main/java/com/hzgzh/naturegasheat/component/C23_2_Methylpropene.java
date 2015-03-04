package com.hzgzh.naturegasheat.component;

public class C23_2_Methylpropene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 56.108;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2700.2;
            case T20:
                return 2701.1;
            case T15:
                return 2702.0;
            case T0:
                return 2704.8;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 2524.1;
            case T20:
                return 2524.2;
            case T15:
                return 2524.3;
            case T0:
                return 2524.5;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.965;
            case T15:
                return 0.971;
            case T20:
                return 0.972;

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
