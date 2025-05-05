package lab02.exceptions;

/**
 * Exceção lançada quando um local não está disponível para alocação.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class LocalIndisponivelException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro descrevendo a situação
     */
    public LocalIndisponivelException(String errorMessage) {
        super(errorMessage);
    }
}