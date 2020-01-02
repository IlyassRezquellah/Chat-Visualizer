package algorithms.matrioshka;

import java.util.Arrays;
import users.data.Message;

public class Month{
    //Array de dias y su limite de tamaño
    private int limitDays;
    private Day[] days;
    //Variables para almacenar los conteos
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Month(int month, int year){
        //Inicializa los días de este meás, con su limitDays 
        //Gracias a getNumberDaysGivenYear obtenemos el total de días que tiene este mes, pasandole el año y mes en el que está
        limitDays = Utils.Auxiliary.getNumberDaysGivenYear(year, month);
        days = new Day[limitDays];
        for(int i = 0; i < limitDays;i++)
            days[i] = new Day(i);        
        messageCount = 0;
        wordCount = 0;
        charCount = 0;
    }
    //Metodos para la propia clase Months (LinkedHashMap<String, Month> treeMonths)
    public void addCount(Message mess, int wordValue, int charValue){
        messageCount++;
        addWordCount(wordValue);
        addCharCount(charValue);
        //REVISAR EL TEMA DE LOS DÍAS, el array empieza por 0, y el mes por 1 (Esto funciona)
        days[mess.getDay()-1].addCount(mess, wordValue, charValue);
    }
    public int getMessageCount(){
        return messageCount;
    }
    public int getWordCount(){
        return wordCount;
    }
    private void addWordCount(int wordCount){
        this.wordCount += wordCount;
    }
    public int getCharCount(){
        return charCount;
    }
    private void addCharCount(int charCount){
        this.charCount += charCount;
    }
    //Metodos para la clase Day (Day[] days)
    public Day[] getDays(){
        return days;
    }
    public Day getOneDay(int key){
        return days[key];
    }
    public int getDayMessageCount(int day){
        return days[day].getMessageCount();
    }
    public int getDayWordCount(int day){
        return days[day].getWordCount();
    }
    public int getDayCharCount(int day){
        return days[day].getCharCount();
    }
    //Metodos para la clase Hour (Hour[] hours)
    public int getHourMessageCount(int day, int hour){
        return days[day].getHourMessageCount(hour);
    }
    public int getHourWordCount(int day, int hour){
        return days[day].getHourWordCount(hour);
    }
    public int getHourCharCount(int day, int hour){
        return days[day].getHourCharCount(hour);
    }
}
