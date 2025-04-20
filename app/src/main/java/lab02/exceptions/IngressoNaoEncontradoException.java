package lab02.exceptions;

public class IngressoNaoEncontradoException extends Exception {
    public IngressoNaoEncontradoException(String errorMessage){
        super(errorMessage);
    }
}