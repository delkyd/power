package com.hzgzh.naturegasheat.component;

public class C38_o_Xylene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 106.167;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4596.31;
            case T20:
                return 4597.46;
            case T15:
                return 4598.64;
            case T0:
                return 4602.17;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4376.23;
            case T20:
                return 4376.34;
            case T15:
                return 4376.48;
            case T0:
                return 4376.80;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.737;
            case T15:
                return 0.804;
            case T20:
                return 0.821;

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
