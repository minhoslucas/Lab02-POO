package lab02.exceptions;

/**
 * Exceção lançada quando os ingressos para um evento estão esgotados.
 * 
 * @author Lucas Beserra - 281815
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class IngressoEsgotadoException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro indicando que os ingressos estão esgotados
     */
    public IngressoEsgotadoException(String errorMessage) {
        super(errorMessage);
    }
}