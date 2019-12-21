package Utils;

import java.time.YearMonth;

public class Auxiliary
{
    public static int getNumberDaysGivenYear(int year, int month)
    {
        return YearMonth.of(year, month).lengthOfMonth();
    }
    
}
