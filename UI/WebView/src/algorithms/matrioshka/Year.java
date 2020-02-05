package algorithms.matrioshka;

import Utils.enums.EnumMonths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import users.data.Message;

public class Year{
    //LinkedHashMap de meses con su limite de tamaño
    private static final int LIMIR_MONTHS = 12;
    //Usamos un LikedHashMap, para que respete el orden de inserción de meses
    //(LinkedHashMap<"Nombre del mes", "Obj Month">)
    private LinkedHashMap<String, Month> treeMonths;
    //Enum para el nombre de los meses
    private EnumMonths enumMonths;
    //Variables para almacenar los conteos
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Year(int yourYear){
        //Inicializa los meses de este año, con su LIMIR_MONTHS
        treeMonths = new LinkedHashMap<>();
        for(int i = 1; i <= LIMIR_MONTHS; i++){
            enumMonths = EnumMonths.values()[i];
            treeMonths.put(enumMonths.name(), new Month(enumMonths.getNumber(), yourYear));
        }
        messageCount = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    //Metodos para la propia clase Year (LinkedHashMap<Integer, Year> yearTree)
    public boolean addCount(Message mess, int wordValue, int charValue){
        try{
            //System.out.println(mess.getMes() + " / " + EnumMonths.values()[mess.getMes()].name());
            messageCount++;
            addWordCount(wordValue);
            addCharCount(charValue);
            getMonthByNumber(mess.getMonth()).addCount(mess, wordValue, charValue);
            /*treeMonths.get(EnumMonths.values()[mess.getMonth()].name()).addCount
                (mess, charValue, wordValue);*/
        }
        catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        }
        return true;
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
    //Metodos para la clase Months (LinkedHashMap<String, Month> treeMonths)
    public HashMap<String, Month> getAllMonths(){
        return treeMonths;
    }
    public Month getOneMonth(String key){
        return treeMonths.get(key);
    }
    public Month getMonthByNumber(int number){
        return treeMonths.get(EnumMonths.values()[number].name());
    }
    public int getMonthMessageCount(int month){
        return getMonthByNumber(month).getMessageCount();
    }
    public int getMonthWordCount(int month){
        return getMonthByNumber(month).getWordCount();
    }
    public int getMonthCharCount(int month){
        return getMonthByNumber(month).getCharCount();
    }
    //Metodos para la clase Day (Day[] days)
    public int getDayMessageCount(int month, int day){
        return getMonthByNumber(month).getDayMessageCount(day);
    }
    public int getDayWordCount(int month, int day){
        return getMonthByNumber(month).getDayMessageCount(day);
    }
    public int getDayCharCount(int month, int day){
        return getMonthByNumber(month).getDayCharCount(day);
    }
    //Metodos para la clase Hour (Hour[] hours)
    public int getHourMessageCount(int month, int day, int hour){
        return getMonthByNumber(month).getHourMessageCount(day, hour);
    }
    public int getHourWordCount(int month, int day, int hour){
        return getMonthByNumber(month).getHourMessageCount(day, hour);
    }
    public int getHourCharCount(int month, int day, int hour){
        return getMonthByNumber(month).getHourCharCount(day, hour);
    }
}
