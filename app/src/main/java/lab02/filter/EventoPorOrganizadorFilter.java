package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.Organizadora;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorOrganizadorFilter extends Filtro<Organizadora>{
    private ArrayList<Evento> event_list;
    private ArrayList<Evento> result;

    EventoPorOrganizadorFilter(ArrayList<Evento> event_list){
        super(event_list);
    }

    @Override
    public void filter(Organizadora org) throws EventoNaoEncontradoException{
        for (Evento event : this.event_list){
            if (event.getOrganizadora().equals(org)){
                result.add(event);
            }
        }
        if (event_list.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}