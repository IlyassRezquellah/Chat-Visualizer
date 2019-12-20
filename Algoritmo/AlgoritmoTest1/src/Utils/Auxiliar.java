package Utils;

import java.time.YearMonth;

public class Auxiliar
{
    public static int getNumberDaysGivenYear(int year, int month)
    {
        return YearMonth.of(year, month).lengthOfMonth();
    }
    /*public static void getNumberDaysGivenYear(int year, int month)
    {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        System.out.println(daysInMonth);
    }*/
}
