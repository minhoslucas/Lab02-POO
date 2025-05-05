package lab02.filter;

import java.util.ArrayList;

import lab02.events.Evento;
import lab02.events.Organizadora;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro que seleciona eventos com base na organizadora especificada.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoPorOrganizadoraFilter extends Filter<Organizadora> {

    private Organizadora organizadora;

    /**
     * Construtor do filtro por organizadora.
     * @param organizadora A organizadora que será usada como critério de filtro
     */
    public EventoPorOrganizadoraFilter(Organizadora organizadora) {
        this.organizadora = organizadora;
    }

    /**
     * Filtra a lista de eventos, retornando apenas os organizados pela organizadora especificada.
     * 
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos da organizadora especificada
     * @throws EventoNaoEncontradoException Se nenhum evento da organizadora for encontrado
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException {
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos) {
            if(test.getOrganizadora().equals(this.organizadora)) {
                result.add(test);
            }
        }
        
        if (result.size() == 0) {
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        
        return result;
    }
}
