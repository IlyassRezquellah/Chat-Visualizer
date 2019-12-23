package algorithms;

import Utils.Regex;
import algorithms.matrioshka.*;
import Utils.Colors;
import java.io.FileOutputStream;
import java.util.*;
import users.data.Message;

public class AlgorithmCMT{
    //Usamos un LikedHashMap, para que respete el orden de inserción de años
    //(LinkedHashMap<"Numero de año", "Obj Year">)
    private LinkedHashMap<Integer, Year> yearTree;
    //(LinkedHashMap<"Nombre del mes", "Obj Month">)
    private LinkedHashMap<String, Month> treeMonths;
    //Siempre controlamos el año en el que estamos actualmente (solo cambia si ela año aumenta en la conversación)
    private int currentYear;
    
    public AlgorithmCMT(){
        //Inicializamos el HashMap de años
        this.yearTree = new LinkedHashMap<Integer, Year>();
        //El currentYear empieza a 0, para dejar claro que no hay ningun año aún
        currentYear = 0;
    }
    
    //A partir de un numero (año); crea ese año e inicializa todos sus meses, días y horas
    public void createNewYear(int year){//9013 contructores aprox
        this.yearTree.put(year, new Year(year));
    }
    //Metodo para hacer el algoritmo basico de conteos y medias de un mensaje
    public boolean calculateCMT(Message mess){
        //Almacenaran el conteo de caracteres y palabras en un mensaje
        int wordCount = 0;
        int charCount = 0;
        
        try{
            //Primero comprobamos si el año del mensaje existe, si no es así se crea un año nuevo
            if(mess.getYear() != currentYear){
                //Adquirimos el numero del año a partir de mensaje y lo asociamos a currentYear, para tenerlo siempre en cuenta
                currentYear = mess.getYear();
                //Una vex tenemos el año, creamos el nuevo año dento del HashMap
                createNewYear(currentYear);
            }
            //Conteo de letras a partir de una String
            wordCount = getWordCountOfString(mess.getText());
            //Conteo de palabras a partir de una String
            charCount = getCharCountOfString(mess.getText());
            
            //Metodo de medias
            
            /*Introduce el conteo de este mensaje en el año actual por medio se .addCount.
            Este Objeto año se lo pasa ha su mes, día y hora en expecifico indicados por el mensaje*/
            //Chuleta: yearTree.get(Año).addCount("Mensaje", "ConteoPalabras", "ConteoLetras")
            boolean check = yearTree.get(currentYear).addCount(mess, wordCount, charCount);
            
            //El "check" almacena si todo ha ido bien el la inserción de datos
            /*if(check)
                //System.out.println("Inserción exitosa!");
            else
                System.out.println("Inserción fallida...");*/
        } 
        catch(Exception e){
            System.out.println("Error" + e);
            return false;
        }
        return true;
        
    }
    
    //Este metodo es demasiado complejo, hay que cambiarlo.
    //Te da la posiblidad de recorrer todas las listas de hashMap y arrays (Year, Month, day, hour) dentro de los mismos
    //String name hace referencia a la persona
    public void extractData(String name){
        StringBuilder data = new StringBuilder();
        
        data.append("Name--> " + name +"\n");
        
        //YEARS
        //Iteración de años (y), para acceder a un año usar "yearTree.get(y.getKey())"
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            data.append("Year--> "+ y.getKey()+"\n");
            data.append("\tMessages: " +
                    yearTree.get(y.getKey()).getMessageCount()
                    +"\n");
            data.append("\tWords: "  +
                    yearTree.get(y.getKey()).getWordCount()
                   +"\n");
            data.append("\tChars: "  +
                    yearTree.get(y.getKey()).getCharCount()
                    +"\n\n");
           
            //MONTHS
            //Iteración de meses (m), para acceder a un mes usar "???" (En proceso)
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                data.append("\tMonth-->" + m.getKey()+"\n");
                data.append("\t\tMensajes: " +
                        yearTree.get(y.getKey()).getOneMonth(m.getKey()).getMessageCount()
                        +"\n");
                data.append("\t\tWords: " +
                        yearTree.get(y.getKey()).getOneMonth(m.getKey()).getWordCount()
                        +"\n");
                data.append("\t\tChars: " + 
                        yearTree.get(y.getKey()).getOneMonth(m.getKey()).getCharCount()
                        +"\n\n");
                int countDays =1;
                //DAYS
                //Iteración de dias (d), para acceder a un día usar "d"
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    
                    data.append("\t\tDay: "+countDays+"\n");
                    data.append("\t\t\tMesssages: " +
                    d.getMessageCount()
                    +"\n");
                    data.append("\t\t\tWords: " +
                    d.getWordCount()
                    +"\n");
                    data.append("\t\t\tChars: " +
                    d.getCharCount()
                    +"\n\n");
                    countDays++;
                    int countHours = 0;
                    //hOURS
                    //Iteración de horas (h), para acceder a un día usar "h"
                    for (Hour h : d.getHours()){
                        
                        data.append("\t\t\tHour: "+countHours +"\n");
                        data.append("\t\t\t\tMesssages: " +
                        h.getMessageCount()
                       +"\n");
                        data.append("\t\t\t\tWords: " +
                        h.getWordCount()
                       +"\n");
                        data.append("\t\t\t\tChars: " +
                        h.getCharCount()
                       +"\n");
                        countHours++;
                    }
                }
            }
        }
        data.append("\n");
        try(FileOutputStream oFile = new FileOutputStream(name+"ShowMe.txt", false)){
            oFile.write(data.toString().getBytes());
        } 
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    //Este metodo crea un ficher log llamado "NombrePersona"+Matrioshka
    //Es muy útil para comprobar el conteo de los años que se han mandado mensajes. Así como el total de meses, días y horas de cada año que se han creados
    public void createLogCountOfTheYearsMonthsDyasAndHours(String name)
    {
        StringBuilder log = new StringBuilder();
        log.append("Total years: " + yearTree.size());
        
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            log.append("\nYear: " + y.getKey());
            
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                log.append("\n\tMounth: " + m.getKey());
                int daysCount = 0;
                int hoursCount = 0;
                
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    
                    for (Hour h : d.getHours())
                        hoursCount++;
                    daysCount++;
                }
                log.append("\n\t\tDays: " + daysCount);
                log.append("\n\t\t\tHours: " + hoursCount);
            }
            log.append("\n");
        }
        //Crea un fichero log con el total de años que se han creado, así como los meses días y horas de cada uno
        try(FileOutputStream oFile = new FileOutputStream(name+"Matrioshka.txt", false)){
            oFile.write(log.toString().getBytes());
        } 
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    //Obtener el numero total de caracteres en una String
    public int getCharCountOfString(String text)
    {
        String[] arrOfStr = text.split(Regex.COUNT_WORDS_CHARS); 
        int count = 0;
        //Contador de chars
        for(int counter = 0, length = arrOfStr.length; counter < length; counter++){
            count +=arrOfStr[counter].length();
        }
        return count;
    }
    //Obtener el numero total de palabras en una String
    public int getWordCountOfString(String text)
    {
        String[] arrOfStr = text.split(Regex.COUNT_WORDS_CHARS); 
        if((text.trim().length() == 0)){
            return 0;
        }else{
            return arrOfStr.length;
        }
    }
    //Obtener un mes de un año en contreto
    public Month getMonthOnYearTree(int year, String month)
    {
        return yearTree.get(year).getOneMonth(month);
    }
}
