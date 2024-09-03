package com.cronparserdemo.domain;

import java.util.ArrayList;
import java.util.Map;

public class ValidateInputs {
    
    public static boolean validateListForDigits(ArrayList<String> valuesToTest, int minAllowedValue, int maxAllowedValue)
    {
        for(String value: valuesToTest)
        {
            if(Integer.parseInt(value)<minAllowedValue || Integer.parseInt(value) > maxAllowedValue)
                return false;
                
        }

        return true;
    }

    public static boolean validateListForCharacters(ArrayList<String> valuesToTest, ArrayList<String> alllowedValues)
    {
        for(String value: valuesToTest)
        {
            if(!alllowedValues.contains(value))
                return false;
                
        }

        return true;
    }

    public static boolean isTimeInRange(Integer start, Integer end,String value)
    {
        Integer valueInteger = Integer.parseInt(value);
        if(valueInteger>=start && valueInteger<=end)
            return true;
        
        return false;
    }

    public static boolean validateRangeForCharacters(ArrayList<String> valuesToTest, Map<String,Integer> alllowedValues)
    {
        String startValue = valuesToTest.get(0);
        String endValue = valuesToTest.get(1);
       
        if(!alllowedValues.containsKey(startValue) || !alllowedValues.containsKey(endValue) || !(alllowedValues.get(startValue)<alllowedValues.get(endValue)) )
            return false;
        return true;
    }

    public static boolean validateRangeForDigits(ArrayList<String> valuesToTest, int minAllowedValue, int maxAllowedValue)
    {
        int startRange = Integer.parseInt(valuesToTest.get(0));
        int endRange = Integer.parseInt(valuesToTest.get(1));
        if(startRange < minAllowedValue || startRange > maxAllowedValue || endRange < startRange || endRange > maxAllowedValue)
            return false;

        return true;
    }

    public static boolean validateStepsForDigits(String stepString, int minAllowedValue, int maxAllowedValue)
    {
       int steps = Integer.parseInt(stepString);
       if(steps < minAllowedValue || steps > maxAllowedValue)
            return false;

        return true;
    }

    public static boolean validateFixedValues(String value, ArrayList<String> alllowedValues)
    {
        if(!alllowedValues.contains(value))
            return false;
        return true;
    }
}
