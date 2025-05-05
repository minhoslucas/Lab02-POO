package lab02.tickets;

import lab02.events.Evento;

/**
 * Classe que representa um ingresso do tipo VIP para eventos.
 * Este tipo de ingresso possui um preço dobrado em relação ao ingresso comum.
 * 
 * @author Lucas Beserra - 281815
 * @see Ingresso
 * @see Evento
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class IngressoVip extends Ingresso {

    /**
     * Construtor da classe IngressoVip.
     * 
     * @param evento O evento associado ao ingresso
     * @param preco O preço base do ingresso (será dobrado para VIP)
     * @param is_cancelable Indica se o ingresso pode ser cancelado
     */
    public IngressoVip(Evento evento, double preco, boolean is_cancelable) {
        super(evento, preco, is_cancelable);
    }

    /**
     * Retorna o preço do ingresso VIP (dobro do preço base).
     * 
     * @return O preço do ingresso VIP
     */
    @Override
    public double getPreco() {
        return this.getPrecoBase() * 2;
    }
}