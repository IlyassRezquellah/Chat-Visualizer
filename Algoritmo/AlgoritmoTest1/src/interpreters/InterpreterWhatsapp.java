package interpreters;

import users.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterWhatsapp
{
    private static final String REGEX_MENSAJE = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s(['A'-'P']['M'])){0,2}\\s-\\s(.*?):\\s(.+)";
    private static final String REGEX_DATE_TIME = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])\\s(.+)";
    List<Person> persons;
    private int totalPersons;
    private Pattern patternMessage;
    private Pattern patternDateTime;
    private Matcher matcherMessage;
    private int index;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private String name;
    private String messge;
    private String filePath;
    /*0: todo
    1: día o mes
    2: mes o día
    3: año (sumarle 2000 si son 2 digitos)
    4: Si hay coma nos indica que el día y el mes están invertido
    5: hora
    6: minuto
    8: puede estar vacio o ser AM o PM (si es PM, hay que sumarle 12 a la hora)
    9: nombre
    10: mensaje*/
    
    public InterpreterWhatsapp(String pathFichero)
    {
        //Compilamos la representación de la expresión regular
        patternMessage = Pattern.compile(REGEX_MENSAJE);
        patternDateTime = Pattern.compile(REGEX_DATE_TIME);
        //Cargamos los encuentros que coinciden y los separa por grupos
        persons = new ArrayList<Person>();
        totalPersons = 0;
        index = -2;
        this.filePath = pathFichero;
    }
    
    public List<Person> chatInterpret()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line; 
            while ((line = reader.readLine()) != null)
            {
                if(!patternDateTime.matcher(line).matches() && index != -2)
                {
                    //System.out.println("Linea suelta.");
                    persons.get(index).addTextoToLastMessage(line);
                }
                else
                {
                    /*if(!patronMensaje.matcher(line).matches())
                    {
                        System.out.println("Información de Whatsapp.");
                    }
                    else*/
                    if(patternMessage.matcher(line).matches())
                    {
                        //System.out.println("Nuevo mensaje.");
                        matcherMessage = patternMessage.matcher(line);
                        while(matcherMessage.find())
                        {
                            //mostrarMatches(matcherMensaje);
                            mountDate(matcherMessage);
                            mountHour(matcherMessage);
                        }
                        if((index = thePersonExist(name)) >= 0)
                            persons.get(index).newMessage(day, month, year, minute, hour, messge);
                        else
                        {
                            persons.add(new Person(name, day, month, year, minute, hour, messge));
                            index = totalPersons;
                            totalPersons++;
                        }
                    }
                }
                //System.out.println("");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: "+e.getMessage());
            return null;
        }
        //mostrarMensajes();        
        return persons;
    }
    public void mountDate(Matcher matcherMensaje)
    {//Identificación del formato de la fecha
        if(matcherMensaje.group(4).equals(", "))
        {
            day = Integer.valueOf(matcherMensaje.group(2));
            month = Integer.valueOf(matcherMensaje.group(1));
        }
        else
        {
            day = Integer.valueOf(matcherMensaje.group(1));
            month = Integer.valueOf(matcherMensaje.group(2));
        }
        year = Integer.valueOf(matcherMensaje.group(3));
        if(matcherMensaje.group(3).length() == 2)
            year += 2000;
    }
    public void mountHour(Matcher matcherMensaje)
    {//Identificación del formato de la hora
        hour = Integer.valueOf(matcherMensaje.group(5));
        String dato = matcherMensaje.group(8);
        if(dato != null && dato.equals("PM"))
            hour += 12;
        minute = Integer.valueOf(matcherMensaje.group(6));
        name = matcherMensaje.group(9);
        messge = matcherMensaje.group(10);
    }
    public void showMatches(Matcher matcher)
    {
        System.out.println("Grupos: "+matcher.groupCount());
        for (int i = 0, t = matcher.groupCount(); i <= t; i++)
            System.out.println(i + ":\t" + matcher.group(i));
    }
    public void showMessages()
    {
        for (int i = 0; i < totalPersons; i++)
        {
            for (int j = 0, t = persons.get(i).getTotalMessages(); j < t; j++)
            {
                System.out.println(persons.get(i).returnMessage(j)+"\n");
            }
        }
    }
    public int thePersonExist(String tName)
    {
        for (int i = 0; i < totalPersons; i++)
        {
            if(persons.get(i).itsMe(tName))
                return i;
        }
        return -1;
    }
}
