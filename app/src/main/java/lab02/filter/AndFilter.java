package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public class AndFilter implements FilterInterface<Evento>{
    
    private FilterInterface<Evento> filter_1;
    private FilterInterface<Evento> filter_2;

    public AndFilter(FilterInterface<Evento> filter_1, FilterInterface<Evento> filter_2){
        this.filter_1 = filter_1;
        this.filter_2 = filter_2;
    }
    

    public ArrayList<Evento> filter(ArrayList<Evento> eventos){
        ArrayList<Evento> result;
        try{
            result = this.filter_1.filter(eventos);
            result = this.filter_2.filter(result);
        }catch (EventoNaoEncontradoException e){
            result = new ArrayList<Evento>();
            System.out.println(e.getMessage());
        }
        return result;
    }
}