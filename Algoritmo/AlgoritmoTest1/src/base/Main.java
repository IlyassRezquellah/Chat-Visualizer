package base;

import interpretes.InterpreteWhatsapp;
import java.util.List;
import users.Persona;

public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    public void run()
    {
        InterpreteWhatsapp parse = new InterpreteWhatsapp("..\\..\\Chats\\ChatBase.txt");
        List<Persona> personas = parse.interpretarChat();
        
        for (int i = 0, t = personas.size(); i < t; i++)
        {
            personas.get(i).startAlgorythm();
        }
    }
}