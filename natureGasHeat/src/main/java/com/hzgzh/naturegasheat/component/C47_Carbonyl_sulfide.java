package com.hzgzh.naturegasheat.component;

public class C47_Carbonyl_sulfide implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 60.076;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 548.23;
            case T20:
                return 548.19;
            case T15:
                return 548.15;
            case T0:
                return 548.01;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 548.23;
            case T20:
                return 548.19;
            case T15:
                return 548.15;
            case T0:
                return 548.01;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.985;
            case T15:
                return 0.987;
            case T20:
                return 0.988;

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
