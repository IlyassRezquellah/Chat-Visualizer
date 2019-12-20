package users.data;

public class Message
{
    private static final String MEDIA_ENGLISH = "<Media omitted>";
    private static final String MEDIA_SPANISH = "<Multimedia omitido>";
    private Fecha fecha;
    private Hora hora;
    private String texto;
    private boolean esMedia;
    
    public Message(int dia, int mes, int ano, int minuto, int hora, String texto)
    {
        setFecha(dia, mes, ano);
        setHora(minuto, hora);
        setTexto(texto);
        if(texto.equals(MEDIA_ENGLISH) || texto.equals(MEDIA_SPANISH))
        {
            esMedia = true;
            setMasTexto("");
        }
        else
        {
            esMedia = false;
            setMasTexto(texto);
        }
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
        if(!esMedia())
            return texto;
        else
            return "Multimedia";
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
    public boolean esMedia()
    {
        return esMedia;
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
