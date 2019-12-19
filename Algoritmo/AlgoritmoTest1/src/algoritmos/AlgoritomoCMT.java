package algoritmos;

import java.time.YearMonth;
import usuarios.datos.Mensaje;

public class AlgoritomoCMT
{//Hay que hacer todo... ArrayList? estos ints no servirán para nada así! Clases autogestionadas?
    private Mensaje message;
    
    public AlgoritomoCMT(Mensaje mensajesOriginales)
    {
        message = null;
    }
    
    public boolean calculate()
    {
        try
        {
            //do somthing
            //Algoritmo = 3 (mensajes, caracteres palablras)
            //MTree
            //Calculo medias
        } 
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }       
        return true;
    }
    public static void getNumberDaysGivenYear(){
        YearMonth yearMonthObject = YearMonth.of(2019, 02);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        System.out.println(daysInMonth);
    }
    
    
}
