package lab02.exceptions;

/**
 * Exceção lançada quando uma tentativa de cancelamento não é permitida.
 * 
 * @author Lucas Beserra - 281815  
 * Comentários feitos por IA e revisados posteriormente
 */
public class CancelamentoNaoPermitidoException extends Exception {
    /**
     * Constrói uma nova exceção com a mensagem de erro especificada.
     * 
     * @param errorMessage a mensagem que descreve o erro.
     */
    public CancelamentoNaoPermitidoException(String errorMessage){
        super(errorMessage);
    }
}
