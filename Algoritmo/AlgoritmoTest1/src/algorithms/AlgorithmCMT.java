package algorithms;

import algorithms.matrioshka.*;
import java.io.FileOutputStream;
import java.util.*;
import users.data.Message;

public class AlgorithmCMT
{    
    private LinkedHashMap<Integer, Year> yearTree;
    private int currentYear;
    
    public AlgorithmCMT()
    {
        this.yearTree = new LinkedHashMap<Integer, Year>();
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
    public void extractData()
    {
        System.out.println(yearTree.size());
        StringBuilder data = new StringBuilder();

        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet())
        {           
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getMounths().entrySet())
            {
                for(Day d : yearTree.get(y.getKey()).getMounth(m.getKey()).getDays())
                {
                    for (Hour h : d.getHours())
                    {
                        
                    }
                }
            }
        }
        
        try(FileOutputStream oFile = new FileOutputStream("output.txt", false); )
        {
            oFile.write(currentYear);
        } 
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
    public void extractCountData()
    {
        System.out.println(yearTree.size());
        StringBuilder data = new StringBuilder();

        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet())
        {
            System.out.println("Year: " + y.getKey());
            
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getMounths().entrySet())
            {
                System.out.println("Mounth:" + m.getKey());
                int ddd = 0;
                int hhh = 0;
                for(Day d : yearTree.get(y.getKey()).getMounth(m.getKey()).getDays())
                {
                    for (Hour h : d.getHours())
                    {
                        hhh++;
                    }
                    ddd++;
                }
                System.out.println("Day: " + ddd);
                System.out.println("Hour: " + hhh + "\n");
            }
            System.out.println("");
        }
        
        try(FileOutputStream oFile = new FileOutputStream("output.txt", false); )
        {
            oFile.write(currentYear);
        } 
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
