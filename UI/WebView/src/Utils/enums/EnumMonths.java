package Utils.enums;

public enum EnumMonths{
    PLACEHOLDER(0),
    JUNARY(1),
    FEBRARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);
    
    private int number;
    
    EnumMonths(int number){
        this.number = number;
    }
    
    public int getNumber(){
        return number;
    }
}
