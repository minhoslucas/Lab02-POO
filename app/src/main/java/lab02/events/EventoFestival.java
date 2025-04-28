/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.time.LocalDate;
 
 import lab02.events.characteristics.CaracteristicaDeEventoFestival;
 
 /**
  * Classe que representa um evento do tipo Festival.
  * Contém características específicas de festivais.
  * 
  * @author Lucas Beserra - 281815
  */
 public class EventoFestival extends Evento {
 
     /** Características específicas do festival */
     private CaracteristicaDeEventoFestival caracteristicas;
     
     /**
      * Construtor da classe EventoFestival
      * @param caracteristicas Características do festival
      * @param precoIngresso Preço do ingresso
      * @param nome Nome do festival
      * @param local Local do festival
      * @param capacidade Capacidade máxima
      * @param organizadora Organizadora responsável
      * @param data Data do festival
      */
     public EventoFestival(CaracteristicaDeEventoFestival caracteristicas, double precoIngresso, String nome, Local local, int capacidade, Organizadora organizadora, LocalDate data) {
         super(precoIngresso, nome, local, capacidade, data, organizadora);
         this.caracteristicas = caracteristicas;
     }
 
     /**
      * Obtém as características do festival
      * @return Características do festival
      */
     public CaracteristicaDeEventoFestival getCaracteristicas() {
         return this.caracteristicas;
     }
 
     /**
      * Define as características do festival
      * @param caracteristicas Novas características
      */
     public void setCaracteristicas(CaracteristicaDeEventoFestival caracteristicas) {
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
      * Exibe a descrição completa do festival
      */
     @Override
     public void descricao(){
         String text = "Nome do Festival: " + this.getNome() + "\n" +
         "Local do Festival: " + this.getLocal().getNome() + "\n" +
         "Data: " + this.getData() + "\n" +
         this.caracteristicas.descricao();
         System.out.println(text);
     }
 }
