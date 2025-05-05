package lab02.events.characteristics;

import java.util.ArrayList;

/**
 * Representa as características específicas de um evento do tipo festival.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA
 */
public class CaracteristicaDeEventoFestival extends CaracteristicaDeEvento {
    
    /** Lista de artistas ou atrações do festival */
    private ArrayList<String> lineup;
    
    /** Duração do festival em horas */
    private int duracao;

    /**
     * Constrói uma característica de festival com lineup e duração específicos.
     * 
     * @param lineup Lista de artistas/atrações do festival
     * @param duracao Duração do festival em horas
     */
    public CaracteristicaDeEventoFestival(ArrayList<String> lineup, int duracao) {
        this.lineup = lineup;
        this.duracao = duracao;
    }
    
    /**
     * @return A lista de artistas/atrações do festival
     */
    public ArrayList<String> getLineup() {
        return this.lineup;
    }

    /**
     * @return A duração do festival em horas
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Define uma nova lista de artistas/atrações para o festival.
     * 
     * @param lineup Nova lista de artistas/atrações
     * @throws IllegalArgumentException Se a lista for nula ou vazia
     */
    public void setLineup(ArrayList<String> lineup) {
        try {
            if (lineup == null || lineup.isEmpty()) {
                throw new IllegalArgumentException("lineup não pode ser null ou vazia");
            }
            this.lineup = lineup;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Formata a lista de artistas para exibição.
     * 
     * @return String formatada com os artistas do lineup
     */
    private String showLineup() {
        StringBuilder text = new StringBuilder();

        for (String word : this.lineup) {
            text.append(word).append("\n");
        }
        return text.toString();
    }

    /**
     * Retorna a descrição completa das características do festival.
     * 
     * @return Descrição contendo o lineup e duração do festival
     */
    @Override
    public String descricao() {
        return "Lineup: " + "\n" + this.showLineup() +
               "Duração: " + this.duracao;
    }
}
