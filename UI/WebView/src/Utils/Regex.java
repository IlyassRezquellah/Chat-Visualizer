package Utils;

public class Regex{
    ///Expresiones regulares usadas para comprobar si son mensajes de una persona, información de whatsapp o si es una linea suelta de otro mensaje
    //Analiza la string del mensaje
    public static final String COUNT_WORDS_CHARS = "[\\s\\t|,|;|\\.|\\?|\\¿|¡|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/]+";
    
    //Regex usada para comprobar el patrón de (Fecha - Hora - Nombre - Mensaje), que podemos encontrar en un mensaje
    public static final String VALIDATE_MENSSAGE = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])(\\s(['A'-'P']['M'])){0,2}\\s-\\s(.*?):\\s(.+)";
    
    //Regex usada para comprobar el partón de (Fecha - Hora): Con esta determino si es una lina suelta (dará false) o un mensaje informativo de whatsapp (dará true).
    //Los mensajes de whatsapp nunca tienen un nombre solo (Fecha - Hora - Mensaje).
    public static final String VALIDATE_DATE_TIME = "^([0-3][0-9]|[0-9])/([0-3][0-9]|[0-9])/(.{2}|.{4})(,\\s|\\s)([0-2]{0,1}[0-9]):([0-6]{0,1}[0-9])\\s(.+)";
    
}
