package lab02.filter;

import java.util.ArrayList;
import lab02.Evento;

class AndFilter{
    private ArrayList<Evento> result;
    private ArrayList<Evento> event_list;

    public AndFilter(ArrayList<Evento> event_list){
        this.event_list = event_list;
        this.result = new ArrayList<Evento>();
    }

    

}