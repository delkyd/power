package com.hzgzh.naturegasheat.component;

public class C32_Cyclohexane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 84.161;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3952.96;
            case T20:
                return 3954.47;
            case T15:
                return 3956.02;
            case T0:
                return 3960.67;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3688.86;
            case T20:
                return 3689.13;
            case T15:
                return 3689.42;
            case T0:
                return 3690.23;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.897;
            case T15:
                return 0.918;
            case T20:
                return 0.924;

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
