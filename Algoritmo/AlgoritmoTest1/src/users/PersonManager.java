package users;

import Utils.enums.EnumMonths;
import algorithms.matrioshka.Day;
import algorithms.matrioshka.Hour;
import algorithms.matrioshka.Month;
import algorithms.matrioshka.Year;
import interpreters.InterpreterWhatsapp;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import users.Person;

public class PersonManager{
    //Variable estatica con la ruta del chat a analizar
    private static String filePath = "..\\..\\Chats\\ChatBase(total).txt";
    InterpreterWhatsapp InterWhatsapp;
    List<Person> persons;
    
    public PersonManager(){
        //Crear más adelante una clases que gestione todo esto
        //Inicializamos la clase interprete que analizará y devolverá el chat de whatsapp
        InterWhatsapp = new InterpreterWhatsapp(filePath);
        //Inicializamos la lista de personas con todos sus mensajes así como la información pertinente del chat
        persons = InterWhatsapp.chatInterpret();
    }
    
    public void startAlgorythm(){
        //Pruebas del algoritmo
        for (int i = 0, t = persons.size(); i < t; i++){
            //Inicia el algoritmo de conteo y medias (Cada clase Person, le enviará sus mensajes uno a uno para que los analice y guarde los datos deseados)
            persons.get(i).startAlgorythm();
            //Crea un log sencillo con todos los años, así como los meses, días y horas que tiene cada uno
            persons.get(i).createLogOfTheMatrioshkaStructure();
        }
        //Calculos post almacenaje de mensajes
        
        
        //Exportar/crear los json necesarios para las gráficas una vez ya tenemos todos los números
        exportJSons();
    }
    //Creación y expotación de ficheros JSon
    //Guardaremos el número total de personas acutales para reutilizarlo sin llamar al metodo
    private int totalPersons;
    //Creamos un array de cada "Persona" almacenando su extructura matrishka con todos los datos
    private LinkedHashMap<Integer, Year>[] personsMatrishka;
    public void exportJSons(){
        totalPersons = persons.size();
        personsMatrishka = new LinkedHashMap[totalPersons];
        //Obtenermos la matrioshka de todas las personas
        for (int i = 0, t = totalPersons; i < t; i++)
            personsMatrishka[i] = persons.get(i).getMatrioshka();
        
        //JSon de conteos básicos
        jSonCountMessages();
        
    }
    
    /*
    {
    "date": "2012-07-27",
    "value": 13
    }, 
    */
    public void jSonCountMessages(){
        String beginingDate = "{\n\"date\": \"";
        StringBuilder jSonFile = new StringBuilder();
        int counterMonths;
        for(HashMap.Entry<Integer, Year> y : personsMatrishka[0].entrySet()){
            counterMonths = 1;
            for(HashMap.Entry<String, Month> m : personsMatrishka[0].get(y.getKey()).getAllMonths().entrySet()){
                                
                for(Day d : personsMatrishka[0].get(y.getKey()).getOneMonth(m.getKey()).getDays()){
                    
                    jSonFile.append(beginingDate + y.getKey() + "-" + String.format("%02d", counterMonths) + "-" + d.getNameString() + "\",\n");
                    //Limite de personas
                    for (int i = 0; i < totalPersons; i++){
                        if(i == totalPersons-1)
                            jSonFile.append("\"" + persons.get(i).getName() + "\": " + personsMatrishka[i].get(y.getKey()).getOneMonth(m.getKey()).getOneDay(d.getArrayName()).getMessageCount() + "\n");
                        else
                            jSonFile.append("\"" + persons.get(i).getName() + "\": " + personsMatrishka[i].get(y.getKey()).getOneMonth(m.getKey()).getOneDay(d.getArrayName()).getMessageCount() + ",\n");
                            
                    }
                    
                    jSonFile.append("}, ");
                    /*for (Hour h : d.getHours()){
                        jSonFile.append(y.getKey() + "/" + m.getKey() + "/" + d.getName() + " - " + h.getName() + ":00\n");
                        //System.out.println(y.getKey() + "/" + m.getKey() + "/" + d.getName() + "\n");
                        for (int i = 0; i < totalPersons; i++){
                            jSonFile.append(persons.get(i).getName() + ": " + personsMatrishka[i].get(y.getKey()).getOneMonth(m.getKey()).getOneDay(d.getArrayName()).getOneHour(h.getName()).getMessageCount() + "\n");
                            //System.out.println(persons.get(i).getName() + ": " + personsMatrishka[i].get(y.getKey()).getOneMonth(m.getKey()).getOneDay(d.getArrayName()).getOneHour(h.getName()).getMessageCount() + "\n");
                        }
                    }*/
                }
                counterMonths++;
                if(m.getKey().equals(EnumMonths.DECEMBER))
                    counterMonths = 1;
            }

        }
        //Elimina la última coma innecesaria
        jSonFile.setLength(jSonFile.length() - 2);
        //jSonFile.append(conf);
        //System.out.println(jSonFile.toString());
        try(FileOutputStream oFile = new FileOutputStream("charMessageCount.txt", false)){
            oFile.write(jSonFile.toString().getBytes());
        } 
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    //Configuraciones de gráficos
    private static final String conf = "\nhelloConf;";

}
