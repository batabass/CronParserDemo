package com.cronparserdemo.domain;

public class Month  extends Parser<Integer>{

    public Month(String expression) {
       super(expression);
       this.minValue = 1;
       this.maxValue = 12;
   }

   
   
}
