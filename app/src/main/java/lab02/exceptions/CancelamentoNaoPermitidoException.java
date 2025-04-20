package lab02.exceptions;

public class CancelamentoNaoPermitidoException extends Exception {
    public CancelamentoNaoPermitidoException(String errorMessage){
        super(errorMessage);
    }
}