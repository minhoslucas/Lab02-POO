package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorTipoFilter extends Filtro<Class<?>>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    EventoPorTipoFilter(ArrayList<Evento> event_list){
        super(event_list);
    }

    @Override
    public void filter(Class<?> classe) throws EventoNaoEncontradoException{
        for (Evento event : this.event_list){
            if (classe.isInstance(event)){
                result.add(event);
            }
        }
        if (event_list.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}