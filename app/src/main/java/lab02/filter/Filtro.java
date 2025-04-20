package lab02.filter;

import lab02.exceptions.EventoNaoEncontradoException;

public interface Filtro<T>{
    void filter(T objeto) throws EventoNaoEncontradoException;
} 