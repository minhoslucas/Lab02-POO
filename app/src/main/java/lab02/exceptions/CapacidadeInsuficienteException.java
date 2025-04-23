package lab02.exceptions;

public class CapacidadeInsuficienteException extends Exception {
    public CapacidadeInsuficienteException(String errorMessage){
        super(errorMessage);
    }
}