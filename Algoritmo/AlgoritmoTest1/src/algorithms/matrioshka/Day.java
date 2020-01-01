package algorithms.matrioshka;

import users.data.Message;

public class Day{
    //Array de horas y su limite de tamaño
    private static final int LIMIR_HOURS = 24;
    private Hour[] hours;
    private int dayName;
    //Variables para almacenar los conteos
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Day(int number){
        //Inicializa las horas dentro de este día
        dayName = number;
        hours = new Hour[LIMIR_HOURS];
        for(int i = 0; i < LIMIR_HOURS;i++)
            hours[i] = new Hour(i);
        messageCount  = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    public void addCount(Message mess, int wordValue, int charValue){
        messageCount++;
        addWordCount(wordValue);
        addCharCount(charValue);
        hours[mess.getHour()].addCount(mess, wordValue, charValue);
    }
    public int getName(){
        return dayName +1;
    }
    //Metodos para la propia clase Day (Day[] days)
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
    //Metodos para la clase Hora (Hour[] hours)
    public Hour[] getHours(){
        return hours;
    }
    public int getHourMessageCount(int hour){
        return hours[hour].getMessageCount();
    }
    public int getHourWordCount(int hour){
        return hours[hour].getWordCount();
    }
    public int getHourCharCount(int hour){
        return hours[hour].getCharCount();
    }
}
