package lab02.exceptions;

/**
 * Exceção lançada quando um evento não é encontrado no sistema.
 * 
 * @author Lucas Beserra - 281815
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class EventoNaoEncontradoException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro descrevendo a situação
     */
    public EventoNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}