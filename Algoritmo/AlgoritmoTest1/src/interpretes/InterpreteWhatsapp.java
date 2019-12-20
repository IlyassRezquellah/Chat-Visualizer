package interpretes;

import users.Persona;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreteWhatsapp
{
    private static final String REGEX_MENSAJE = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s(['A'-'P']['M'])){0,2}\\s-\\s(.*?):\\s(.+)";
    private static final String REGEX_FECHA_HORA = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])\\s(.+)";
    List<Persona> personas;
    private int totalPersonas;
    private Pattern patronMensaje;
    private Pattern patronFechaHora;
    private Matcher matcherMensaje;
    private int indice;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;
    private String nombre;
    private String mensaje;
    private String pathFichero;
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
    
    public InterpreteWhatsapp(String pathFichero)
    {
        //Compilamos la representación de la expresión regular
        patronMensaje = Pattern.compile(REGEX_MENSAJE);
        patronFechaHora = Pattern.compile(REGEX_FECHA_HORA);
        //Cargamos los encuentros que coinciden y los separa por grupos
        personas = new ArrayList<Persona>();
        totalPersonas = 0;
        indice = -2;
        this.pathFichero = pathFichero;
    }
    
    public List<Persona> interpretarChat()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(pathFichero)))
        {
            String line; 
            while ((line = reader.readLine()) != null)
            {
                if(!patronFechaHora.matcher(line).matches() && indice != -2)
                {
                    //System.out.println("Linea suelta.");
                    personas.get(indice).agrregarTextoAlAnteriorMensaje(line);
                }
                else
                {
                    /*if(!patronMensaje.matcher(line).matches())
                    {
                        System.out.println("Información de Whatsapp.");
                    }
                    else*/
                    if(patronMensaje.matcher(line).matches())
                    {
                        //System.out.println("Nuevo mensaje.");
                        matcherMensaje = patronMensaje.matcher(line);
                        while(matcherMensaje.find())
                        {
                            //mostrarMatches(matcherMensaje);
                            montarFecha(matcherMensaje);
                            montarHora(matcherMensaje);
                        }
                        if((indice = laPersonaExiste(nombre)) >= 0)
                            personas.get(indice).nuevoMesaje(dia, mes, ano, minuto, hora, mensaje);
                        else
                        {
                            personas.add(new Persona(nombre, dia, mes, ano, minuto, hora, mensaje));
                            indice = totalPersonas;
                            totalPersonas++;
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
        return personas;
    }
    public void montarFecha(Matcher matcherMensaje)
    {//Identificación del formato de la fecha
        if(matcherMensaje.group(4).equals(", "))
        {
            dia = Integer.valueOf(matcherMensaje.group(2));
            mes = Integer.valueOf(matcherMensaje.group(1));
        }
        else
        {
            dia = Integer.valueOf(matcherMensaje.group(1));
            mes = Integer.valueOf(matcherMensaje.group(2));
        }
        ano = Integer.valueOf(matcherMensaje.group(3));
        if(matcherMensaje.group(3).length() == 2)
            ano += 2000;
    }
    public void montarHora(Matcher matcherMensaje)
    {//Identificación del formato de la hora
        hora = Integer.valueOf(matcherMensaje.group(5));
        String dato = matcherMensaje.group(8);
        if(dato != null && dato.equals("PM"))
            hora += 12;
        minuto = Integer.valueOf(matcherMensaje.group(6));
        nombre = matcherMensaje.group(9);
        mensaje = matcherMensaje.group(10);
    }
    public void mostrarMatches(Matcher matcher)
    {
        System.out.println("Grupos: "+matcher.groupCount());
        for (int i = 0, t = matcher.groupCount(); i <= t; i++)
            System.out.println(i + ":\t" + matcher.group(i));
    }
    public void mostrarMensajes()
    {
        for (int i = 0; i < totalPersonas; i++)
        {
            for (int j = 0, t = personas.get(i).getTotalMensaje(); j < t; j++)
            {
                System.out.println(personas.get(i).devolverMensaje(j)+"\n");
            }
        }
    }
    public int laPersonaExiste(String tNombre)
    {
        for (int i = 0; i < totalPersonas; i++)
        {
            if(personas.get(i).soyYo(tNombre))
                return i;
        }
        return -1;
    }
}
