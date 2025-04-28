package lab02.events.characteristics;

import java.util.ArrayList;

public class CaracteristicaDeEventoFestival extends CaracteristicaDeEvento{
    
    private ArrayList<String> lineup;
    private int duracao;

    public CaracteristicaDeEventoFestival(ArrayList<String> lineup, int duracao){
        this.lineup = lineup;
        this.duracao = duracao;
    }
    
    public ArrayList<String> getLineup(){
        return this.lineup;
    }

    public int getDuracao(){
        return this.duracao;
    }

    public void setLineup(ArrayList<String> lineup){
        this.lineup = lineup;
    }

    private String showLineup(){
        String text = "";

        for (String word : this.lineup){
            text += word;
            text += "\n";
        }

        return text;
    }

    @Override
    public String descricao(){
        return "Lineup: " + "\n" + this.showLineup() +
        "Duração: " + this.duracao;
    }
}