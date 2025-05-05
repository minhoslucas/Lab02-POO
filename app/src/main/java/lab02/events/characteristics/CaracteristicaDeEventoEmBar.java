package lab02.events.characteristics;

/**
 * Representa as características específicas de um evento do tipo Em Bar.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA
 */
public class CaracteristicaDeEventoEmBar extends CaracteristicaDeEvento {
    
    /** Duração e Inicio do happyour */
    private int duracaoHappy; //Duração do happy our
    private int inicioHappy; //Inicio do happy out 

    /**
     * Constrói uma característica de festival com lineup e duração específicos.
     * 
     * @param duracaoHappy Duração do HappyOur
     * @param inicioHappy Duração do festival em horas
     */
    public CaracteristicaDeEventoEmBar(int duracaoHappy, int inicioHappy) {
        this.duracaoHappy = duracaoHappy;
        this.inicioHappy = inicioHappy;
    }
    
    /**
     * @return A Duração do HappyOur
     */
    public int getDuracaoHappy() {
        return this.duracaoHappy;
    }

    /**
     * @return O inicio do HappyOur
     */
    public int getInicioHappy() {
        return this.inicioHappy;
    }

    /**
     * Retorna a descrição completa das características do festival.
     * 
     * @return Descrição contendo o lineup e duração do festival
     */
    @Override
    public String descricao() {
        return "Inicio do HappyOur: " + this.inicioHappy + "\n" +
        "Duração do HappyOur: " + this.duracaoHappy + "\n";
    }
}
