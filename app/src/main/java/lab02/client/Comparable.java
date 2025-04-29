package lab02.client;

public interface Comparable<T extends Cliente>{
    boolean compareTo(Cliente cliente);
}