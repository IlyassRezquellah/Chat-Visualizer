package base;

import interpreters.InterpreterWhatsapp;
import java.util.List;
import users.Person;

public class Main{
    public static void main(String[] args){
        Main app = new Main();
        app.run();
    }
    public void run(){
        //Crear más adelante una clases que gestione todo esto
        //Inicializa la clase interprete que analizará y devolverá el chat de whatsapp
        InterpreterWhatsapp parse = new InterpreterWhatsapp("..\\..\\Chats\\ChatBase(corta).txt");
        //Crea una lista de personas con todos sus mensajes así como la información pertinente del chat
        List<Person> persons = parse.chatInterpret();
        
        //Pruebas del algoritmo
        for (int i = 0, t = persons.size(); i < t; i++){
            //Inicia el algoritmo de conteo y medias (Cada clase Person, le enviará sus mensajes uno a uno para que los analice y guarde los datos deseados)
            persons.get(i).startAlgorythm();
            //Crea un log sencillo con todos los años, así como los meses, días y horas que tiene cada uno
            persons.get(i).createLogOfTheMatrioshkaStructure();
        }
    }
}