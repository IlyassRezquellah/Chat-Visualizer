package algorithms.matrioshka;

import Utils.enums.EnumMonths;
import java.util.HashMap;
import users.data.Message;

public class Year
{
    private static final int LIMIR_MONTHS = 12;
    private HashMap<String, Month> treeMonths;
    private EnumMonths enumMonths;
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Year(int yourYear)
    {
        treeMonths = new HashMap<>();
        for(int i = 1; i <= LIMIR_MONTHS; i++)
        {
            enumMonths = EnumMonths.values()[i];
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
        treeMonths.get(EnumMonths.values()[mess.getMonth()].name()).addCount
            (mess, charValue, wordValue);
        
        return true;
    }
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

}
