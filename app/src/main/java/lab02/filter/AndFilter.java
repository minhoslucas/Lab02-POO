package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

class AndFilter{
    private ArrayList<Evento> result;
    private ArrayList<Evento> event_list;

    public AndFilter(ArrayList<Evento> event_list){
        this.event_list = event_list;
        this.result = new ArrayList<Evento>();
    }

    public ArrayList<Evento> getResult(){
        return this.result;
    }
    public void filter(Evento evento, boolean data, boolean local, boolean nome, boolean org, boolean tipo) throws EventoNaoEncontradoException{
        ArrayList<Evento> current = this.event_list;

        if(data){
            EventoPorDataFilter date_filter = new EventoPorDataFilter(current);
            date_filter.filter(evento.getData());
            current = date_filter.getResult();
        }

        if(local){
            EventoPorLocalFilter local_filter = new EventoPorLocalFilter(current);
            local_filter.filter(evento.getLocal());
            current = local_filter.getResult(); 
        }

        if(nome){
            EventoPorNomeFilter name_filter = new EventoPorNomeFilter(current);
            name_filter.filter(evento.getNome());
            current = name_filter.getResult(); 
        }

        if(org){
            EventoPorOrganizadorFilter name_filter = new EventoPorOrganizadorFilter(current);
            name_filter.filter(evento.getOrganizadora());
            current = name_filter.getResult(); 
        }

        if(tipo){
            EventoPorTipoFilter type_filter = new EventoPorTipoFilter(current);
            type_filter.filter(evento.getClass());
            current = type_filter.getResult(); 
        }

        if (current.size() == 0){
            throw new EventoNaoEncontradoException("Evento Não Encontrado");
        }
    }
}