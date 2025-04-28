/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.time.LocalDate;
import java.util.ArrayList;

import lab02.client.Cliente;
import lab02.exceptions.LocalNaoEncontradoException;
import lab02.tickets.Ingresso;

public abstract class Evento{

    private String nome;
    private Local local;
    private LocalDate data;
    private Organizadora organizadora;
    private int capacidade;
    private double precoIngresso; // preço base do ingresso
    private ArrayList<Ingresso> listaIngressos;

    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param local o local associado ao Evento
     */
    public Evento(double precoIngresso, String nome, Local local, int capacidade, LocalDate data, Organizadora organizadora) {
        this.data = data;
        this.organizadora = organizadora;
        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
        this.precoIngresso = precoIngresso; // modificar para representar o preço base do ingresso
        this.listaIngressos = new ArrayList<Ingresso>();

    }
    private boolean isFull(){
        return this.listaIngressos.size() + 1 > this.local.getCapacidade();
    }

    /**
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return this.precoIngresso;
    }

    public String getNome(){
        return this.nome;
    }

    public Local getLocal(){
        return this.local;
    }

    public void setLocal(Local local){
        this.local = local;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public LocalDate getData(){
        return this.data;
    }

    public Organizadora getOrganizadora(){
        return this.organizadora;
    }

    /**
     * Altera o precoIngresso do Evento para `precoIngresso` 
     * @param precoIngresso o novo precoIngresso do Evento
     */
    public void setPrecoIngresso(double precoIngresso){
        this.precoIngresso = precoIngresso;
    }

    public void venderIngresso(Cliente cliente, Ingresso ingresso) throws LocalNaoEncontradoException {
        if (ingresso.getEvento().isFull()) {
            throw new LocalNaoEncontradoException("EVENTO LOTADO");
        }
        cliente.adicionarIngresso(ingresso);
        System.out.println("Ingresso Vendido com Sucesso!");
    }

    public void descricao(){
        System.out.println("Evento: " + this.nome + " - Local: " + this.local);
    }
}