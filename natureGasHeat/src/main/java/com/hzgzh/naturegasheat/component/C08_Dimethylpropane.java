package com.hzgzh.naturegasheat.component;

public class C08_Dimethylpropane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 72.15;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3514.61;
            case T20:
                return 3516.01;
            case T15:
                return 3517.43;
            case T0:
                return 3521.72;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3250.51;
            case T20:
                return 3250.67;
            case T15:
                return 3250.83;
            case T0:
                return 3251.28;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.943;
            case T15:
                return 0.955;
            case T20:
                return 0.959;

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
