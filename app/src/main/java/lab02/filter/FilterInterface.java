package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Interface que define o contrato para implementação de filtros de eventos.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários criados por IA e corrigidos posteriormente
 */
public interface FilterInterface<T> {
    
    /**
     * Filtra uma lista de eventos de acordo com critérios específicos.
     * 
     * @param event_list A lista de eventos a ser filtrada
     * @return Uma lista contendo apenas os eventos que atendem aos critérios do filtro
     * @throws EventoNaoEncontradoException Se nenhum evento for encontrado após a filtragem
     */
    ArrayList<Evento> filter(ArrayList<Evento> event_list) throws EventoNaoEncontradoException;
}