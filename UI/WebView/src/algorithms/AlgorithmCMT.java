package algorithms;

import Utils.Regex;
import algorithms.matrioshka.*;
import Utils.Colors;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.*;
import java.util.Map.Entry;
import users.data.Message;

public class AlgorithmCMT{
    //Usamos un LikedHashMap, para que respete el orden de inserción de años
    //(LinkedHashMap<"Numero de año", "Obj Year">)
    private LinkedHashMap<Integer, Year> yearTree;
    //(LinkedHashMap<"Nombre del mes", "Obj Month">)
    private LinkedHashMap<String, Month> treeMonths;
    //Siempre controlamos el año en el que estamos actualmente (solo cambia si ela año aumenta en la conversación)
    private int currentYear;
    //Variables globales que tendran el valor total de la suma de mensajes,words y chars de x personas
    private int messagesGlobal;
    private int wordsGlobal;
    private int charsGlobal;
    private int daysGlobal;
    private float percentageSpoke;
    private double[][] averageMonthMessagesByYear;
    private double[][] averageMonthWordsByYear;
    private double[][] averageMonthCharsByYear;
    private double[] dataHours;
    
    public AlgorithmCMT(){
        //Inicializamos el HashMap de años
        this.yearTree = new LinkedHashMap<Integer, Year>();
        //El currentYear empieza a 0, para dejar claro que no hay ningun año aún
        currentYear = 0;
        
        messagesGlobal = 0;
        wordsGlobal = 0;
        charsGlobal = 0;
        daysGlobal = 0;
        percentageSpoke = 0.0f;
    }
    //Getters de datos globales
    public int getYearNumber(int position){
        return (int)yearTree.keySet().toArray()[position];
    }
    //Getters para pasar datos a la clase de PersonManager
    public int getMessagesGlobal(){
        return messagesGlobal;
    }
    public int getWordsGlobal(){
        return wordsGlobal;
    }
    public int getCharsGlobal(){
        return charsGlobal;
    }
    public int getDaysGlobal(){
        return daysGlobal;
    }
    public float getPercentageSpoke(){
        return percentageSpoke;
    }

