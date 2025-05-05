package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro que seleciona eventos com base no tipo/classe especificado.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoPorTipoFilter extends Filter<Class<?>> {

    private Class<?> classe;

    /**
     * Construtor do filtro por tipo de evento.
     * @param classe A classe/interface que define o tipo de evento a ser filtrado
     */
    public EventoPorTipoFilter(Class<?> classe) {
        this.classe = classe;
    }

    /**
     * Filtra a lista de eventos, retornando apenas os do tipo especificado.
     * 
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos do tipo especificado
     * @throws EventoNaoEncontradoException Se nenhum evento do tipo for encontrado
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException {
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos) {
            if(this.classe.isInstance(test)) {
                result.add(test);
            }
        }
        
        if (result.size() == 0) {
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        
        return result;
    }
}
