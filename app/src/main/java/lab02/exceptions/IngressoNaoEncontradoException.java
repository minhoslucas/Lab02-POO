package lab02.exceptions;

/**
 * Exceção lançada quando um ingresso não é encontrado.
 * 
 * @author Lucas Beserra - 281815
 * Comentários feitos por IA e revisados posteriormente
 */
public class IngressoNaoEncontradoException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param errorMessage Mensagem de erro descrevendo a situação
     */
    public IngressoNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}