    //----------------------------------
    //Media de messages mensuales
    public double[][] getAverageMonthMessages(){
        return averageMonthMessagesByYear;
    }
    public double getOneAverageMonthMessages(int y, int m){
        return averageMonthMessagesByYear[y][m];
    }
    //----------------------------------
    //Media de words mensuales
    public double[][] getAverageMonthWords(){
        return averageMonthWordsByYear;
    }
    public double getOneAverageMonthWords(int y, int m){
        return averageMonthWordsByYear[y][m];
    }
    //----------------------------------
    //Media de chars mensuales
    public double[][] getAverageMonthChars(){
        return averageMonthCharsByYear;
    }
    public double getOneAverageMonthChars(int y, int m){
        return averageMonthCharsByYear[y][m];
    }
    //-----------------------------------
    //Getter del array de messages dia
    public double[] getCountHourPerson(){
        return dataHours;
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
            //Conteo de palabras a partir de una String
            wordCount = getWordCountOfString(mess.getText());
            //Conteo de letras a partir de una String
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
    //Este metodo hace una carga de datos posterior a la inicialización de la matrioshka
    public void postMatrioshka(){
        //Asignamos los valores a las variables globales de tops
        topsGlobalData();
        //Calcula los días que ha hablado esta persona en la consversación
        personCountDays();
        //Obtiene la media de mensajes/words/chars que se escriben de manera mensual
        averageMonthMessagesPerson();
        averageMonthWordsPerson();
        averageMonthCharsPerson();
        //Obtiene el numero de messages de cada hora de cada persona
        countHourMessagesPerson();
        //Obtiene media de cuanto habla cada persona 
        percentagePersonCount();
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
    public void createLogCountOfTheYearsMonthsDyasAndHours(String name){
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
    public int getCharCountOfString(String text){
        String[] arrOfStr = text.split(Regex.COUNT_WORDS_CHARS); 
        int count = 0;
        //Contador de chars
        for(int counter = 0, length = arrOfStr.length; counter < length; counter++){
            count +=arrOfStr[counter].length();
        }
        return count;
    }
    //Obtener el numero total de palabras en una String
    public int getWordCountOfString(String text){
        String[] arrOfStr = text.split(Regex.COUNT_WORDS_CHARS); 
        if((text.trim().length() == 0)){
            return 0;
        }else{
            return arrOfStr.length;
        }
    }
    //Obtener un mes de un año en contreto
    public Month getMonthOnYearTree(int year, String month){
        return yearTree.get(year).getOneMonth(month);
    }
    //Devuelve la extructura matrioshka con toda la información
    public LinkedHashMap<Integer, Year> getMatrioshka(){
        return yearTree;
    }
    //Calcular las tops globales
    public void topsGlobalData(){
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            messagesGlobal = messagesGlobal + yearTree.get(y.getKey()).getMessageCount();
            wordsGlobal = wordsGlobal + yearTree.get(y.getKey()).getWordCount();
            charsGlobal = charsGlobal + yearTree.get(y.getKey()).getCharCount();
        }
    }
    // dias totales de la conversacion de cada persona
    public void personCountDays(){
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    if(d.getMessageCount()>0){
                        daysGlobal++;
                    }
                }
            }
        }
    }
    //Obtener media (palabras totales de la convo/mensajes totales de la convo) de cada persona en la conversación 
    public void percentagePersonCount(){
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            percentageSpoke += (float)(yearTree.get(y.getKey()).getWordCount())/ (yearTree.get(y.getKey()).getMessageCount());
        }

    }
    //Calcula la media de messages que manda cada persona mensualmente
    public void averageMonthMessagesPerson(){
        //Inicializamos el array 2D donde guardaremos las medias de mensajes mensuales escritos de cada año
        averageMonthMessagesByYear = new double[yearTree.size()][12];
        //Variable auxiliar para guardar lo que habla una persona
        double averageMonthMessages = 0.0f;
        //Contadores pra moverse automaticamente entre los datos del array 2D
        int counYears = 0;
        int counMonths = 0;
        //Bucle de la matrioshka para obtener la información pertienen sobre los días, meses y años
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    averageMonthMessages += d.getMessageCount();
                }
                averageMonthMessagesByYear[counYears][counMonths] = averageMonthMessages / yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays().length;
                //System.out.println("Media del mes de " + m.getKey() + "--> " + averageMonthByYear[counYears][counMonths]); 
                averageMonthMessages = 0;
                counMonths++;
            }
            counYears++;
            counMonths = 0;
        }
        //Muestara por consola de todos los datos obteneidos. Se cambiará más tarde por un json
        /*for (int year = 0, tY = averageMonthByYear.length; year < tY; year++){
            System.out.println("Año: " + year);
            for (int month = 0, tM = 12; month < tM; month++){
                System.out.println("Media del mes " + month + ": " + averageMonthByYear[year][month]);
            }
        }
        System.out.println("\n");*/
    }
     //Calcula la media de palabras que manda cada persona mensualmente
    public void averageMonthWordsPerson(){
        //Inicializamos el array 2D donde guardaremos las medias de palabras mensuales escritos de cada año
        averageMonthWordsByYear = new double[yearTree.size()][12];
        //Variable auxiliar para guardar lo que habla una persona
        double averageMonthWords = 0.0f;
        //Contadores pra moverse automaticamente entre los datos del array 2D
        int counYears = 0;
        int counMonths = 0;
        //Bucle de la matrioshka para obtener la información pertienen sobre los días, meses y años
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    averageMonthWords += d.getWordCount();
                }
                averageMonthWordsByYear[counYears][counMonths] = averageMonthWords / yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays().length;
                //System.out.println("Media del mes de " + m.getKey() + "--> " + averageMonthByYear[counYears][counMonths]); 
                averageMonthWords = 0;
                counMonths++;
            }
            counYears++;
            counMonths = 0;
        }
        
    }
    
     //Calcula la media de chars que manda cada persona mensualmente
    public void averageMonthCharsPerson(){
        //Inicializamos el array 2D donde guardaremos las medias de chars mensuales escritos de cada año
        averageMonthCharsByYear = new double[yearTree.size()][12];
        //Variable auxiliar para guardar lo que habla una persona
        double averageMonthChars = 0.0f;
        //Contadores pra moverse automaticamente entre los datos del array 2D
        int counYears = 0;
        int counMonths = 0;
        //Bucle de la matrioshka para obtener la información pertienen sobre los días, meses y años
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    averageMonthChars += d.getCharCount();
                }
                averageMonthCharsByYear[counYears][counMonths] = averageMonthChars / yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays().length;
                //System.out.println("Media del mes de " + m.getKey() + "--> " + averageMonthByYear[counYears][counMonths]); 
                averageMonthChars = 0;
                counMonths++;
            }
            counYears++;
            counMonths = 0;
        }
        
    }
    //Metodo para sacar los mensajes por hora de cada persona
    public void countHourMessagesPerson(){
        //Inicializamos el array  donde guardaremos los messages de cada hora que mandado cada persona a lo largo de todos los años de la conversacion
        dataHours = new double[24];
        double messagesHours = 0f;
        //Contador --> <24
        int countHours = 0;
        //Bucle de la matrioshka para obtener la información pertienen sobre los días, meses y años
        for(HashMap.Entry<Integer, Year> y : yearTree.entrySet()){
            for(HashMap.Entry<String, Month> m : yearTree.get(y.getKey()).getAllMonths().entrySet()){
                for(Day d : yearTree.get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    countHours =0;
                    for (Hour h : d.getHours()){
                       //messagesHours += (float)(h.getWordCount())/ (h.getMessageCount());
                       messagesHours += h.getMessageCount();
                       dataHours[countHours] += messagesHours;
                       //System.out.println("Hora" + countHours +": " + dataHours[countHours]);
                       messagesHours=0;
                       countHours++;                     
                    }
                }
            }
        } 
        //System.out.println(Arrays.toString(dataHours));
        /*for (int i = 0; i < 24; i++) {
            System.out.println("Hora" + i +": " + dataHours[i]);
        }*/
        
    }
}
