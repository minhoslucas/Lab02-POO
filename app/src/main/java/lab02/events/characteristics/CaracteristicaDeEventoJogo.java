package lab02.events.characteristics;

import java.util.ArrayList;

/**
 * Representa as características específicas de um evento esportivo/jogo.
 * Contém informações sobre os times participantes e o tipo de jogo.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA
 */
public class CaracteristicaDeEventoJogo extends CaracteristicaDeEvento {

    /** Lista de times participantes do jogo */
    private ArrayList<String> times;
    
    /** Tipo/categoria do jogo */
    private String type;

    /**
     * Constrói uma característica de jogo com times e tipo específicos.
     *
     * @param times Lista de times que participarão do jogo
     * @param type  Categoria/classificação do jogo
     */
    public CaracteristicaDeEventoJogo(ArrayList<String> times, String type) {
        this.times = times;
        this.type = type;
    }

    /**
     * Obtém a lista de times do jogo.
     *
     * @return ArrayList contendo os nomes dos times participantes
     */
    public ArrayList<String> getTimes() {
        return this.times;
    }

    /**
     * Obtém o tipo do jogo.
     *
     * @return String com a classificação do jogo
     */
    public String getType() {
        return this.type;
    }

    /**
     * Formata a lista de times para exibição.
     *
     * @return String formatada com os nomes dos times
     */
    private String showTimes() {
        StringBuilder text = new StringBuilder();

        for (String word : this.times) {
            text.append(word).append("\n");
        }
        return text.toString();
    }

    /**
     * Retorna a descrição completa das características do jogo.
     *
     * @return String contendo os times e tipo de jogo formatados
     */
    @Override
    public String descricao() {
        return "Times: " + "\n" + this.showTimes() +
               "Tipo de Jogo: " + this.type;
    }
}