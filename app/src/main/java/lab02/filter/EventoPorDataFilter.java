package lab02.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import lab02.events.Evento;
import lab02.exceptions.EventoNaoEncontradoException;

/**
 * Filtro que seleciona eventos com base na data especificada.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoPorDataFilter extends Filter<LocalDate> {

    private LocalDate data;

    /**
     * Construtor do filtro por data de evento.
     * @param data A data que será usada como critério de filtro
     */
    public EventoPorDataFilter(LocalDate data) {
        this.data = data;
    }

    /**
     * Filtra a lista de eventos, retornando apenas os eventos que ocorrem na data especificada.
     * 
     * @param eventos Lista de eventos a ser filtrada
     * @return Lista contendo apenas eventos da data especificada
     * @throws EventoNaoEncontradoException Se nenhum evento na data for encontrado
     */
    @Override
    public ArrayList<Evento> filter(ArrayList<Evento> eventos) throws EventoNaoEncontradoException {
        ArrayList<Evento> result = new ArrayList<Evento>();

        for (Evento test : eventos) {
            if(test.getData().equals(this.data)) {
                result.add(test);
            }
        }
        
        if (result.size() == 0) {
            throw new EventoNaoEncontradoException("EVENTO NÃO ENCONTRADO");
        }
        
        return result;
    }
}