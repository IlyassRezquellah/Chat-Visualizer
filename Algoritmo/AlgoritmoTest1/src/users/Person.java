package users;

import algorithms.AlgorithmCMT;
import Utils.Colors;
import algorithms.matrioshka.Year;
import users.data.Message;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Person{
    //Nombre de la personas que envia estos mensajes
    private String name;
    //Mensajes enviados por esta persona (Contienen el mensaje, así como la fecha y hora en que se enviaron)
    private List<Message> mensajes;
    //Conteo total de mensajes mandados por esta persona
    private int totalMessages;
    //Algoritmo propio de cada persona
    private AlgorithmCMT algorithm;
    ///-------------------------------------------------------------------------
    ///Contructores:
    //Este constructor crea una nueva persona, únicamente con un nombre
    public Person(String name){
        setName(name);
        mensajes = new ArrayList<>();
        algorithm = new AlgorithmCMT();
        totalMessages = 0;
    }
    public Person(String name, Message message){
        setName(name);
        mensajes = new ArrayList<>();
        algorithm = new AlgorithmCMT();
        totalMessages = 0;
        addNewMessage(message);
    }
    //Este contructor crea una persona y le añade un mensaje nuevo directamente
    public Person(String name, int day, int month, int year, int minute, int hour, String text){
        setName(name);
        mensajes = new ArrayList<>();
        algorithm = new AlgorithmCMT();
        totalMessages = 0;
        addNewMessage(day, month, year, minute, hour, text);
    }
    
    //Añade un mensaje a esta persona
    public void addNewMessage(int day, int month, int year, int minute, int hour, String text){
        mensajes.add(new Message(day, month, year, minute, hour, text));
        totalMessages++;
    }
    public void addNewMessage(Message message){
        mensajes.add(new Message(message.getDay(), message.getMonth(), message.getYear(), 
                message.getMinute(), message.getHour(), message.getText()));
        totalMessages++;
    }
    //Añade una linea suelta al mensaja anterior
    public void addTextoToLastMessage(String texto){
        getMessageObj(totalMessages-1).setMoreText(texto);
    }
    
    //Getters y setters basicos
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Message getMessageObj(int i){
        return mensajes.get(i);
    }
    public int getTotalMessages(){
        return mensajes.size();
    }
    public String returnMessage(int i){
        return String.format("Fecha: %s%nHora: %s%nNombre: %s%nMensaje: %s", getMessageObj(i).getDateStr(), 
                getMessageObj(i).getHourStr(), getName(), getMessageObj(i).getText());
    }
    public boolean itsMe(String alguien){
        return getName().equals(alguien);
    }
    //Devuelve la matrioshka de esta persona
    public LinkedHashMap<Integer, Year> getMatrioshka(){
        return algorithm.getMatrioshka();
    }
    //--------------------------------------------------------------------------
    //Metodos del algoritmo
    //Inicia el argoritmo pasandole mensaje a mensaje para obtener los numeros deseados
    public void startAlgorythm(){
        for(Message m : mensajes){
            algorithm.calculateCMT(m);
        }
        //Comprueba los datos extrayendolos (Consola, fichero log)
        algorithm.extractData(this.getName());
       // System.out.println("Total messages de " + getName() +" "+ Colors.ANSI_YELLOW + getTotalMessages() + Colors.ANSI_RESET+ "\n\n");
    }
    //Crea un log con toda la estructura matrioshka creada, así como el numero de años, meses, días y horas creados
    public void createLogOfTheMatrioshkaStructure(){
        algorithm.createLogCountOfTheYearsMonthsDyasAndHours(this.getName());
    }
}
