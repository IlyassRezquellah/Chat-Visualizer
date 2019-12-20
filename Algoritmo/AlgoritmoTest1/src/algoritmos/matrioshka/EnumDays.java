package algoritmos.matrioshka;

public enum EnumDays
{
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);
    
    private int number;
    
    EnumDays(int number)
    {
        this.number = number;
    }
    
    public int getNumber()
    {
        return number;
    }
}
