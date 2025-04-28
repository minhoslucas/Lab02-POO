package lab02.events.characteristics;

import java.util.ArrayList;

public class CaracteristicaDeEventoJogo extends CaracteristicaDeEvento{

    private ArrayList<String> times;
    private String type;

    public CaracteristicaDeEventoJogo(ArrayList<String> times, String type){
        this.times = times;
        this.type = type;
    }
    
    public ArrayList<String> getTimes(){
        return this.times;
    }

    public String getType(){
        return this.type;
    }

    private String showTimes(){
        String text = "";

        for (String word : this.times){
            text += word;
            text += "\n";
        }
        return text;
    }

    public String descricao(){
        return "Times: " + "\n" + this.showTimes() +
        "Tipo de Jogo: " + this.type;
    }
}
