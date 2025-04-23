package lab02;

import java.util.ArrayList;

public interface Comparable<T extends Cliente>{
    ArrayList<Ingresso> compareTo(Cliente cliente);
}