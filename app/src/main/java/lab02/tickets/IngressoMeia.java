package lab02.tickets;

import lab02.events.Evento;

public class IngressoMeia extends Ingresso{

    public IngressoMeia(Evento evento, double preco, boolean is_cancelable){
        super(evento, preco, is_cancelable);
    }

    @Override
    public double getPreco(){
        return this.getPrecoBase()/2;
    }
}