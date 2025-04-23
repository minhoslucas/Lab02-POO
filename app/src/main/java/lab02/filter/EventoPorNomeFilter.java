package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorNomeFilter extends Filtro<String>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    EventoPorNomeFilter(ArrayList<Evento> event_list){
        super(event_list);
    }

    @Override
    public void filter(String nome) throws EventoNaoEncontradoException{
        for (Evento event : this.event_list){
            if (event.getNome().equals(nome)){
                result.add(event);
            }
        }
        if (event_list.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}