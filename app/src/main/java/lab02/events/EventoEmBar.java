/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.time.LocalDate;
 import lab02.events.characteristics.CaracteristicaDeEventoEmBar;
 
 /**
  * Classe que representa um evento do tipo Em Bar.
  * Contém características específicas de eventos em bar.
  * 
  * @author Lucas Beserra - 281815  
  * Comentários feitos por IA e revisados posteriormente
  */
 public class EventoEmBar extends Evento {
     
     /** Características específicas do evento */
     private CaracteristicaDeEventoEmBar caracteristicas;    
     
     /**
      * Construtor da classe EventoEmBar.
      * 
      * @param nome Nome do evento
      * @param local Local da apresentação
      * @param precoIngresso Preço do ingresso
      * @param caracteristicas Características do evento
      * @param capacidade Capacidade máxima do local
      * @param organizadora Organizadora responsável pelo evento
      * @param data Data do evento
      */
     public EventoEmBar(String nome, Local local, double precoIngresso, 
                       CaracteristicaDeEventoEmBar caracteristicas, int capacidade, 
                       Organizadora organizadora, LocalDate data) {
         super(precoIngresso, nome, local, capacidade, data, organizadora);
         this.caracteristicas = caracteristicas;
     }
 
     /**
      * Obtém as características do evento.
      * 
      * @return as características do evento
      */
     public CaracteristicaDeEventoEmBar getCaracteristicas() {
         return this.caracteristicas;
     }
 
     /**
      * Define as características do evento.
      * 
      * @param caracteristicas novas características do evento
      * @throws IllegalArgumentException se as características forem nulas
      */
     public void setCaracteristicas(CaracteristicaDeEventoEmBar caracteristicas) {
         try {
             if (caracteristicas == null) {
                 throw new IllegalArgumentException("As características do evento não podem ser nulas.");
             }
             this.caracteristicas = caracteristicas;
         } catch (IllegalArgumentException e) {
             System.err.println(e.getMessage());
         }
     }
 
     /**
      * Exibe a descrição completa do evento no console.
      */
     @Override
     public void descricao() {
         String text = "Nome do evento: " + this.getNome() + "\n" +
                       "Local do evento: " + this.getLocal().getNome() + "\n" +
                       "Data: " + this.getData() + "\n" +
                       this.caracteristicas.descricao();
         System.out.println(text);
     }
 }