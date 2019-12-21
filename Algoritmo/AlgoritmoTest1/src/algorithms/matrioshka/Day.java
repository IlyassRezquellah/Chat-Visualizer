package algorithms.matrioshka;

import users.data.Message;

public class Day
{
    private static final int LIMIR_HOURS = 24;
    private Hour[] hour;
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Day()
    {
        hour = new Hour[LIMIR_HOURS];
        for(int i = 0; i < LIMIR_HOURS;i++)
            hour[i] = new Hour();
        messageCount  = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    public void addCount(Message mess, int charValue, int wordValue)
    {
        messageCount++;
        addWordCount(wordValue);
        addCharCount(charValue);
        hour[mess.getHour()].addCount(mess, charValue, wordValue);
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
