package com.hzgzh.naturegasheat.component;

public class C39_Methanol implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 32.042;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 764.09;
            case T20:
                return 764.59;
            case T15:
                return 765.09;
            case T0:
                return 766.59;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 676.06;
            case T20:
                return 676.14;
            case T15:
                return 676.22;
            case T0:
                return 676.44;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.773;
            case T15:
                return 0.872;
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
