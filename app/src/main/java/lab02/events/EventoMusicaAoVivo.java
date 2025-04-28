package lab02.events;

import java.time.LocalDate;

import lab02.events.characteristics.CaracteristicaDeEventoMusicaAoVivo;

public class EventoMusicaAoVivo extends Evento {

    private CaracteristicaDeEventoMusicaAoVivo caracteristicas;

    public EventoMusicaAoVivo(String nome, Local local, double precoIngresso, CaracteristicaDeEventoMusicaAoVivo caracteristicas, int capacidade, Organizadora organizadora, LocalDate data) {
        super(precoIngresso, nome, local, capacidade, data, organizadora);
        this.caracteristicas = caracteristicas;
    }

    @Override
    public void descricao(){
        String text = "Nome do Artista: " + this.getNome() + "\n" +
        "Local da Apresentação: " + this.getLocal().getNome() + "\n" +
        "Data: " + this.getData() + "\n" +
        caracteristicas.descricao();
        System.out.println(text);
    }
}