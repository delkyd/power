package com.hzgzh.naturegasheat.component;

public class C33_Methylcyclohexane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 98.188;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4600.64;
            case T20:
                return 4602.35;
            case T15:
                return 4604.09;
            case T0:
                return 4609.34;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 4292.53;
            case T20:
                return 4292.78;
            case T15:
                return 4293.06;
            case T0:
                return 4293.82;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.855;
            case T15:
                return 0.886;
            case T20:
                return 0.894;

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
