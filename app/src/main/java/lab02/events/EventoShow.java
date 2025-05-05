/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.time.LocalDate;
 import lab02.events.characteristics.CaracteristicaDeEventoShow;
 
 /**
  * Classe que representa um evento do tipo Show.
  * Contém características específicas de shows artísticos.
  * 
  * @author Lucas Beserra - 281815  
  * Comentários feitos por IA e revisados posteriormente
  */
 public class EventoShow extends Evento {
     
     /** Características específicas do show */
     private CaracteristicaDeEventoShow caracteristicas;    
     
     /**
      * Construtor da classe EventoShow.
      * 
      * @param nome Nome do show
      * @param local Local da apresentação
      * @param precoIngresso Preço do ingresso
      * @param caracteristicas Características do show
      * @param capacidade Capacidade máxima do local
      * @param organizadora Organizadora responsável pelo evento
      * @param data Data do show
      */
     public EventoShow(String nome, Local local, double precoIngresso, 
                       CaracteristicaDeEventoShow caracteristicas, int capacidade, 
                       Organizadora organizadora, LocalDate data) {
         super(precoIngresso, nome, local, capacidade, data, organizadora);
         this.caracteristicas = caracteristicas;
     }
 
     /**
      * Obtém as características do show.
      * 
      * @return as características do show
      */
     public CaracteristicaDeEventoShow getCaracteristicas() {
         return this.caracteristicas;
     }
 
     /**
      * Define as características do show.
      * 
      * @param caracteristicas novas características do show
      * @throws IllegalArgumentException se as características forem nulas
      */
     public void setCaracteristicas(CaracteristicaDeEventoShow caracteristicas) {
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
      * Exibe a descrição completa do show no console.
      */
     @Override
     public void descricao() {
         String text = "Nome do Show: " + this.getNome() + "\n" +
                       "Nome do Artista: " + this.caracteristicas.getArtista() + "\n" +
                       "Local da Apresentação: " + this.getLocal().getNome() + "\n" +
                       "Data: " + this.getData() + "\n" +
                       this.caracteristicas.descricao();
         System.out.println(text);
     }
 }
 