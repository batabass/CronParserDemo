package com.cronparserdemo.domain;

import java.util.*;

public abstract class Parser<T> {

    Integer minValue;
    Integer maxValue;
    String expression;

    public Parser( String expression) {
       
        this.expression = expression;
    }

    public Integer getMinValue() {
        return minValue;
    }
    public Integer getMaxValue() {
        return maxValue;
    }
    public String getExpression() {
        return expression;
    }

    public PATTERN findTypeOfPattern(String expression)
    {
        if(expression.contains("/"))
            return PATTERN.STEPS;
        else if(expression.contains(","))
            return PATTERN.LIST;
        else if(expression.contains("-"))
            return PATTERN.RANGE;
        
        else if(expression.contains("*"))
            return PATTERN.ASTRISK;
        else 
            return PATTERN.FIXED_VALUE;
    }

   
    private ArrayList<String> parseList(String expression)
    {
        ArrayList<String> parsedList = new ArrayList<>();
        String splitAtComma[] = expression.split(",");
        for(String ele : splitAtComma)
        {
            parsedList.add(ele);
        }

        return parsedList;
    }

    private ArrayList<String> parseRange(String expression)
    {
        ArrayList<String> parsedList = new ArrayList<>();
        String splitAtHyphen[] = expression.split("-");
        for(String ele : splitAtHyphen)
        {
            parsedList.add(ele);
        }

        return parsedList;
    }


    public ArrayList<String> parse(String parsedFor) throws Exception
    {
        String expression = this.expression;
        int minValue = this.minValue;
        int maxValue= this.maxValue;

        ArrayList<String> parsedString = new ArrayList<>();

        PATTERN typeOfPattern = findTypeOfPattern(expression);

        switch (typeOfPattern) {

            case ASTRISK:

                for(int mins=minValue;mins<=maxValue;mins++)
                    parsedString.add(String.valueOf(mins));
                
                    break;

            case LIST:

                ArrayList<String> parsedList = parseList(expression);
                
                if(ValidateInputs.validateListForDigits(parsedList,minValue,maxValue))
                    parsedString.addAll(parsedList);
                else
                    throw new Exception("The passed List of "+parsedFor+" is Invalid :"+expression+". "+parsedFor+" should be between "+minValue+" and "+maxValue);
                
                    break;

            case RANGE:

                ArrayList<String> range = parseRange(expression);
                if(ValidateInputs.validateRangeForDigits(range,minValue,maxValue))
                {
                    int startTime = Integer.parseInt(range.get(0));
                    int endTime = Integer.parseInt(range.get(1));
                    for(int mins=startTime;mins <= endTime;mins++)
                        parsedString.add(String.valueOf(mins));

                }
                else
                    throw new Exception("The passed Range is Invalid :"+expression+". It should be between "+minValue+" and "+maxValue);

                    break;
            
            case STEPS:
                
                String steps[] = expression.split("/");
               
                if(steps.length==2 && (steps[0].equals("*") || (steps[0].matches(("\\d+")) && ValidateInputs.isTimeInRange(minValue, maxValue, steps[0]))) && (steps[1].matches(("\\d+")) && ValidateInputs.isTimeInRange(minValue, maxValue, steps[1])))
                {
                    int start = -1;
                    if(!steps[0].equals("*"))
                    {
                        start= Integer.parseInt(steps[0]);
                    }
                    else{
                        start = 0;
                    }
                    int step = Integer.parseInt(steps[1]);
                    for(int time=start;time<=maxValue;time+=step)
                    {
                        parsedString.add(String.valueOf(time));
                    }
                }

                else
                    throw new Exception("The passed Step Expression is Invalid :"+expression+". It should be between "+minValue+" and "+maxValue);

                    break;
                
            default:

                parsedString.add(expression);
                break;
        }

        return parsedString;
        

    }
    
    

    
    
}
