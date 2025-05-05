package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.events.Local;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro que seleciona eventos com base no local especificado.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoPorLocalFilter extends Filter<Local> {

    private Local local;

    /**
     * Construtor do filtro por local de evento.
     * @param local O local que será usado como critério de filtro
     */
    public EventoPorLocalFilter(Local local) {
        this.local = local;
    }

    /**
     * Filtra a lista de eventos, retornando apenas os eventos realizados no local especificado.
     * 
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos do local especificado
     * @throws EventoNaoEncontradoException Se nenhum evento no local for encontrado
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException {
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos) {
            if(test.getLocal().equals(this.local)) {
                result.add(test);
            }
        }
        
        if (result.size() == 0) {
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        
        return result;
    }
}
