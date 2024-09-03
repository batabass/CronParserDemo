package com.cronparserdemo.domain;


public class Minute  extends Parser<Integer>{

     public Minute(String expression) {
        super(expression);
        this.minValue = 0;
        this.maxValue = 59;
    }

    
    
}
