/*
 * Ingresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.tickets;

import lab02.events.Evento;

/**
 * Contém a estrutura de implementação de um Ingresso.
 */
public abstract class Ingresso {

    private Evento evento;
    private double preco;
    private boolean is_cancelable;

    /**
     * Construtor da classe Ingresso
     * @param preco o preço do Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public Ingresso(Evento evento, double preco, boolean is_cancelable) {
        this.evento = evento;
        this.preco = preco;
        this.is_cancelable = is_cancelable;
    }

    /**
     * Retorna o preço base do Ingresso
     * @return o preço base do Ingresso
     */
    public double getPrecoBase(){
        return this.preco;
    }

    public abstract double getPreco();

    /**
     * Define o evento associado ao Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    /**
     * Retorna o evento associado ao Ingresso
     * @return o evento associado ao Ingresso
     */
    public Evento getEvento() {
        return this.evento;
    }

    public boolean isCancelable() {
        return this.is_cancelable;
    }
    
    public void setCancelable(boolean is_cancelable) {
        this.is_cancelable = is_cancelable;
    }

    @Override

    public String toString(){
        return "Ingresso para: " + this.evento.getNome() + "\n" +
        "Preço: " + this.preco;
    }
}
