package algorithms.matrioshka;

import users.data.Message;

public class Month
{
    private int limitDays;
    private Day[] days;
    private int messageCount;
    private int wordCount;
    private int charCount;
    
    public Month(int month, int year)
    {
        limitDays = Utils.Auxiliary.getNumberDaysGivenYear(year, month);
        days = new Day[limitDays];
        for(int i = 0; i < limitDays;i++)
            days[i] = new Day();
        messageCount = 0;
        wordCount = 0;
        charCount = 0;
    }
    
    public void addCount(Message mess, int charValue, int wordValue)
    {
        messageCount++;
        addWordCount(wordValue);
        addCharCount(charValue);
        //System.out.println("Dia: " + mess.getDia());
        days[mess.getDay()-1].addCount(mess, charValue, wordValue);
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
    //Days
    public Day[] getDays()
    {
        return days;
    }
    public int getDayMessageCount(int day)
    {
        return days[day].getMessageCount();
    }
    public int getDayWordCount(int day)
    {
        return days[day].getWordCount();
    }
    public int getDayCharCount(int day)
    {
        return days[day].getCharCount();
    }
    //Hour
    public int getHourMessageCount(int day, int hour)
    {
        return days[day].getHourMessageCount(hour);
    }
    public int getHourWordCount(int day, int hour)
    {
        return days[day].getHourWordCount(hour);
    }
    public int getHourCharCount(int day, int hour)
    {
        return days[day].getHourCharCount(hour);
    }
   
}
