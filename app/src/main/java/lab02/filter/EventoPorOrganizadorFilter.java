package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;
import lab02.Organizadora;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorOrganizadorFilter implements FilterInterface<Organizadora>{

    private Organizadora organizadora;

    public EventoPorOrganizadorFilter(Organizadora organizadora) {
        this.organizadora = organizadora;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> event_list) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();
        
        for (Evento test : event_list){
            if(test.getOrganizadora().equals(this.organizadora)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("Evento não encontrado");
        }
        return result;
    }
}