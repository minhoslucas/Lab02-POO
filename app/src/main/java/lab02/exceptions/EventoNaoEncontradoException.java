package lab02.exceptions;

public class EventoNaoEncontradoException extends Exception {
    public EventoNaoEncontradoException(String errorMessage){
        super(errorMessage);
    }
}