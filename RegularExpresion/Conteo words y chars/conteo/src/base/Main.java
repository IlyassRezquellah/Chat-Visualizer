
package base;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Main {
          public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //El metodo trim() quita el primer espacio --> " Hola" pasaria a ser "Hola"
        String s = scan.nextLine().trim();
        //String[] arrOfStr = s.split("[\\s\\W]+");
        String[] arrOfStr = s.split("[\\s\\t|,|;|\\.|\\?|\\¿|¡|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/]+"); 
        
        
        //System.out.println(words2.size());
        //System.out.println(arrOfStr.length);
        int count = 0;
        //Print de todas las palabras separadas
        for (String a : arrOfStr) {
            System.out.println(a); 
        }
        
        //Contador de chars
        for(int counter = 0; counter < arrOfStr.length; counter++) {
            count +=arrOfStr[counter].length();
        }
        
        System.out.println("String --> " + s);
        if((s.trim().length() == 0)){
            System.out.println(0);
        }else{
            System.out.println("Suma de todas las palabras = " +arrOfStr.length);
        }
        System.out.println("Suma de todos los chars = " + count);
        scan.close();
        //------------------------------------
        //Cosas a tener en cuenta
        //Caso 1 -->
        //Una string con ":3" contaria 
        
    }
}
