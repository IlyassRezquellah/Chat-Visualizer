package usuarios.datos;

public class Fecha
{
    private int dia;
    private int mes;
    private int ano;
    
    public Fecha(int dia, int mes, int ano)
    {
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }
    public int getDia()
    {
        return dia;
    }
    public int getMes()
    {
        return mes;
    }
    public int getAno()
    {
        return ano;
    }
    public String getTodo()
    {
        return String.format("%02d/%02d/%4d", getDia(), getMes(), getAno());
    }
    public void setDia(int dia)
    {
        this.dia = dia;
    }
    public void setMes(int mes)
    {
        this.mes = mes;
    }
    public void setAno(int ano)
    {
        this.ano = ano;
    }
    public void setTodo(int dia, int mes, int ano)
    {
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }
}
