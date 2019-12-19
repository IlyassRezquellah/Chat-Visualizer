package algoritmos;

import java.util.ArrayList;
import java.util.List;
import usuarios.Persona;
import usuarios.datos.Mensaje;
//Algoritmo de conteo, medias y totales. Pero CMT mola más!
public class AlgoritomoCMT
{//Hay que hacer todo... ArrayList? estos ints no servirán para nada así! Clases autogestionadas?
    private Mensaje mensajes;
    
    public AlgoritomoCMT(Mensaje mensajesOriginales)
    {
        mensajes = null;
    }
    
    public boolean calcular()
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
