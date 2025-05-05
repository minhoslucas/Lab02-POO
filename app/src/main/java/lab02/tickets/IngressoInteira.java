package lab02.tickets;

import lab02.events.Evento;

/**
 * Classe que representa um ingresso do tipo inteira para eventos.
 * Este tipo de ingresso mantém o preço base sem alterações.
 * 
 * @author Lucas Beserra - 281815
 * @see Ingresso
 * @see Evento
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class IngressoInteira extends Ingresso {

    /**
     * Construtor da classe IngressoInteira.
     * 
     * @param evento O evento associado ao ingresso
     * @param preco O preço base do ingresso (será mantido integralmente)
     * @param is_cancelable Indica se o ingresso pode ser cancelado
     */
    public IngressoInteira(Evento evento, double preco, boolean is_cancelable) {
        super(evento, preco, is_cancelable);
    }

    /**
     * Retorna o preço do ingresso inteira (igual ao preço base).
     * 
     * @return O preço do ingresso inteira
     */
    @Override
    public double getPreco() {
        return this.getPrecoBase();
    }
}