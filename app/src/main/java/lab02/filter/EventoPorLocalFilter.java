package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.events.Local;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorLocalFilter implements FilterInterface<Local>{

    private Local local;

    public EventoPorLocalFilter(Local local){
        this.local = local;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos){
            if(test.getLocal().equals(this.local)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        return result;
    }
}