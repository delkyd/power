package com.hzgzh.naturegasheat.component;

public interface Component {
    final static int T0 = 0;
    final static int T15 = 15;
    final static int T20 = 20;
    final static int T25 = 25;


    public double getMoleMass();

    public double getHHV(int temp);

    public double getLHV(int temp);

    public double zFactor(int temp);

    public double sFactor(int temp);
}
