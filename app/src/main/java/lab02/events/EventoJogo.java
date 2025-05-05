/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.time.LocalDate;
 import lab02.events.characteristics.CaracteristicaDeEventoJogo;
 
 /**
  * Classe que representa um evento do tipo Jogo Esportivo.
  * Contém características específicas de jogos/eventos esportivos.
  * 
  * @author Lucas Beserra - 281815  
  * Comentários feitos por IA e revisados posteriormente
  */
 public class EventoJogo extends Evento {
     
     /** Características específicas do jogo esportivo */
     private CaracteristicaDeEventoJogo caracteristicas;
     
     /**
      * Construtor da classe EventoJogo.
      * 
      * @param nome Nome do jogo
      * @param local Local onde será realizado
      * @param caracteristicas Características específicas do jogo
      * @param precoIngresso Preço do ingresso
      * @param capacidade Capacidade máxima do local
      * @param organizadora Organizadora responsável
      * @param data Data do jogo
      */
     public EventoJogo(String nome, Local local, CaracteristicaDeEventoJogo caracteristicas, 
                       double precoIngresso, int capacidade, Organizadora organizadora, LocalDate data) {
         super(precoIngresso, nome, local, capacidade, data, organizadora);
         this.caracteristicas = caracteristicas;
     }
 
     /**
      * Obtém as características do jogo.
      * 
      * @return características do jogo esportivo
      */
     public CaracteristicaDeEventoJogo getCaracteristicas() {
         return this.caracteristicas;
     }
 
     /**
      * Define as características do jogo.
      * 
      * @param caracteristicas novas características do jogo
      * @throws IllegalArgumentException se as características forem nulas
      */
     public void setCaracteristicas(CaracteristicaDeEventoJogo caracteristicas) {
         try {
             if (caracteristicas == null) {
                 throw new IllegalArgumentException("As características do evento não podem ser nulas.");
             }
             this.caracteristicas = caracteristicas;
         } catch (IllegalArgumentException e) {
             System.out.println(e.getMessage());
         }
     }
 
     /**
      * Exibe a descrição completa do jogo esportivo no console.
      */
     @Override
     public void descricao() {
         String text = "Nome do Jogo: " + this.getNome() + "\n" +
                       "Local do Jogo: " + this.getLocal().getNome() + "\n" +
                       this.caracteristicas.descricao();
         System.out.println(text);
     }
 }
 