package users.data;

public class Message
{
    private static final String MEDIA_ENGLISH = "<Media omitted>";
    private static final String MEDIA_SPANISH = "<Multimedia omitido>";
    private Date date;
    private Time time;
    private String text;
    private boolean isMedia;
    
    public Message(int day, int month, int year, int minute, int hour, String text)
    {
        setDate(day, month, year);
        setHout(minute, hour);
        setText(text);
        if(text.equals(MEDIA_ENGLISH) || text.equals(MEDIA_SPANISH))
        {
            isMedia = true;
            setMoreText("");
        }
        else
        {
            isMedia = false;
            setMoreText(text);
        }
    }
    public Date getDateObj()
    {
        return date;
    }
    public Time getHourObj()
    {
        return time;
    }
    public String getDateStr()
    {
        return date.getTodo();
    }
    public String getHourStr()
    {
        return time.getAll();
    }
    public String getText()
    {
        if(!isMedia())
            return text;
        else
            return "Multimedia";
    }
    public int getHour()
    {
        return time.getHour();
    }
    public int getMinute()
    {
        return time.getMinute();
    }
    public int getDay()
    {
        return date.getDia();
    }
    public int getMonth()
    {
        return date.getMes();
    }
    public int getYear()
    {
        return date.getAno();
    }
    public boolean isMedia()
    {
        return isMedia;
    }
    public void setDate(int dia, int mes, int ano)
    {
        this.date = new Date(dia, mes, ano);
    }
    public void setHout(int minuto, int hora)
    {
        this.time = new Time(minuto, hora);
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setMoreText(String text)
    {
        this.text += ("\n\r" + text);
    }  
}
