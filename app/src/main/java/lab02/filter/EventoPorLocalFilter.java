package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.Local;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorLocalFilter extends Filtro<Local>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    EventoPorLocalFilter(ArrayList<Evento> event_list){
        super(event_list);
    }

    @Override
    public void filter(Local local) throws EventoNaoEncontradoException{
        for (Evento event : this.event_list){
            if (event.getLocal().equals(local)){
                result.add(event);
            }
        }
        if (event_list.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}