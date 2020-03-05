package users.data;

public class Date
{
    private int dia;
    private int mes;
    private int ano;
    
    public Date(int day, int month, int year){
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    public Date(Date date){
        setDay(date.getDay());
        setMonth(date.getMonth());
        setYear(date.getYear());
    }
    
    public int getDay(){
        return dia;
    }
    public int getMonth(){
        return mes;
    }
    public int getYear(){
        return ano;
    }
    public String getFullDate(){
        return String.format("%02d/%02d/%4d", getDay(), getMonth(), getYear());
    }
    public void setDay(int dia){
        this.dia = dia;
    }
    public void setMonth(int mes){
        this.mes = mes;
    }
    public void setYear(int ano){
        this.ano = ano;
    }
    public void setAll(int dia, int mes, int ano){
        setDay(dia);
        setMonth(mes);
        setYear(ano);
    }
    public java.util.Date getDate(){
        return new java.util.Date(getYear(), getMonth(), getDay()); 
    }
    @Override
    public String toString()
    {
        return String.format("%d-%d-%d", getDay(), getMonth(), getYear());
    }
    
}
