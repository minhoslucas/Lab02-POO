package lab02.exceptions;

/**
 * Exceção lançada quando um local não é encontrado.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentáridos feitos por IA e corrigidos posteriormente
 */
public class LocalNaoEncontradoException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro descrevendo a exceção
     */
    public LocalNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}