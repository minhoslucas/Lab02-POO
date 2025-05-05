/*
 * Ingresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos
 */

 package lab02.tickets;

 import lab02.events.Evento;
 
 /**
  * Classe abstrata que representa a estrutura base para todos os tipos de ingressos.
  * Define os atributos e comportamentos comuns que todos os ingressos devem ter.
  * 
  * @author Lucas Beserra - 281815
  * @version 1.0
  * @see Evento
  */
 public abstract class Ingresso {
 
     private Evento evento;
     private double preco;
     private boolean is_cancelable;
 
     /**
      * Construtor da classe Ingresso.
      * 
      * @param evento O evento associado ao ingresso
      * @param preco O preço base do ingresso
      * @param is_cancelable Indica se o ingresso pode ser cancelado
      */
     public Ingresso(Evento evento, double preco, boolean is_cancelable) {
         this.evento = evento;
         this.preco = preco;
         this.is_cancelable = is_cancelable;
     }
 
     /**
      * Retorna o preço base do ingresso.
      * 
      * @return O preço base do ingresso
      */
     public double getPrecoBase() {
         return this.preco;
     }
 
     /**
      * Método abstrato que deve ser implementado pelas subclasses para retornar
      * o preço final do ingresso (pode incluir descontos ou acréscimos).
      * 
      * @return O preço final do ingresso
      */
     public abstract double getPreco();
 
     /**
      * Define o evento associado ao ingresso.
      * 
      * @param evento O novo evento a ser associado ao ingresso
      */
     public void setEvento(Evento evento) {
         this.evento = evento;
     }
     
     /**
      * Retorna o evento associado ao ingresso.
      * 
      * @return O evento associado ao ingresso
      */
     public Evento getEvento() {
         return this.evento;
     }
 
     /**
      * Verifica se o ingresso pode ser cancelado.
      * 
      * @return true se o ingresso pode ser cancelado, false caso contrário
      */
     public boolean isCancelable() {
         return this.is_cancelable;
     }
     
     /**
      * Define se o ingresso pode ser cancelado.
      * 
      * @param is_cancelable true para permitir cancelamento, false para proibir
      */
     public void setCancelable(boolean is_cancelable) {
         this.is_cancelable = is_cancelable;
     }
 
     /**
      * Retorna uma representação em String do ingresso.
      * 
      * @return String contendo informações do evento e preço do ingresso
      */
     @Override
     public String toString() {
         return "Ingresso para: " + this.evento.getNome() + "\n" +
                "Preço: " + this.preco;
     }
 }
