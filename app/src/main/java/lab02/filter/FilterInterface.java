package lab02.filter;

import lab02.exceptions.EventoNaoEncontradoException;

public interface FilterInterface<T>{
    void filter(T objeto) throws EventoNaoEncontradoException;
} 
