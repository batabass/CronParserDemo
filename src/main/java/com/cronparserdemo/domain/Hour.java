package com.cronparserdemo.domain;

public class Hour extends Parser<Integer>{

    public Hour(String expression) {
       super(expression);
       this.minValue = 0;
       this.maxValue = 23;
   }

   
   
}
