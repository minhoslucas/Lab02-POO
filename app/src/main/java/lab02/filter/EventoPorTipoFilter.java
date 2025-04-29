package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorTipoFilter extends Filter<Class<?>>{

    private Class<?> classe;

    public EventoPorTipoFilter(Class<?> classe){
        this.classe = classe;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos){
            if(this.classe.isInstance(test)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        return result;
    }
}