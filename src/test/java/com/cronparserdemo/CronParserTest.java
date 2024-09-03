package com.cronparserdemo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class CronParserTest {

	@Test
    public void testParseExpressionValidCronExpression() throws Exception {
        CronParser parser = new CronParser();
        Map<String, ArrayList<String>> parsedValues = new LinkedHashMap<>();

        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";
        parser.parseExpression(expression, parsedValues);

        assertEquals("0 15 30 45", String.join(" ", parsedValues.get("minute")));
        assertEquals("0", String.join(" ", parsedValues.get("hour")));
        assertEquals("1 15", String.join(" ", parsedValues.get("day of month")));
        assertEquals("1 2 3 4 5", String.join(" ", parsedValues.get("day of week")));
        assertEquals("/usr/bin/find", expression.split(" ")[5]);


		expression = "0 12 10 * 2-6 /bin/backup";
        parser.parseExpression(expression, parsedValues);

        assertEquals("0", String.join(" ", parsedValues.get("minute")));
        assertEquals("12", String.join(" ", parsedValues.get("hour")));
        assertEquals("10", String.join(" ", parsedValues.get("day of month")));
        assertEquals("2 3 4 5 6", String.join(" ", parsedValues.get("day of week")));
        assertEquals("/bin/backup", expression.split(" ")[5]);
    }

	@Test
    public void testParseExpressionInvalidCronExpression() {
        CronParser parser = new CronParser();
        Map<String, ArrayList<String>> parsedValues = new LinkedHashMap<>();

        String expression = "*/15 0 1,15 * /usr/bin/find";

        Exception exception = assertThrows(Exception.class, () -> {
            parser.parseExpression(expression, parsedValues);
        });

        String expectedMessage = "Invalid Cron Expression";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

		String expression2 = "*/90 0 1,15 * /usr/bin/find";
		exception = assertThrows(Exception.class, () -> {
            parser.parseExpression(expression2, parsedValues);
        });

		actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    

}
