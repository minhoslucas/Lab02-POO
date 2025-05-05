/*
 * Local.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02.events;

import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.LocalIndisponivelException;

/**
 * Contém a estrutura de implementação de um Local utilizado em eventos.
 * Responsável por armazenar nome, capacidade e associação à imobiliária.
 * 
 * @author Lucas Beserra - 281815  
 * Comentários feitos por IA e revisados posteriormente
 */
public class Local {
    private String nome;
    private double capacidadeMaxima;
    private ImobiliariaDeEventos imobiliaria;

    /**
     * Construtor da classe Local.
     * 
     * @param nome o nome do local
     * @param capacidadeMaxima a capacidade máxima do local
     * @param imoboliaria a imobiliária responsável pelo local
     */
    public Local(String nome, double capacidadeMaxima, ImobiliariaDeEventos imoboliaria) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.imobiliaria = imoboliaria;
    }

    /**
     * Retorna o nome do local.
     * 
     * @return o nome do local
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do local para o valor fornecido.
     * 
     * @param nome o novo nome do local
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a capacidade máxima do local.
     * 
     * @return a capacidade do local
     */
    public double getCapacidade() {
        return capacidadeMaxima;
    }

    /**
     * Define a nova capacidade máxima do local.
     * 
     * @param capacidadeMaxima a nova capacidade
     */
    public void setCapacidade(double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    /**
     * Retorna a imobiliária responsável pelo local.
     * 
     * @return a imobiliária associada
     */
    public ImobiliariaDeEventos getImobiliaria() {
        return this.imobiliaria;
    }

    /**
     * Define a imobiliária responsável pelo local.
     * 
     * @param imobiliaria nova imobiliária associada
     */
    public void setImobiliaria(ImobiliariaDeEventos imobiliaria) {
        try {
            if (imobiliaria == null) {
                throw new IllegalArgumentException("Imobiliária não pode ser nula");
            }
            this.imobiliaria = imobiliaria;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Aloca este local para um evento, verificando se há capacidade e disponibilidade.
     * 
     * @param evento o evento ao qual se deseja alocar o local
     * @throws CapacidadeInsuficienteException se o local não comportar a capacidade do evento
     * @throws LocalIndisponivelException se o local já estiver alocado para outro evento
     */
    public void alocarParaEvento(Evento evento) throws CapacidadeInsuficienteException, LocalIndisponivelException {

        if (this.capacidadeMaxima < evento.getCapacidade()) {
            throw new CapacidadeInsuficienteException("local com capacidade insuficiênte");
        }

        for (Evento test : evento.getOrganizadora().getEventos()) {
            if (test.getLocal().equals(this)) {
                throw new LocalIndisponivelException("Local indisponível para o evento");
            }
        }

        evento.setLocal(this);
    }
}
