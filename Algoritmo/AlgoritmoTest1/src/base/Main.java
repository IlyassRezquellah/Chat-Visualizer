package base;

import users.PersonManager;

public class Main{
    public static void main(String[] args){
        Main app = new Main();
        app.run();
    }
    public void run(){
        PersonManager pManager = new PersonManager();
        pManager.startAlgorythm();
    }
}