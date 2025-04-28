package lab02.events.characteristics;

import java.util.ArrayList;

public class CaracteristicaDeEventoMusicaAoVivo extends CaracteristicaDeEvento{

    private ArrayList<String> setlist;
    private int duracao;

    public CaracteristicaDeEventoMusicaAoVivo(ArrayList<String> setlist, int duracao){
        this.setlist = setlist;
        this.duracao = duracao;
    }
    
    public ArrayList<String> getSetlist(){
        return this.setlist;
    }

    public void setSetlist(ArrayList<String> setlist){
        this.setlist = setlist;
    }

    public void addToSetlist(String song){
        this.setlist.add(song);
    }

    public int getDuracao(){
        return this.duracao;
    }

    private String showSetlist(){
        String text = "";

        for (String word : this.setlist){
            text += word;
            text += "\n";
        }

        return text;
    }

    public String descricao(){
        return "Setlist: " + "\n" + this.showSetlist() +
        "Duração: " + this.duracao;
    }
}