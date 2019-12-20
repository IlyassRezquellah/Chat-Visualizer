package algoritmos;

import algoritmos.matrioshka.*;
import java.util.HashMap;
import users.data.Message;

public class AlgoritomoCMT
{    
    HashMap<Integer, Year> yearTree;
    private int actualYear;
    public AlgoritomoCMT()
    {
        this.yearTree = new HashMap<Integer, Year>();
        actualYear = 0;
    }
    public void createNewYear(int year)
    {//9013
        this.yearTree.put(year, new Year(year));
    }
    
    public boolean calculate(Message mess)
    {
        int charCount = 0;
        int wordCount = 0;
        try
        {
            if(mess.getAno() != actualYear)
            {
                actualYear = mess.getAno();
                createNewYear(actualYear);
            }
            
            //Algoritmo = 3 (mensajes, caracteres, palablras)
            //Calculo medias
            yearTree.get(actualYear).addCount(mess, 3, 4);
        } 
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }       
        return true;
    }   
}
