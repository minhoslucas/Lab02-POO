package lab02.exceptions;

public class LocalNaoEncontradoException extends Exception {
    public LocalNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}