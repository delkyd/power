package com.hzgzh.balance.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/2/5.
 */
public class VibrationPoint {
    public String name;
    public String unit = "um/s";
    public double vib;
    public double phase;
    public double resVib;
    public double resPhase;
    public ArrayList<InfluenceCoeffient> coeff = new ArrayList<>();
}
