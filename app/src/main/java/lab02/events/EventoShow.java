/*

 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.time.LocalDate;

import lab02.Local;
import lab02.Organizadora;

public class EventoShow extends Evento {
        
    private String artista;
    
    /**
    * Construtor da classe EventocShow
    * @param nome o nome do Evento
    * @param local o local do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param artista o artista do Evento
    * @param organizadora a organizadora do Evento
    */
    public EventoShow(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, String artista) {
        super(nome, local, capacidade, precoIngresso, organizadora, data);
        this.artista = artista;

    }

    /**
    * Retorna a descrição do Evento
    * @return a descrição do Evento
    */
    public String getDescricao() {
        return "Show: " + this.getNome() + " - Artista: " + this.artista  + " - Local: " + this.getLocal().getNome() + " - Data: " + this.getData();
    }
}
