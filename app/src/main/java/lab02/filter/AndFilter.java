package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro composto que aplica dois filtros em sequência (operador AND lógico).
 * Retorna apenas os eventos que satisfazem ambos os filtros.
 * 
 * @author Lucas Beserra -281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class AndFilter extends Filter<Evento> {
    
    private Filter<?> filter_1;
    private Filter<?> filter_2;

    /**
     * Construtor do filtro AND composto.
     * @param filter_1 Primeiro filtro a ser aplicado
     * @param filter_2 Segundo filtro a ser aplicado
     */
    public AndFilter(Filter<?> filter_1, Filter<?> filter_2) {
        this.filter_1 = filter_1;
        this.filter_2 = filter_2;
    }

    /**
     * Aplica os dois filtros em sequência.
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos que passaram em ambos os filtros
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) {
        ArrayList<Evento> result;
        try {
            result = this.filter_1.filter(eventos);
            result = this.filter_2.filter(result);
        } catch (EventoNaoEncontradoException e) {
            result = new ArrayList<Evento>();
            System.out.println(e.getMessage());
        }
        return result;
    }
}