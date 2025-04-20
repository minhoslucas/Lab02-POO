package lab02.exceptions;

public class IngressoEsgotadoException extends Exception {
    public IngressoEsgotadoException(String errorMessage){
        super(errorMessage);
    }
}