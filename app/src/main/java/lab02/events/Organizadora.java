package lab02.events;

import java.time.LocalDate;
import java.util.ArrayList;
import lab02.events.characteristics.CaracteristicaDeEventoFestival;
import lab02.events.characteristics.CaracteristicaDeEventoJogo;
import lab02.events.characteristics.CaracteristicaDeEventoMusicaAoVivo;
import lab02.events.characteristics.CaracteristicaDeEventoShow;
import lab02.exceptions.EventoNaoEncontradoException;
import lab02.filter.Filter;

/**
 * Representa uma organizadora de eventos, responsável pela criação e gerenciamento dos mesmos.
 * 
 * @author Lucas Beserra - 281815  
 * Comentários feitos por IA e revisados posteriormente
 */
public class Organizadora {

    private String nome;
    private int cnpj;
    private String endereco;
    private ArrayList<Evento> eventos;

    /**
     * Construtor da classe Organizadora.
     * 
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

    /**
     * Cria um evento do tipo festival.
     * 
     * @param nome o nome do evento
     * @param local o local do evento
     * @param capacidade a capacidade do evento
     * @param precoIngresso o preço do ingresso
     * @param organizadora a organizadora responsável
     * @param data a data do evento
     * @param lineup a lista de atrações
     * @param duracao a duração do evento
     * @return o evento criado
     */
    public EventoFestival criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, 
                                     ArrayList<String> lineup, int duracao) {
        CaracteristicaDeEventoFestival caracteristicas = new CaracteristicaDeEventoFestival(lineup, duracao);
        EventoFestival novo_evento = new EventoFestival(caracteristicas, precoIngresso, nome, local, capacidade, organizadora, data);
        return novo_evento;
    }

    /**
     * Cria um evento do tipo jogo.
     * 
     * @param nome o nome do evento
     * @param local o local do evento
     * @param capacidade a capacidade do evento
     * @param precoIngresso o preço do ingresso
     * @param organizadora a organizadora responsável
     * @param data a data do evento
     * @param times os times que participarão
     * @param tipo o tipo do jogo
     * @return o evento criado
     */
    public EventoJogo criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, ArrayList<String> times, String tipo) {
        CaracteristicaDeEventoJogo caracteristicas = new CaracteristicaDeEventoJogo(times, tipo);
        EventoJogo novo_evento = new EventoJogo(nome, local, caracteristicas, precoIngresso, capacidade, organizadora, data);
        return novo_evento;
    }

    /**
     * Cria um evento de música ao vivo.
     * 
     * @param nome o nome do evento
     * @param local o local do evento
     * @param capacidade a capacidade do evento
     * @param precoIngresso o preço do ingresso
     * @param organizadora a organizadora responsável
     * @param data a data do evento
     * @param duration a duração da apresentação
     * @param setlist a lista de músicas
     * @return o evento criado
     */
    public EventoMusicaAoVivo criaEvento(String nome, Local local, int capacidade, double precoIngresso, Organizadora organizadora, LocalDate data, int duration, ArrayList<String> setlist) {
        CaracteristicaDeEventoMusicaAoVivo caracteristicas = new CaracteristicaDeEventoMusicaAoVivo(setlist, duration);
        EventoMusicaAoVivo novo_evento = new EventoMusicaAoVivo(nome, local, precoIngresso, caracteristicas, capacidade, organizadora, data);
        return novo_evento;
    }

    /**
     * Cria um evento do tipo show.
     * 
     * @param nome o nome do evento
     * @param local o local do evento
     * @param capacidade a capacidade do evento
     * @param precoIngresso o preço do ingresso
     * @param organizadora a organizadora responsável
     * @param data a data do evento
     * @param artista o artista principal
     * @return o evento criado
     */
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

    /**
     * Define a lista de eventos da organizadora.
     * 
     * @param eventos lista de eventos
     */
    public void adicionaEvento(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * Adiciona um evento à lista da organizadora.
     * 
     * @param evento evento a ser adicionado
     */
    public void adicionaEvento(Evento evento) {
        this.eventos.add(evento);
    }

    /**
     * Exibe todos os eventos da organizadora no console.
     */
    public void showEventos(){
        int i = 1;
        for (Evento evento : this.eventos){
            System.out.println(i + ">" + evento.getNome() + "- R$" + evento.getPrecoIngresso());
            i += 1;
        }
    }

    /**
     * Busca eventos que satisfaçam determinado filtro.
     * 
     * @param filtro filtro a ser aplicado
     * @return lista de eventos filtrados ou vazia caso nenhum satisfaça
     */
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
