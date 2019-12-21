package algorithms;

import algorithms.matrioshka.Year;
import java.util.HashMap;
import users.data.Message;

public class AlgorithmCMT
{    
    private HashMap<Integer, Year> yearTree;
    private int currentYear;
    
    public AlgorithmCMT()
    {
        this.yearTree = new HashMap<Integer, Year>();
        currentYear = 0;
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
            if(mess.getYear() != currentYear)
            {
                currentYear = mess.getYear();
                createNewYear(currentYear);
            }
            
            //Algoritmo = 3 (mensajes, caracteres, palablras)
            //Calculo medias
            yearTree.get(currentYear).addCount(mess, 3, 4);
        } 
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }       
        return true;
    }   
}
