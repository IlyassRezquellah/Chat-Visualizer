package usuarios.datos;

public class Hora
{
    private int minuto;
    private int hora;

    public Hora(int minuto, int hora)
    {
        if(minuto >= 0 & minuto < 60)
            setMinuto(minuto);
        else
            setMinuto(0);
        if(hora >= 0 & hora < 24)
            setHora(hora);
        else
            setHora(0);
    }
    public int getMinuto()
    {
        return minuto;
    }
    public int getHora()
    {
        return hora;
    }
    public String getTodo()
    {
        return String.format("%02d:%02d", getHora(), getMinuto());
    }
    public void setMinuto(int minuto)
    {
        this.minuto = minuto;
    }
    public void setHora(int hora)
    {
        this.hora = hora;
    }
    public void setTodo(int minuto, int hora)
    {
        setMinuto(minuto);
        setHora(hora);
    }
}
