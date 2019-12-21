package base;

import interpreters.InterpreterWhatsapp;
import java.util.List;
import users.Person;

public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    public void run()
    {
        //Crear más adelante una clases que gestione todo esto
        InterpreterWhatsapp parse = new InterpreterWhatsapp("..\\..\\Chats\\ChatBase.txt");
        List<Person> persons = parse.chatInterpret();
        
        for (int i = 0, t = persons.size(); i < t; i++)
        {
            persons.get(i).startAlgorythm();
        }
    }
}