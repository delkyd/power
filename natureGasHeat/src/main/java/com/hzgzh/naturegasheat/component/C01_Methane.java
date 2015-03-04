package com.hzgzh.naturegasheat.component;

public class C01_Methane implements Component {

    @Override
    public double getMoleMass() {
        // TODO Auto-generated method stub
        return 16.043;
    }

    @Override
    public double getHHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 892.97;
            case T15:
                return 891.56;
            case T20:
                return 891.09;
            case T25:
                return 890.63;
        }
        return -1;
    }

    @Override
    public double getLHV(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 802.82;
            case T15:
                return 802.69;
            case T20:
                return 802.65;
            case T25:
                return 882.60;
        }
        return -1;
    }

    @Override
    public double zFactor(int temp) {
        // TODO Auto-generated method stub
        switch (temp) {
            case T0:
                return 0.9976;
            case T15:
                return 0.998;
            case T20:
                return 0.9981;

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
