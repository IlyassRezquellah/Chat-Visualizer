package base;

import java.util.regex.*;

public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run2();
    }
    public void run2()
    {
        Interprete parse = new Interprete();
        System.out.println(parse.interpretarChat());
    }
//    public void run()
//    {
//        String texto =  "1/18/19, 12:11 PM - Loki val: Why/ do you have 2 numbers, Banner?";
//        //String texto = "18/01/19 22:11 - Loki: Why/ do you have 2 numbers, Banner?";
//        //String expresion = "^([0-2][0-9]|(3)[0-1])/([0-2][0-9]|(3)[0-1])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s['A'-'P']['M']){0,2}\\s-\\s";
//        String expresion = "^([0-2][0-9]|(3)[0-1])/([0-2][0-9]|(3)[0-1])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s(['A'-'P']['M'])){0,2}\\s-\\s(.+):\\s(.+)"; //Se repite el indicar (AM-PM) del formato 12h
//        int cabezera = 0;
//        Persona per = null;
//        //Compilamos la representación de la expresión regular
//        Pattern pattern = Pattern.compile(expresion);
//        //Cargamos los encuentros que coinciden y los separa por grupos
//        Matcher matcher = pattern.matcher(texto);
//        //MatchResult resultado = matcher.toMatchResult();//Resultado total de la expresión regular (innecesario)
//        /*0: todo
//        1: día o mes
//        3: mes o día
//        5: año (sumarle 2000 si son 2 digitos)
//        6: Si hay coma nos indica que el día y el mes están invertido
//        7: hora
//        8: minuto
//        10: puede estar vacio o ser AM o PM (si es PM, hay que sumarle 12 a la hora)
//        11: nombre
//        12: mensaje*/
//        int dia;
//        int mes;
//        int ano;
//        int hora;
//        int minuto;
//        String nombre;
//        String mensaje;
//
//        //Entra al bucle cuando encuentra al menos una coincidencia
//        while(matcher.find())
//        {
//            for (int i = 0, t = matcher.groupCount(); i <= t; i++)
//                System.out.println(i + ":\t" + matcher.group(i));
//
//            //Identificación del formato de la fecha
//            if(matcher.group(6).equals(","))
//            {
//                dia = Integer.valueOf(matcher.group(3));
//                mes = Integer.valueOf(matcher.group(1));
//            }
//            else
//            {
//                dia = Integer.valueOf(matcher.group(1));
//                mes = Integer.valueOf(matcher.group(3));
//            }
//            ano = Integer.valueOf(matcher.group(5));
//            if(matcher.group(5).length() == 2)
//                ano += 2000;
//
//            hora = Integer.valueOf(matcher.group(7));
//            if(matcher.group(10).equals("PM")) //Identificación del formato de la hora
//                hora += 12;
//            minuto = Integer.valueOf(matcher.group(8));
//
//            nombre = matcher.group(11);
//            mensaje = matcher.group(12);
//
//            /*per = new Persona(mensaje[0], Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(5)), 
//                    Integer.valueOf(matcher.group(8)), Integer.valueOf(matcher.group(7)), mensaje[1]);*/
//        }
//        //System.out.println(per.devolverMensaje(0));
//    }
}