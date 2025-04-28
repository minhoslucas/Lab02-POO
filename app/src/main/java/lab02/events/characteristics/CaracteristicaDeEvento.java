package lab02.events.characteristics;

/**
 * @author Lucas Beserra - 281815
 * 
 * Classe abstrata base para características de eventos.
 * Subclasses devem implementar o método descricao().
 * 
 * Comentários feitos por IA
 */
public abstract class CaracteristicaDeEvento {

    public CaracteristicaDeEvento() {
    }

    /**
     * @return Descrição textual das características do evento
     */
    public abstract String descricao();
}
