package algoritmos.matrioshka​​;

import users.data.Message;

public class Month
{
    private int limitDays;
    Day[] days;
    private int messageCount;
    private int wordCount;
    private int charCount;
    public Month(int month, int year)
    {
        limitDays = Utils.Auxiliar.getNumberDaysGivenYear(year, month);
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
        days[mess.getDia()-1].addCount(mess, charValue, wordValue);
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
