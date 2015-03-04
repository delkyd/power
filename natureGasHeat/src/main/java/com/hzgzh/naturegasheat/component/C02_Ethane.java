package com.hzgzh.naturegasheat.component;

public class C02_Ethane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 30.070;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1560.69;
            case T20:
                return 1561.41;
            case T15:
                return 1562.14;
            case T0:
                return 1564.34;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T25:
                return 1428.64;
            case T20:
                return 1428.74;
            case T15:
                return 1428.84;
            case T0:
                return 1429.12;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.99;
            case T15:
                return 0.9915;
            case T20:
                return 0.9920;

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
