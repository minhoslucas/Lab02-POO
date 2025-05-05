package lab02.tickets;

import lab02.events.Evento;

/**
 * Classe que representa um ingresso do tipo meia-entrada para eventos.
 * Este tipo de ingresso possui metade do preço de um ingresso comum.
 * 
 * @author Lucas Beserra - 281815
 * @see Ingresso
 * @see Evento
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class IngressoMeia extends Ingresso {

    /**
     * Construtor da classe IngressoMeia.
     * 
     * @param evento O evento associado ao ingresso
     * @param preco O preço base do ingresso (será dividido por 2 para meia-entrada)
     * @param is_cancelable Indica se o ingresso pode ser cancelado
     */
    public IngressoMeia(Evento evento, double preco, boolean is_cancelable) {
        super(evento, preco, is_cancelable);
    }

    /**
     * Retorna o preço do ingresso meia-entrada (metade do preço base).
     * 
     * @return O preço do ingresso meia-entrada
     */
    @Override
    public double getPreco() {
        return this.getPrecoBase() / 2;
    }
}