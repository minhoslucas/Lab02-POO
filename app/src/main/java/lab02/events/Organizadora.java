package lab02.events;

import java.time.LocalDate;
import java.util.ArrayList;
import lab02.events.characteristics.CaracteristicaDeEventoFestival;
import lab02.events.characteristics.CaracteristicaDeEventoJogo;
import lab02.events.characteristics.CaracteristicaDeEventoMusicaAoVivo;
import lab02.events.characteristics.CaracteristicaDeEventoShow;
import lab02.exceptions.EventoNaoEncontradoException;
import lab02.filter.Filter;

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

    public EventoFestival criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, 
                            ArrayList<String> lineup, int duracao) {
        CaracteristicaDeEventoFestival caracteristicas = new CaracteristicaDeEventoFestival(lineup, duracao);
        EventoFestival novo_evento = new EventoFestival(caracteristicas, precoIngresso, nome, local, capacidade, organizadora, data);
        return novo_evento;
    }

    public EventoJogo criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, ArrayList<String> times, String tipo) {
        CaracteristicaDeEventoJogo caracteristicas = new CaracteristicaDeEventoJogo(times, tipo);
        EventoJogo novo_evento = new EventoJogo(nome, local, caracteristicas, precoIngresso, capacidade, organizadora, data);
        return novo_evento;
    }

    public EventoMusicaAoVivo criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, int duration, ArrayList<String> setlist) {
        CaracteristicaDeEventoMusicaAoVivo caracteristicas = new CaracteristicaDeEventoMusicaAoVivo(setlist, duration);
        EventoMusicaAoVivo novo_evento = new EventoMusicaAoVivo(nome, local, precoIngresso, caracteristicas, capacidade, organizadora, data);
        return novo_evento;
    }

    public EventoShow criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, String artista) {
        CaracteristicaDeEventoShow caracteristicas = new CaracteristicaDeEventoShow(artista);
        EventoShow novo_evento = new EventoShow(nome, local, precoIngresso, caracteristicas, capacidade, organizadora, data);
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

    public void showEventos(){
        int i = 1;
        for (Evento evento : this.eventos){
            System.out.println(i + ">" + evento.getNome() + "- R$" + evento.getPrecoIngresso());
            i += 1;
        }
    }

    public ArrayList<Evento> buscarEventos(Filter<?> filtro){
        ArrayList<Evento> result;
        try{
            result = filtro.filter(this.eventos);
        }catch (EventoNaoEncontradoException e){
            result = new ArrayList<Evento>();
            System.out.println(e.getMessage());
        }
        return result;
    }
}
