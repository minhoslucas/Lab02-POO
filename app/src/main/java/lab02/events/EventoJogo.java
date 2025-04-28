/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */


package lab02.events;


import java.time.LocalDate;

import lab02.events.characteristics.CaracteristicaDeEventoJogo;

public class EventoJogo extends Evento {
        
    private CaracteristicaDeEventoJogo caracteristicas;
    /**
    * Construtor da classe EventoEsporte
    * @param nome o nome do Evento
    * @param local o local do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    */
    public EventoJogo(String nome, Local local, CaracteristicaDeEventoJogo caracteristicas, double precoIngresso, int capacidade, Organizadora organizadora, LocalDate data) {
        super(precoIngresso, nome, local, capacidade, data, organizadora);
        this.caracteristicas = caracteristicas;
    }

    /**
     * Retorna uma string contendo a descri o do Evento, com seu nome, times, local e data
     * @return uma string com a descri o do Evento
     */
    @Override
    public void descricao(){
        String text = "Nome do Jogo: " + this.getNome() + "\n" +
        "Local do Jogo: " + this.getLocal().getNome() + "\n" +
        this.caracteristicas.descricao();
        System.out.println(text);
    }
}
