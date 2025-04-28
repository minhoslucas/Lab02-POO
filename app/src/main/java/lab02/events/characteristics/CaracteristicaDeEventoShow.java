package lab02.events.characteristics;

/**
 * Representa as características específicas de um evento do tipo show.
 * Contém informações sobre o artista principal do show.
 *
 * @author Lucas Beserra - 281815
 * 
 * Comentarios feitos por IA
 */
public class CaracteristicaDeEventoShow extends CaracteristicaDeEvento {

    /** Nome do artista principal do show */
    private String artista;

    /**
     * Constrói uma característica de show com o artista especificado.
     * @param artista Nome do artista principal
     */
    public CaracteristicaDeEventoShow(String artista) {
        this.artista = artista;
    }
    
    /**
     * Obtém o nome do artista do show.
     * @return Nome do artista
     */
    public String getArtista() {
        return this.artista;
    }

    /**
     * Retorna a descrição completa das características do show.
     * @return Descrição contendo o nome do artista
     */
    public String descricao() {
        return "Artista: " + this.artista;
    }
}
