package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.events.Organizadora;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorOrganizadoraFilter extends Filter<Organizadora>{

    private Organizadora organizadora;

    public EventoPorOrganizadoraFilter(Organizadora organizadora){
        this.organizadora = organizadora;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos){
            if(test.getOrganizadora().equals(this.organizadora)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        return result;
    }
}