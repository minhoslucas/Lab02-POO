/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.time.LocalDate;
 import java.util.ArrayList;
 
 import lab02.client.Cliente;
 import lab02.exceptions.LocalNaoEncontradoException;
 import lab02.tickets.Ingresso;
 
 /**
  * Classe abstrata que representa um evento genérico.
  * Contém informações básicas como nome, local, data e capacidade.
  * 
  * @author Lucas Beserra - 281815
  *
  * Comentarios feitos por IA
  */
 public abstract class Evento {
 
     /** Nome do evento */
     private String nome;
     
     /** Local onde o evento será realizado */
     private Local local;
     
     /** Data do evento */
     private LocalDate data;
     
     /** Organizadora responsável pelo evento */
     private Organizadora organizadora;
     
     /** Capacidade máxima do evento */
     private int capacidade;
     
     /** Preço base do ingresso */
     private double precoIngresso;
     
     /** Lista de ingressos vendidos */
     private ArrayList<Ingresso> listaIngressos;
 
     /**
      * Construtor da classe Evento
      * @param precoIngresso Preço base do ingresso
      * @param nome Nome do evento
      * @param local Local do evento
      * @param capacidade Capacidade máxima
      * @param data Data do evento
      * @param organizadora Organizadora responsável
      */
     public Evento(double precoIngresso, String nome, Local local, int capacidade, LocalDate data, Organizadora organizadora) {
         this.data = data;
         this.organizadora = organizadora;
         this.nome = nome;
         this.local = local;
         this.capacidade = capacidade;
         this.precoIngresso = precoIngresso;
         this.listaIngressos = new ArrayList<Ingresso>();
     }
 
     /**
      * Verifica se o evento está lotado
      * @return true se o evento estiver lotado, false caso contrário
      */
     private boolean isFull() {
         return this.listaIngressos.size() + 1 > this.local.getCapacidade();
     }
 
     /**
      * Retorna o preço do ingresso
      * @return Preço do ingresso
      */
     public double getPrecoIngresso() {
         return this.precoIngresso;
     }
 
     /**
      * Retorna o nome do evento
      * @return Nome do evento
      */
     public String getNome() {
         return this.nome;
     }
 
     /**
      * Retorna o local do evento
      * @return Local do evento
      */
     public Local getLocal() {
         return this.local;
     }
 
     /**
      * Define o local do evento
      * @param local Novo local do evento
      */
     public void setLocal(Local local) {
         this.local = local;
     }
 
     /**
      * Retorna a capacidade do evento
      * @return Capacidade do evento
      */
     public int getCapacidade() {
         return this.capacidade;
     }
 
     /**
      * Retorna a data do evento
      * @return Data do evento
      */
     public LocalDate getData() {
         return this.data;
     }
 
     /**
      * Retorna a organizadora do evento
      * @return Organizadora do evento
      */
     public Organizadora getOrganizadora() {
         return this.organizadora;
     }
 
     /**
      * Define o preço do ingresso
      * @param precoIngresso Novo preço do ingresso
      */
     public void setPrecoIngresso(double precoIngresso) {
         this.precoIngresso = precoIngresso;
     }
 
     /**
      * Realiza a venda de um ingresso para um cliente
      * @param cliente Cliente que está comprando o ingresso
      * @param ingresso Ingresso a ser vendido
      * @throws LocalNaoEncontradoException Se o evento estiver lotado
      */
     public void venderIngresso(Cliente cliente, Ingresso ingresso) throws LocalNaoEncontradoException {
         if (ingresso.getEvento().isFull()) {
             throw new LocalNaoEncontradoException("EVENTO LOTADO");
         }
         cliente.adicionarIngresso(ingresso);
         System.out.println("Ingresso Vendido com Sucesso!");
     }
 
     /**
      * Exibe a descrição básica do evento
      */
     public void descricao() {
         System.out.println("Evento: " + this.nome + " - Local: " + this.local);
     }
 }