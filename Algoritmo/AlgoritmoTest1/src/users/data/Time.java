package users.data;

public class Time
{
    private int minute;
    private int hour;

    public Time(int minute, int hour)
    {
        if(minute >= 0 & minute < 60)
            setMinute(minute);
        else
            setMinute(0);
        if(hour >= 0 & hour < 24)
            setHour(hour);
        else
            setHour(0);
    }
    public int getMinute()
    {
        return minute;
    }
    public int getHour()
    {
        return hour;
    }
    public String getAll()
    {
        return String.format("%02d:%02d", getHour(), getMinute());
    }
    public void setMinute(int minute)
    {
        this.minute = minute;
    }
    public void setHour(int hour)
    {
        this.hour = hour;
    }
    public void setAll(int minute, int hour)
    {
        setMinute(minute);
        setHour(hour);
    }
}
