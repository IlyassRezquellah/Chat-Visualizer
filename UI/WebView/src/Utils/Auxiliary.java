package Utils;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Auxiliary{
    public static String chatPath = "/";
    public static final String jSDataPatch = "web/javaScript/data.js";
    //Este metodo nos devuelve un int con el número total de día en un mes, mediante un año y un mes.
    public static int getNumberDaysGivenYear(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }
    public static String purge[]= {
        /*Preposiciones*/
        "a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "según", "sin", "sobre", "tras", "versus", "vía",
        /*Conjunciones*/
        "mas","ni","o","ora","pero","sino","siquiera","u","y","aunque","como","conque","cuando","donde","entonces","ergo","e","empero","incluso","luego","mientras","porque","pues","que","sea","si","ya","adonde","aun","como","conque"
    };
    public static void countAndPrintRepeatedWordOccurences(
            String strContent) {
 
        // Step 1: create Map of String-Integer
        Map<String, Integer> mapOfRepeatedWord = 
                new HashMap<String, Integer>();
 
        // Step 2: split line using space as delimiter
        strContent = strContent.toLowerCase().replaceAll("[^a-zñáéíóú0-9 ]", "");
        for (int i = 0, t=Utils.Auxiliary.purge.length; i < t; i++) {
            strContent = strContent.replace(" "+Utils.Auxiliary.purge[i]+" ", " ");
            
        }
        String[] words = strContent.split(" ");
 
        // Step 3: iterate through String[] array
        for(String word : words) {
 
            // Step 4: convert all String into lower case, 
            // before comparison
            String tempUCword = word;
 
            // Step 5: check whether Map contains particular word 
            if(mapOfRepeatedWord.containsKey(tempUCword)){
 
                // Step 6: If contains, increase count value by 1
                mapOfRepeatedWord.put(tempUCword, 
                        mapOfRepeatedWord.get(tempUCword) + 1);
            } 
            else {
 
                // Step 7: otherwise, make a new entry
                mapOfRepeatedWord.put(tempUCword, 1);
            }
        }
 
        /*System.out.println("Before sorting : \n");
        System.out.println("Words" + "\t\t" + "Count");
        System.out.println("======" + "\t\t" + "=====");*/
 
        // Step 8: print word along with its count
        /*for(Map.Entry<String, Integer> entry : 
            mapOfRepeatedWord.entrySet()){
            System.out.println(entry.getKey() 
                    + "\t\t" + entry.getValue());
        }*/
 
        // Step 9: Sorting logic by invoking sortByCountValue()
        Map<String, Integer> wordLHMap = sortByCountValue(
                mapOfRepeatedWord);
 
        System.out.println("\n\nAfter sorting"
                + " in descending order of count : \n");
        System.out.println("Words" + "\t\t" + "Count");
        System.out.println("======" + "\t\t" + "=====");
 
        // Step 10: Again print after sorting
        for(Map.Entry<String, Integer> entry : 
            wordLHMap.entrySet()) {
            System.out.println(entry.getKey() 
                    + "\t\t" + entry.getValue());
        }
    }
 
     /**
     * this method sort acc. to count value
     * @param mapOfRepeatedWord
     * @return
     */
    
    public static Map<String, Integer> sortByCountValue(
            Map<String, Integer> mapOfRepeatedWord) {
 
        // get entrySet from HashMap object
        Set<Map.Entry<String, Integer>> setOfWordEntries = 
                mapOfRepeatedWord.entrySet();
 
        // convert HashMap to List of Map entries
        List<Map.Entry<String, Integer>> listOfwordEntry = 
                new ArrayList<Map.Entry<String, Integer>>(
                        setOfWordEntries);
 
        // sort list of entries using Collections.sort(ls, cmptr);
        
        Collections.sort(listOfwordEntry, 
                new Comparator<Map.Entry<String, Integer>>() {
 
            @Override
            public int compare(Entry<String, Integer> es1, 
                    Entry<String, Integer> es2) {
                return es2.getValue().compareTo(es1.getValue());
            }
        });
 
        // store into LinkedHashMap for maintaining insertion
        Map<String, Integer> wordLHMap = 
                new LinkedHashMap<String, Integer>();
 
        // iterating list and storing in LinkedHahsMap
        for(Map.Entry<String, Integer> map : listOfwordEntry){
            wordLHMap.put(map.getKey(), map.getValue());
        }
 
        return wordLHMap;
    }
    
}
