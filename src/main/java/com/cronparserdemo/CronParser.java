package com.cronparserdemo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cronparserdemo.domain.*;

import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class CronParser {

	public void parseExpression(String expression,Map<String,ArrayList<String>> parsedValues) throws Exception
    {

        String partsOfCronExpression[] = expression.split(" ");
        if(partsOfCronExpression.length<6)
        {
            throw new Exception("Invalid Cron Expression."+expression+" Expression should be of form <Minutes HOURS DAY_OF_MONTH MONTH DAY_OF_WEEK (Command)> . Please enter valid cron expression");
        }

        String minute = partsOfCronExpression[0];
        String hour = partsOfCronExpression[1];
        String dayOfMonth = partsOfCronExpression[2];
        String month = partsOfCronExpression[3];
        String dayOfWeek = partsOfCronExpression[4];
        String command = partsOfCronExpression[5];
        parsedValues.put("minute",new Minute(minute).parse("Minute"));
        parsedValues.put("hour",new Hour(hour).parse("Hour"));
        parsedValues.put("day of month",new Day(dayOfMonth).parse("Day"));
        parsedValues.put("month",new Month(month).parse("Month"));
        parsedValues.put("day of week",new Weekday(dayOfWeek).parse("Weekday"));
       

        for(Map.Entry<String,ArrayList<String>> entry: parsedValues.entrySet())
        {
            System.out.print(String.format("%-14s",entry.getKey()));
            for(String str:entry.getValue())
                System.out.print(str+" ");
            System.out.println();
        }
        System.out.print(String.format("%-14s","command"));
        System.out.println(partsOfCronExpression[5]);

       
       

    }

    public static void main(String args[]) throws Exception
    {
        CronParser parser = new CronParser();
        String expression=args[0];
        Map<String,ArrayList<String>> parsedValues = new LinkedHashMap<>();
        parser.parseExpression(expression,parsedValues);
        



    }

}
