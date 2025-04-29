package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public class EventoPorNomeFilter extends Filter<String>{

    private String nome;

    public EventoPorNomeFilter(String nome){
        this.nome = nome;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos){
            if(test.getNome().equals(this.nome)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        return result;
    }
}