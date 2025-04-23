package lab02.filter;

import java.util.ArrayList;

import lab02.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

public interface FilterInterface<T extends Evento>{
    void filter(ArrayList<Evento> event_list) throws EventoNaoEncontradoException;
} 
