package lab02;

import java.util.ArrayList;
import java.util.List;

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

    public Evento criaEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, 
                            List<String> lineup, int duracao) {
        EventoFestival novo_evento = new EventoFestival(nome, local, precoIngresso, organizadora, data, lineup, duracao);
        return novo_evento;
    }

    public Evento criaEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, List<String> times) {
        EventoJogo novo_evento = new EventoJogo(nome, local, precoIngresso, organizadora, data, times);
        return novo_evento;
    }

    public Evento criaEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, String artista) {
        EventoShow novo_evento = new EventoShow(nome, local, precoIngresso, organizadora, data, artista);
        return novo_evento;
    }
 
}
