/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.time.LocalDate;
import java.util.List;

import lab02.Local;
import lab02.Organizadora;

public class EventoFestival extends Evento {
        
    private List<String> lineup;
    private int duracao;
    
    /**
    * Construtor da classe EventoFestival
    * @param nome o nome do Evento
    * @param local o local do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param dataInicio a data de início do Festival
    * @param dataFim a data de fim do Festival
    */
    public EventoFestival(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, List<String> lineup, int duracao) {
        super(nome, local, capacidade, precoIngresso, organizadora, data);
        this.lineup = lineup;
        this.duracao = duracao;
    }

    /**
    * Retorna a lista com os nomes dos artistas que se apresentarão no Festival
    * @return a lista com os nomes dos artistas do Festival
    */
    public List<String> getLineup() {
        return this.lineup;
    }
    
    /**
    * Retorna a dura o do Festival em dias
    * @return a dura o do Festival
    */
    public int getDuracao() {
        return this.duracao;
    }


    /**
     * Retorna uma string contendo a descri o do Festival, com seu nome, lineup, local e dura o
     * @return uma string com a descri o do Festival
     */
    @Override
    public String descricao() {
        return "Festival: " + this.getNome() + " - Lineup: " + this.lineup + " - Local: " + this.getLocal().getNome() + " - Duração: " + this.duracao;
    }
}
