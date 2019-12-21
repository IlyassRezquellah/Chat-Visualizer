package algorithms.matrioshka;

import Utils.enums.EnumMonths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import users.data.Message;

public class Year
{
    private static final int LIMIR_MONTHS = 12;
    private LinkedHashMap<String, Month> treeMonths;
    private EnumMonths enumMonths;
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Year(int yourYear)
    {
        treeMonths = new LinkedHashMap<>();
        for(int i = 1; i <= LIMIR_MONTHS; i++)
        {
            enumMonths = EnumMonths.values()[i];
            System.out.println("Month name: " + enumMonths.name());
            treeMonths.put(enumMonths.name(), new Month(enumMonths.getNumber(), yourYear));
        }
        messageCount = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    public boolean addCount(Message mess, int charValue, int wordValue)
    {
        //System.out.println(mess.getMes() + " / " + EnumMonths.values()[mess.getMes()].name());
        messageCount++;
        addCharCount(charValue);
        addWordCount(wordValue);
        getMonthByNumber(mess.getMonth()).addCount(mess, charValue, wordValue);
        /*treeMonths.get(EnumMonths.values()[mess.getMonth()].name()).addCount
            (mess, charValue, wordValue);*/
        
        return true;
    }
    //Year
    public int getMessageCount()
    {
        return messageCount;
    }
    public int getWordCount()
    {
        return wordCount;
    }
    private void addWordCount(int wordCount)
    {
        this.wordCount += wordCount;
    }
    public int getCharCount()
    {
        return charCount;
    }
    private void addCharCount(int charCount)
    {
        this.charCount += charCount;
    }
    //Months
    public HashMap<String, Month> getMounths()
    {
        return treeMonths;
    }
    public Month getMounth(String key)
    {
        return treeMonths.get(key);
    }
    public Month getMonthByNumber(int number)
    {
        return treeMonths.get(EnumMonths.values()[number].name());
    }
    public int getMonthMessageCount(int month)
    {
        return getMonthByNumber(month).getMessageCount();
    }
    public int getMonthWordCount(int month)
    {
        return getMonthByNumber(month).getWordCount();
    }
    public int getMonthCharCount(int month)
    {
        return getMonthByNumber(month).getCharCount();
    }
    //Day
    public int getDayMessageCount(int month, int day)
    {
        return getMonthByNumber(month).getDayMessageCount(day);
    }
    public int getDayWordCount(int month, int day)
    {
        return getMonthByNumber(month).getDayMessageCount(day);
    }
    public int getDayCharCount(int month, int day)
    {
        return getMonthByNumber(month).getDayCharCount(day);
    }
    //Hour
    public int getHourMessageCount(int month, int day, int hour)
    {
        return getMonthByNumber(month).getHourMessageCount(day, hour);
    }
    public int getHourWordCount(int month, int day, int hour)
    {
        return getMonthByNumber(month).getHourMessageCount(day, hour);
    }
    public int getHourCharCount(int month, int day, int hour)
    {
        return getMonthByNumber(month).getHourCharCount(day, hour);
    }
}
