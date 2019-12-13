package base;

public class Mensaje
{
    private Fecha fecha;
    private Hora hora;
    private String texto;
    
    public Mensaje(int dia, int mes, int ano, int minuto, int hora, String texto)
    {
        setFecha(dia, mes, ano);
        setHora(minuto, hora);
        setTexto(texto);
    }
    public Fecha getFechaObj()
    {
        return fecha;
    }
    public Hora getHoraObj()
    {
        return hora;
    }
    public String getFechaStr()
    {
        return fecha.getTodo();
    }
    public String getHoraStr()
    {
        return hora.getTodo();
    }
    public String getTexto()
    {
        return texto;
    }
    public int getHora()
    {
        return hora.getHora();
    }
    public int getMinuto()
    {
        return hora.getMinuto();
    }
    public int getDia()
    {
        return fecha.getDia();
    }
    public int getMes()
    {
        return fecha.getMes();
    }
    public int getAno()
    {
        return fecha.getAno();
    }
    public void setFecha(int dia, int mes, int ano)
    {
        this.fecha = new Fecha(dia, mes, ano);
    }
    public void setHora(int minuto, int hora)
    {
        this.hora = new Hora(minuto, hora);
    }
    public void setTexto(String texto)
    {
        this.texto = texto;
    }
    public void setMasTexto(String texto)
    {
        this.texto += ("\n\r" + texto);
    }  

}
