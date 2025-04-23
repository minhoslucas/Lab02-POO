package lab02.exceptions;

public class LocalIndisponivelException extends Exception {
    public LocalIndisponivelException(String errorMessage){
        super(errorMessage);
    }
}