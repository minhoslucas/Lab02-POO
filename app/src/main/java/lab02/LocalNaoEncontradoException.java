package lab02;

public class LocalNaoEncontradoException extends Exception {
    public LocalNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}