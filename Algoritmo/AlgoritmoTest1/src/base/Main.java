package base;

import interpretes.InterpreteWhatsapp;

public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    public void run()
    {
        InterpreteWhatsapp parse = new InterpreteWhatsapp(".\\Chats\\ChatBase.txt");
        System.out.println(parse.interpretarChat());
    }
}