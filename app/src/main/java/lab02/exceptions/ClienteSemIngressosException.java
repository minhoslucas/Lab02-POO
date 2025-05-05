package lab02.exceptions;

/**
 * Exceção lançada quando um cliente não possui ingressos associados.
 * 
 * @author Lucas Beserra - 281815
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class ClienteSemIngressosException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro indicando que o cliente não possui ingressos
     */
    public ClienteSemIngressosException(String errorMessage) {
        super(errorMessage);
    }
}