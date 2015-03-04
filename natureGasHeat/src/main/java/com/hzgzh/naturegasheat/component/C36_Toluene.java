package com.hzgzh.naturegasheat.component;

public class C36_Toluene implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 92.141;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3947.89;
            case T20:
                return 3948.84;
            case T15:
                return 3949.81;
            case T0:
                return 3952.72;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 3771.83;
            case T20:
                return 3771.95;
            case T15:
                return 3772.08;
            case T0:
                return 3772.42;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.849;
            case T15:
                return 0.883;
            case T20:
                return 0.892;

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
