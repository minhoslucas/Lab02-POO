package lab02.events.characteristics;

/**
 * Classe abstrata base para características de eventos.
 * Subclasses devem implementar o método descricao().
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA
 */
public abstract class CaracteristicaDeEvento {

    /**
     * Construtor da classe abstrata CaracteristicaDeEvento.
     */
    public CaracteristicaDeEvento() {
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses para retornar
     * uma descrição textual das características do evento.
     * 
     * @return Descrição textual das características do evento
     */
    public abstract String descricao();
}
