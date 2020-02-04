package Utils;

import java.time.YearMonth;

public class Auxiliary{
    public static String chatPath = "../";
    //Este metodo nos devuelve un int con el número total de día en un mes, mediante un año y un mes.
    public static int getNumberDaysGivenYear(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }
    
}
