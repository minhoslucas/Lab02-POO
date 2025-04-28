package lab02.events.characteristics;

import java.util.ArrayList;

/**
 * Classe que representa características específicas de um evento de música ao vivo
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA
 */
public class CaracteristicaDeEventoMusicaAoVivo extends CaracteristicaDeEvento{

    /** Lista de músicas do setlist */
    private ArrayList<String> setlist;
    
    /** Duração do evento em minutos */ 
    private int duracao;

    /**
     * Construtor da classe
     * @param setlist Lista de músicas
     * @param duracao Duração do evento
     */
    public CaracteristicaDeEventoMusicaAoVivo(ArrayList<String> setlist, int duracao){
        this.setlist = setlist;
        this.duracao = duracao;
    }
    
    /**
     * @return Lista de músicas do setlist
     */
    public ArrayList<String> getSetlist(){
        return this.setlist;
    }
    
    /**
     * Define o setlist do evento
     * @param setlist Lista de músicas
     */
    public void setSetlist(ArrayList<String> setlist){
        try {
            if (setlist == null || setlist.isEmpty()) {
            throw new IllegalArgumentException("Setlist não pode ser null ou vazia");
        }
        this.setlist = setlist;
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adiciona uma música ao setlist
     * @param song Música a ser adicionada
     */
    public void addToSetlist(String song){
        this.setlist.add(song);
    }

    /**
     * @return Duração do evento
     */
    public int getDuracao(){
        return this.duracao;
    }

    /**
     * Formata o setlist para exibição
     * @return Setlist formatado
     */
    private String showSetlist(){
        StringBuilder text = new StringBuilder();

        for (String word : this.setlist){
            text.append(word).append("\n");
        }
        
        return text.toString();
    }

    /**
     * @return Descrição completa do evento
     */
    public String descricao(){
        return "Setlist: " + "\n" + this.showSetlist() +
        "Duração: " + this.duracao;
    }
}