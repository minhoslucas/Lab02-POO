package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public abstract class Filtro<T> implements FilterInterface<T>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    public Filtro(ArrayList<Evento> event_list){
        this.event_list = event_list;
        this.result = new ArrayList<Evento>();
    }

    public ArrayList<Evento> getResult(){
        return this.result;
    }

    public void setEventList(ArrayList<Evento> event_list){
        this.event_list = event_list;
    }

    public abstract void filter(T criteria) throws EventoNaoEncontradoException;
}