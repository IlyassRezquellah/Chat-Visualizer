package algoritmos;

import java.util.ArrayList;
import java.util.List;
import usuarios.Persona;
//Algoritmo de conteo, medias y totales. Pero CMT mola más!
public class AlgoritomoCMT
{//Hay que hacer todo... ArrayList? estos ints no servirán para nada así! Clases autogestionadas?
    private List<Persona> personas;
    private int m_PalabrasDia;
    private int m_PalabrasMensaje;
    private int m_RangoHoraMasHablada;
    private int c_MensajesTotales;
    private int c_PalabrasTotales;
    private int c_CaracteresTotales;
    private int t_DiasHablados;
    private int t_DiasNoHablados;
    
    public AlgoritomoCMT(List<Persona> personasOrigen)
    {
        personas = personasOrigen;
        m_PalabrasDia = 0;
        m_PalabrasMensaje = 0;
        m_RangoHoraMasHablada = 0;
        c_MensajesTotales = 0;
        c_PalabrasTotales = 0;
        c_CaracteresTotales = 0;
        t_DiasHablados = 0;
        t_DiasNoHablados = 0;
    }
    
    public boolean calcular()
    {
        try
        {
            //do somthing
        } 
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }       
        return true;
    }
    
}
