package lab02.exceptions;

/**
 * Exceção lançada quando uma operação falha devido à capacidade insuficiente.
 * 
 * @author Lucas Beserra - 281815  
 * Comentários feitos por IA e revisados posteriormente
 */
public class CapacidadeInsuficienteException extends Exception {
    /**
     * Constrói uma nova exceção com a mensagem de erro especificada.
     * 
     * @param errorMessage a mensagem que descreve o erro.
     */
    public CapacidadeInsuficienteException(String errorMessage){
        super(errorMessage);
    }
}
