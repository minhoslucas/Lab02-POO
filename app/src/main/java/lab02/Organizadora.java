package lab02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab02.filter.EventoPorDataFilter;
import lab02.filter.Filtro;

public class Organizadora {

    private String nome;
    private int cnpj;
    private String endereco;
    private ArrayList<Evento> eventos;

    /**
     * Construtor da classe Organizadora
     * @param nome o nome da organizadora
     * @param cnpj o CNPJ da organizadora
     * @param endereco o endereço da organizadora
     */
    public Organizadora(String nome, int cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.eventos = new ArrayList<Evento>();
    }

    public Evento criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, 
                            List<String> lineup, int duracao) {
        EventoFestival novo_evento = new EventoFestival(nome, local, capacidade, precoIngresso, organizadora, data, lineup, duracao);
        return novo_evento;
    }

    public Evento criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, List<String> times) {
        EventoJogo novo_evento = new EventoJogo(nome, local, capacidade, precoIngresso, organizadora, data, times);
        return novo_evento;
    }

    public Evento criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, int duration) {
        EventoMusicaAoVivo novo_evento = new EventoMusicaAoVivo(nome, local, capacidade, precoIngresso, organizadora, data, duration);
        return novo_evento;
    }

    public Evento criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, String artista) {
        EventoShow novo_evento = new EventoShow(nome, local, capacidade, precoIngresso, organizadora, data, artista);
        return novo_evento;
    }

    public String getNome() {
        return nome;
    }

    public int getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void adicionaEvento(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public void adicionaEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public ArrayList<Evento> buscarEventos(Filtro filtro){
        
    }
    
}
