package base;

import users.PersonManager;

public class Main{
    public static void main(String[] args){
        Main app = new Main();
        app.run();
    }
    public void run(){
        //Inicializamos el manager de personas, cargando la lista de personas con sus mensajes de chat que nos devuelve la clase PersonManager que analiza el txt del chat (whatsapp)
        PersonManager pManager = new PersonManager();
        //Una vez toda la información del chat es alamacenada, se inicia el algoritmo que creará los datos de análisis.
        pManager.startAlgorythm();
    }
}