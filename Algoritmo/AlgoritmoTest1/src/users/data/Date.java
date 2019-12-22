package users.data;

public class Date
{
    private int dia;
    private int mes;
    private int ano;
    
    public Date(int dia, int mes, int ano){
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }
    
    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAno(){
        return ano;
    }
    public String getFullDate(){
        return String.format("%02d/%02d/%4d", getDia(), getMes(), getAno());
    }
    public void setDia(int dia){
        this.dia = dia;
    }
    public void setMes(int mes){
        this.mes = mes;
    }
    public void setAno(int ano){
        this.ano = ano;
    }
    public void setTodo(int dia, int mes, int ano){
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }
}
