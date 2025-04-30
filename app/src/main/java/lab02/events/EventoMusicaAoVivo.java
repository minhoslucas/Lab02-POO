package lab02.events;

import java.time.LocalDate;
import lab02.events.characteristics.CaracteristicaDeEventoMusicaAoVivo;

/**
 * Classe que representa um evento de música ao vivo.
 * Contém características específicas de shows musicais.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentarios feitos por IA
 */
public class EventoMusicaAoVivo extends Evento {

    /** Características específicas do show musical */
    private CaracteristicaDeEventoMusicaAoVivo caracteristicas;

    /**
     * Construtor da classe EventoMusicaAoVivo
     * @param nome Nome do artista/show
     * @param local Local da apresentação
     * @param precoIngresso Preço do ingresso
     * @param caracteristicas Características do show
     * @param capacidade Capacidade máxima
     * @param organizadora Organizadora responsável
     * @param data Data do show
     */
    public EventoMusicaAoVivo(String nome, Local local, double precoIngresso, 
                            CaracteristicaDeEventoMusicaAoVivo caracteristicas, 
                            int capacidade, Organizadora organizadora, LocalDate data) {
        super(precoIngresso, nome, local, capacidade, data, organizadora);
        this.caracteristicas = caracteristicas;
    }

    /**
     * Obtém as características do show musical
     * @return Características do show
     */
    public CaracteristicaDeEventoMusicaAoVivo getCaracteristicas() {
        return this.caracteristicas;
    }

    /**
     * Define as características do show musical
     * @param caracteristicas Novas características do show
     * @throws IllegalArgumentException Se as características forem nulas
     */
    public void setCaracteristicas(CaracteristicaDeEventoMusicaAoVivo caracteristicas) {
        try {
            if (caracteristicas == null) {
                throw new IllegalArgumentException("As características do evento não podem ser nulas.");
            }
            this.caracteristicas = caracteristicas;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exibe a descrição completa do show musical
     */
    @Override
    public void descricao() {
        String text = "Nome do Artista: " + this.getNome() + "\n" +
        "Local da Apresentação: " + this.getLocal().getNome() + "\n" +
        "Data: " + this.getData() + "\n" +
        caracteristicas.descricao();
        System.out.println(text);
    }
}