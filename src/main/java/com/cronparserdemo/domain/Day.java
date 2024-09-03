package com.cronparserdemo.domain;

public class Day extends Parser<Integer>{

    public Day(String expression) {
       super(expression);
       this.minValue = 1;
       this.maxValue = 31;
   }

   
   
}
