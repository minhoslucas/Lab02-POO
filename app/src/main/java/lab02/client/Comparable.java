package lab02.client;

/**
 * Interface que define a comparação entre dois objetos do tipo Cliente.
 * 
 * @param <T> Tipo genérico que estende Cliente
 */
public interface Comparable<T extends Cliente> {

    /**
     * Compara o cliente atual com outro cliente.
     * 
     * @param cliente Cliente a ser comparado
     * @return true se os clientes forem iguais, false caso contrário
     */
    boolean compareTo(T cliente);
}
