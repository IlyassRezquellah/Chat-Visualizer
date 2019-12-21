package users;

import algorithms.AlgorithmCMT;
import users.data.Message;
import java.util.ArrayList;
import java.util.List;

public class Person
{
    private String name;
    private List<Message> mensajes;
    private int totalMessages;
    private AlgorithmCMT algorithm;
    
    public Person(String name)
    {
        setName(name);
        mensajes = new ArrayList<>();
        algorithm = new AlgorithmCMT();
        totalMessages = 0;
    }
    public Person(String name, int day, int month, int year, int minute, int hour, String text)
    {
        setName(name);
        mensajes = new ArrayList<>();
        algorithm = new AlgorithmCMT();
        totalMessages = 0;
        newMessage(day, month, year, minute, hour, text);
        //System.out.println("Algo creado para: " + name);
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void newMessage(int day, int month, int year, int minute, int hour, String text)
    {
        mensajes.add(new Message(day, month, year, minute, hour, text));
        totalMessages++;
    }
    public Message getMessageObj(int i)
    {
        return mensajes.get(i);
    }
    public int getTotalMessages()
    {
        return mensajes.size();
    }
    public void addTextoToLastMessage(String texto)
    {
        getMessageObj(totalMessages-1).setMoreText(texto);
    }
    public String returnMessage(int i)
    {
        return String.format("Fecha: %s%nHora: %s%nNombre: %s%nMensaje: %s", getMessageObj(i).getDateStr(), 
                getMessageObj(i).getHourStr(), getName(), getMessageObj(i).getText());
    }
    public boolean itsMe(String alguien)
    {
        return getName().equals(alguien);
    }
    public void startAlgorythm()
    {
        for(Message m : mensajes)
        {
            algorithm.calculate(m);
        }
        //algorithm.extractCountData();
        algorithm.extractData();
        
    }
}
