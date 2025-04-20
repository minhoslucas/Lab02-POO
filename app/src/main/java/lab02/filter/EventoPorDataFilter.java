package lab02.filter;

import java.time.LocalDate;
import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

class EventoPorLocalFilter implements Filtro<LocalDate>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    EventoPorLocalFilter(ArrayList<Evento> event_list){
        this.result = new ArrayList<Evento>();
        this.event_list = event_list;
    }

    public ArrayList<Evento> getResult(){
        return this.result;
    }

    public void filter(LocalDate date) throws EventoNaoEncontradoException{
        for (Evento event : event_list){
            if (event.getData().equals(date)){
                result.add(event);
            }
        }
        if (event_list.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}