package algorithms.matrioshka;

import users.data.Message;

public class Day
{
    private static final int LIMIR_HOURS = 24;
    private Hour[] hours;
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Day()
    {
        hours = new Hour[LIMIR_HOURS];
        for(int i = 0; i < LIMIR_HOURS;i++)
            hours[i] = new Hour();
        messageCount  = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    public void addCount(Message mess, int charValue, int wordValue)
    {
        messageCount++;
        addWordCount(wordValue);
        addCharCount(charValue);
        hours[mess.getHour()].addCount(mess, charValue, wordValue);
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
    //Hour
    public Hour[] getHours()
    {
        return hours;
    }
    public int getHourMessageCount(int hour)
    {
        return hours[hour].getMessageCount();
    }
    public int getHourWordCount(int hour)
    {
        return hours[hour].getWordCount();
    }
    public int getHourCharCount(int hour)
    {
        return hours[hour].getCharCount();
    }
    
}
