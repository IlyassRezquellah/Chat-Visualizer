package interpreters;

import Utils.RegularExpressions;
import users.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterWhatsapp
{
    //Lista de personas donde se almacenarán todas las personas que se encuentren en el chat (cada persona contentrá toda su información de mensajes)
    List<Person> persons;
    //Conteo de personas usado para tener un control del numero actual de personas creadas (Evitamos llamar al metodo size de la lista)
    private int totalPersons;
    private int index;
    ///Paterns usado para poder hacer la busqueda de patrones con las Regex anteriores
    //Este pattern se usa para deteminar si la linea actual es un mensaje de una persona o no (usa REGEX_MENSAJE)
    private Pattern patternMessage;
    //Este otro patter se usa para diferenciar una linea suelta de un mensaje (Usa REGEX_DATE_TIME)
    private Pattern patternDateTime;
    //Matcher encargado de almacenar las coincidencias separadas por grupos en base a los patterns anteriores 
    private Matcher matcherMessage;
    /* Chuleta de grupos de un mensaje en un matcher
    0: todo
    1: día o mes
    2: mes o día
    3: año (sumarle 2000 si son 2 digitos)
    4: Si hay coma nos indica que el día y el mes están invertido
    5: hora
    6: minuto
    8: puede estar vacio o ser AM o PM (si es PM, hay que sumarle 12 a la hora)
    9: nombre
    10: mensaje*/
    //Ruta del chat
    private String filePath;
    //Objetos básicos para almacenar los datos de un mensaje
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private String name;
    private String messge;
    
    public InterpreterWhatsapp(String pathFichero){
        /*Compilamos la representación de ambas expresión regular, para posteriormente 
        cargar los encuentros que coinciden y los separa por grupos usando el matcher*/
        patternMessage = Pattern.compile(RegularExpressions.VALIDATE_MENSSAGE);
        patternDateTime = Pattern.compile(RegularExpressions.VALIDATE_DATE_TIME);
        //Inicialización basica de objetos
        persons = new ArrayList<Person>();
        totalPersons = 0;
        index = -2;
        //Carga la ruta del chat
        this.filePath = pathFichero;
    }
    
    public List<Person> chatInterpret(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            //Leemos el chat txt linea a linea
            while ((line = reader.readLine()) != null){
                //Usando patternDateTime determinamos si esta linea suelta tiene o no la estructura del REGEX_DATE_TIME
                //Si sale false, es una linea suelta del mensaje anterior
                if(!patternDateTime.matcher(line).matches() && index != -2){
                    //System.out.println("Linea suelta.");
                    persons.get(index).addTextoToLastMessage(line);
                }
                else{//De lo contrario, si es true significa que es un mensaje
                    //Este if se usaba antes con patternMessage para identificar un mensaje informativo de whatsapp, ahora solo lo omito
                    /*if(!patronMensaje.matcher(line).matches()){
                        System.out.println("Información de Whatsapp.");
                    }
                    else*/
                    //Usando patternMessage determinamos si es un mensaje de una persona cuando devuelve true. Es posible con la extructura de REGEX_MENSAJE 
                    if(patternMessage.matcher(line).matches()){
                        //Una vez sabemos que es un mensaje valido, usamos el matcherMessage para dividir el mensaje en los grupos deseados
                        matcherMessage = patternMessage.matcher(line);
                        //Luego necesitamos usar find() con un bucle para acceder a los grupos internos del matcher 
                        while(matcherMessage.find()){
                            //mostrarMatches(matcherMensaje);
                            //Estos metodos guardan y montan la estrucura del mensaje teniendo en cuenta los formatos de fecha y hora, entre otras cosas
                            mountDate(matcherMessage);
                            mountHour(matcherMessage);
                            mountNameAndMessage(matcherMessage);
                        }
                        //Cuando tenemos ya tada la información guardada, comprobamos si la persona existe o no, para guardar este mensaje en ella
                        //Si exiteste la persona, únicamnete le agraga un mensaje
                        if((index = thePersonExist(name)) >= 0)
                            persons.get(index).addNewMessage(day, month, year, minute, hour, messge);
                        else{//De lo contrario, si la persona no existe, la crea y añade su primer mensaje en el contructor
                            persons.add(new Person(name, day, month, year, minute, hour, messge));
                            //Actualizamos el numero de personas y su indice actual
                            index = totalPersons;
                            totalPersons++;
                        }
                    }
                }
                //System.out.println("");
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
        //mostrarMensajes();        
        return persons;
    }
    //A partir de un matcher, guarda el día, hora, mes y año del mensaje de los grupos expecificados en la chuleta de arriba
    public void mountDate(Matcher matcherMensaje){
        //If identificador del formato de la fecha (dd/mm/yyy o mm/dd/yy) y lo adapta al siempre primer tipo
        //Guarda el día, mes y año teniendo en cuenta su formato (Siempre se guarda en dd/mm/yyy)
        //siempre se necesita conversión a int, ya que el matcher guarda strings y necesitamos numeros para el día, mes y año
        if(matcherMensaje.group(4).equals(", ")){
            day = Integer.valueOf(matcherMensaje.group(2));
            month = Integer.valueOf(matcherMensaje.group(1));
        }
        else{
            day = Integer.valueOf(matcherMensaje.group(1));
            month = Integer.valueOf(matcherMensaje.group(2));
        }
        
        year = Integer.valueOf(matcherMensaje.group(3));
        //Whatsapp siempre exporta el mes con los 2 numeros finales, así que siempre hay que rellenar los 2 anteriores
        if(matcherMensaje.group(3).length() == 2)
            year += 2000;
    }
    //A partir de un matcher, guarda la hora y minuto del mensaje de los grupos expecificados en la chuleta de arriba
    public void mountHour(Matcher matcherMensaje){
        //Usando los grupos del matcher almacena la hora en formato 24h (si se da el caso de un formato 12h, la convierte a 24h)
        //Guarda las horas (se necesita conversión a int, ya que el matcher guarda strings)
        hour = Integer.valueOf(matcherMensaje.group(5));
        
        //Este "dato" determina el tipo de formato que tiene la hora (12h o 24h)
        String dato = matcherMensaje.group(8);
        //If identificador del formato de la hora y si es PM le suma 12 para equilibrarlo a 24h
        if(dato != null && dato.equals("PM"))
            hour += 12;
        //Guarda los minutos (se necesita conversión a int, ya que el matcher guarda strings)
        minute = Integer.valueOf(matcherMensaje.group(6));
    }
    //Metodo para determinar si una persona ya existe
    public int thePersonExist(String tName){
        for (int i = 0; i < totalPersons; i++){
            if(persons.get(i).itsMe(tName))
                return i;
        }
        //Si devuelve -1, es que esta persona no existe
        return -1;
    }
    public void mountNameAndMessage(Matcher matcherMensaje){
        //Guarda el nombre de la persona y el mensaje
        name = matcherMensaje.group(9);
        messge = matcherMensaje.group(10);
    }
    ///-------------------------------------------------------------------------
    /*Metodos irrelebantes para el programa, existen con el motivo de testear 
        rápidamente que todo funcona correctamente de manera interna por consola*/
    //Metodo para mostrar los grupos de un matcher
    public void showMatches(Matcher matcher){
        System.out.println("Grupos: "+matcher.groupCount());
        for (int i = 0, t = matcher.groupCount(); i <= t; i++)
            System.out.println(i + ":\t" + matcher.group(i));
    }
    //Metodo para mostrar los mensajes con toda su información
    public void showMessages(){
        for (int i = 0; i < totalPersons; i++){
            for (int j = 0, t = persons.get(i).getTotalMessages(); j < t; j++){
                System.out.println(persons.get(i).returnMessage(j)+"\n");
            }
        }
    }
}
