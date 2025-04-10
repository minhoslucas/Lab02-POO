package lab02;

import java.util.ArrayList;

public class EventoMusicaAoVivo extends Evento {

    private ArrayList<String> setlist;
    private int duration;

    public EventoMusicaAoVivo(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int duration) {
        super(nome, local, precoIngresso, organizadora, data);
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
        return "Local: " + this.local + "\n"
        + "setlist: " + this.setlist + "\n"
        + "Duração: " + this.duration;
    }
}