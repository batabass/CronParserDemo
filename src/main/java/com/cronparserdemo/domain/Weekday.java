package com.cronparserdemo.domain;

public class Weekday extends Parser<Integer>{

    public Weekday(String expression) {
       super(expression);
       this.minValue = 0;
       this.maxValue = 6;
   }

   
   
}
