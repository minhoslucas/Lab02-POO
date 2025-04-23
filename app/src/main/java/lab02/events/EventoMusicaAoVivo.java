package lab02.events;

import java.time.LocalDate;
import java.util.ArrayList;

import lab02.Local;
import lab02.Organizadora;

public class EventoMusicaAoVivo extends Evento {

    private ArrayList<String> setlist;
    private int duration;

    public EventoMusicaAoVivo(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, int duration) {
        super(nome, local, capacidade, precoIngresso, organizadora, data);
        this.duration = duration;
        this.setlist = new ArrayList<String>();
    }

    public void setSetlist(ArrayList<String> setlist) {
        this.setlist = setlist;
    }

    public ArrayList<String> getSetlist() {
        return this.setlist;
    }

    public void addToSetlist(String musica) {
        this.setlist.add(musica);
    } 

    @Override
    public String descricao() {
        return "Local: " + this.getLocal().getNome() + "\n"
        + "setlist: " + this.setlist + "\n"
        + "Duração: " + this.duration;
    }
}