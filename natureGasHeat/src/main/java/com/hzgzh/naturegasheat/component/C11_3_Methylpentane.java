package com.hzgzh.naturegasheat.component;

public class C11_3_Methylpentane implements Component {

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
                return 4189.9;
            case T20:
                return 4191.54;
            case T15:
                return 4193.22;
            case T0:
                return 4198.24;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3881.79;
            case T20:
                return 3881.97;
            case T15:
                return 3882.19;
            case T0:
                return 3882.72;
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
                return 0.917;
            case T20:
                return 0.928;

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
