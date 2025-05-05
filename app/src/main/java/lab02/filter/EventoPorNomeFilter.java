package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro que seleciona eventos com base no nome especificado.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoPorNomeFilter extends Filter<String> {

    private String nome;

    /**
     * Construtor do filtro por nome de evento.
     * @param nome O nome do evento que será usado como critério de filtro
     */
    public EventoPorNomeFilter(String nome) {
        this.nome = nome;
    }

    /**
     * Filtra a lista de eventos, retornando apenas os eventos com o nome especificado.
     * 
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos com o nome especificado
     * @throws EventoNaoEncontradoException Se nenhum evento com o nome for encontrado
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException {
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos) {
            if(test.getNome().equals(this.nome)) {
                result.add(test);
            }
        }
        
        if (result.size() == 0) {
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        
        return result;
    }
}