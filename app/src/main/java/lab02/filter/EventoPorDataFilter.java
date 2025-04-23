package lab02.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;


public class EventoPorDataFilter implements FilterInterface<LocalDate>{

    private LocalDate data;

    public EventoPorDataFilter(LocalDate data){
        this.data = data;
    }

    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException{
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos){
            if(test.getData().equals(this.data)){
                result.add(test);
            }
        }
        if (result.size() == 0){
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        return result;
    }
}