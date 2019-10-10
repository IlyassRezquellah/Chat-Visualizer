//*******************************************************************
// Welcome to CompileJava!
// If you experience any issues, please contact us ('More Info')  -->
//*******************************************************************

import java.lang.Math;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class HelloWorld
{
  
  public static void main(String[] args)
  {
    HelloWorld app = new HelloWorld();
    app.run();
  }
  
  public void run()
  {
    /*import java.lang.Math;
	import java.util.regex.Pattern;
	import java.util.regex.Matcher;*/
    
    String text = "18/01/19, 12:11 PM - Loki: Why/ do you have 2 numbers, Banner?";
    String regex = "^([0-2][0-9]|(3)[0-1])/([0-2][0-9]|(3)[0-1])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s(['A'-'P']['M'])){0,2}\\s-\\s";
    String matches = "/|(?:,|\\s)";

    Pattern pat = Pattern.compile(regex);
    Matcher match = pat.matcher(text);
    
    String[] splitted = text.split(matches, 4);

      for(int i = 1; match.find();++i )
            System.out.println("found"+ match.groupCount() +": " + match.group());
    
    //El split devuelve la linea despues de la fecha y hora 
    //splitted = splitted[splitted.length-1].split(":");
    
    if(text.matches(regex))
    {
      String[] ex = text.split(regex);
		System.out.println(ex[0]);
    	//splitted = pat.split(text);
    }
    
    /*for(int i = 0, t = splitted.length; i < t;++i )
    	System.out.println(splitted[i] + "\n");*/
  }
}

