/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.util.ArrayList;

import lab02.Cliente;
import lab02.Ingresso;
import lab02.Local;
import lab02.Organizadora;
import lab02.exceptions.LocalNaoEncontradoException;
import java.time.LocalDate;

public abstract class Evento {
    private String nome;
    private Local local;
    private int capacidade;
    private double precoIngresso; // preço base do ingresso
    private Organizadora organizadora;
    private LocalDate data;
    private ArrayList<Ingresso> listaIngressos;

    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param local o local associado ao Evento
     */
    public Evento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data) {
        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
        this.precoIngresso = precoIngresso; // modificar para representar o preço base do ingresso
        this.organizadora = organizadora;
        this.data = data;
        this.listaIngressos = new ArrayList<Ingresso>();

    }
    private boolean isFull(){
        return this.listaIngressos.size() + 1 > this.local.getCapacidade();
    }
    /**
     * Retorna o nome do Evento
     * @return o nome do Evento
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do Evento para `nome` 
     * @param nome o novo nome do Evento
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o Local do Evento
     * @return o local do Evento
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Altera o local do Evento para `local` 
     * @param local o novo local do Evento
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return this.precoIngresso;
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

    /**
     * Retorna a data do Evento
     * @return a data do Evento
     */
    public LocalDate getData() {
        return data;
    }

    public Organizadora getOrganizadora(){
        return this.organizadora;
    }

    public String descricao(){
        return "Evento: " + this.nome + " - Local: " + this.local;
    }
}