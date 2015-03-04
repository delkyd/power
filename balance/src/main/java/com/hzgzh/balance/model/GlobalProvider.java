package com.hzgzh.balance.model;

/**
 * Created by Administrator on 2015/2/7.
 */
public final class GlobalProvider {
    private final static Global global = new Global();

    public static Global getInstance() {
        return global;
    }


}
