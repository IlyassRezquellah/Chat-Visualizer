package users.data;

public class Message
{
    //String que indican en los diferentes idiomas si el mensaje es un documento multimedia
    private static final String MEDIA_ENGLISH = "<Media omitted>";
    private static final String MEDIA_SPANISH = "<Multimedia omitido>";
    private boolean isMedia;
    //Datos basicos de un mensaje
    private Date date;
    private Time time;
    private String text;
    
    public Message(){
        date = null;
        time = null;
        setText("");
        
    }
    
    public Message(int day, int month, int year, int minute, int hour, String text){
        setDate(day, month, year);
        setHout(minute, hour);
        
        //Guarda el texto comprobando si es un documento multimedia
        if(text.equals(MEDIA_ENGLISH) || text.equals(MEDIA_SPANISH)){
            isMedia = true;
            setMoreText("");
        }
        else{
            isMedia = false;
            setMoreText(text);
        }
        setText(text);
    }
    //Geters y setes para La clase Menssage y las de Date y Time
    public Date getDateObj(){
        return date;
    }
    public Time getHourObj(){
        return time;
    }
    public String getDateStr(){
        return date.getFullDate();
    }
    public String getHourStr(){
        return time.getFullTime();
    }
    public String getText(){
        if(!isMedia())
            return text;
        else
            return "Multimedia";
    }
    public int getHour(){
        return time.getHour();
    }
    public int getMinute(){
        return time.getMinute();
    }
    public int getDay(){
        return date.getDay();
    }
    public int getMonth(){
        return date.getMonth();
    }
    public int getYear(){
        return date.getYear();
    }
    public boolean isMedia(){
        return isMedia;
    }
    public void setDate(int dia, int mes, int ano){
        this.date = new Date(dia, mes, ano);
    }
    public void setHout(int minuto, int hora){
        this.time = new Time(minuto, hora);
    }
    public void setText(String text){
        //Guarda el texto comprobando si es un documento multimedia
        if(text.equals(MEDIA_ENGLISH) || text.equals(MEDIA_SPANISH)){
            isMedia = true;
            this.text = "";
        }
        else{
            isMedia = false;
            this.text = text;
        }
    }
    public void setMoreText(String text){
        this.text += ("\n\r" + text);
    }
     public java.util.Date getDate(){
        return date.getDate(); 
    }
}
