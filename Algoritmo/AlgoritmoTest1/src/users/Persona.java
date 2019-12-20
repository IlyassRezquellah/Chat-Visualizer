package users;

import algoritmos.AlgoritomoCMT;
import users.data.Message;
import java.util.ArrayList;
import java.util.List;

public class Persona
{
    private String nombre;
    private List<Message> mensajes;
    private int totalMensajes;
    private AlgoritomoCMT algoritmo;
    
    public Persona(String nombre)
    {
        setNombre(nombre);
        mensajes = new ArrayList<>();
        algoritmo = new AlgoritomoCMT();
        totalMensajes = 0;
    }
    public Persona(String nombre, int dia, int mes, int ano, int minuto, int hora, String texto)
    {
        setNombre(nombre);
        mensajes = new ArrayList<>();
        algoritmo = new AlgoritomoCMT();
        totalMensajes = 0;
        nuevoMesaje(dia, mes, ano, minuto, hora, texto);
        //System.out.println("Algo creado para: " + nombre);
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
        mensajes.add(new Message(dia, mes, ano, minuto, hora, texto));
        totalMensajes++;
    }
    public Message getMensajeObj(int i)
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
    public void startAlgorythm()
    {
        for(Message m : mensajes)
        {
            algoritmo.calculate(m);
        }
        
    }
}
