package lab02.client;

import java.util.ArrayList;

import lab02.tickets.Ingresso;

public interface Comparable<T extends Cliente>{
    ArrayList<Ingresso> compareTo(Cliente cliente);
}