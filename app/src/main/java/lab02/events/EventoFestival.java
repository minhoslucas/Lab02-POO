/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.time.LocalDate;

import lab02.events.characteristics.CaracteristicaDeEventoFestival;

public class EventoFestival extends Evento {

    private CaracteristicaDeEventoFestival caracteristicas;
    
    /**
    * Construtor da classe EventoFestival
    * @param nome o nome do Evento
    * @param local o local do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param dataInicio a data de início do Festival
    * @param dataFim a data de fim do Festival
    */
    public EventoFestival(CaracteristicaDeEventoFestival caracteristicas, double precoIngresso, String nome, Local local, int capacidade, Organizadora organizadora, LocalDate data) {
        super(precoIngresso, nome, local, capacidade, data, organizadora);
        this.caracteristicas = caracteristicas;
    }
    
    /**
     * Retorna uma string contendo a descri o do Festival, com seu nome, lineup, local e dura o
     * @return uma string com a descri o do Festival
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
