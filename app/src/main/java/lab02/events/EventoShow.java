/*

 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.time.LocalDate;

import lab02.events.characteristics.CaracteristicaDeEventoShow;

public class EventoShow extends Evento {
        
    private CaracteristicaDeEventoShow caracteristicas;    
    /**
    * Construtor da classe EventocShow
    * @param nome o nome do Evento
    * @param local o local do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param artista o artista do Evento
    * @param organizadora a organizadora do Evento
    */
    public EventoShow(String nome, Local local, double precoIngresso, CaracteristicaDeEventoShow caracteristicas, int capacidade, Organizadora organizadora, LocalDate data) {
        super(precoIngresso, nome, local, capacidade, data, organizadora);
        this.caracteristicas = caracteristicas;
    }

    /**
    * Retorna a descrição do Evento
    * @return a descrição do Evento
    */
    @Override
    public void descricao(){
        String text = "Nome do Show: " + this.getNome() + "\n" +
        "Nome do Artista: " + this.caracteristicas.getArtista() + "\n" +
        "Local da Apresentação: " + this.getLocal().getNome() + "\n" +
        "Data: " + this.getData() + "\n" +
        this.caracteristicas.descricao();
        System.out.println(text);
    }
}
