package usuarios;

import usuarios.datos.Mensaje;
import java.util.ArrayList;
import java.util.List;

public class Persona
{
    private String nombre;
    private List<Mensaje> mensajes;
    private int totalMensajes;
    
    public Persona(String nombre)
    {
        setNombre(nombre);
        mensajes = new ArrayList<>();
        totalMensajes = 0;
    }
    public Persona(String nombre, int dia, int mes, int ano, int minuto, int hora, String texto)
    {
        setNombre(nombre);
        mensajes = new ArrayList<>();
        totalMensajes = 0;
        nuevoMesaje(dia, mes, ano, minuto, hora, texto);
    }
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public void nuevoMesaje(int dia, int mes, int ano, int minuto, int hora, String texto)
    {
        mensajes.add(new Mensaje(dia, mes, ano, minuto, hora, texto));
        totalMensajes++;
    }
    public Mensaje getMensajeObj(int i)
    {
        return mensajes.get(i);
    }
    public int getTotalMensaje()
    {
        return mensajes.size();
    }
    public void agrregarTextoAlAnteriorMensaje(String texto)
    {
        getMensajeObj(totalMensajes-1).setMasTexto(texto);
    }
    public String devolverMensaje(int i)
    {
        return String.format("Fecha: %s%nHora: %s%nNombre: %s%nMensaje: %s", getMensajeObj(i).getFechaStr(), 
                getMensajeObj(i).getHoraStr(), getNombre(), getMensajeObj(i).getTexto());
    }
    public boolean soyYo(String alguien)
    {
        return getNombre().equals(alguien);
    }
}
