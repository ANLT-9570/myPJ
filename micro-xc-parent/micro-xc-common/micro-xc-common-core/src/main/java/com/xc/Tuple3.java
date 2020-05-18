package com.xc;

public class Tuple3<F,S,T> extends Tuple2<F,S> {
    public final T _3;

    public Tuple3(F _1, S _2, T _3) {
        super(_1, _2);
        this._3 = _3;
    }
}
