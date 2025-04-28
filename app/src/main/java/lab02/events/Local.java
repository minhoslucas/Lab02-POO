/*
 * Local.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02.events;

import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.LocalIndisponivelException;

/**
 * Contém a estrutura de implementação de um Local.
 * 
 * @author Gabriel Leite - 216180
 */
public class Local{
    private String nome;
    private double capacidadeMaxima;
    private ImobiliariaDeEventos imobiliaria;

    /**
     * Construtor da classe Local
     * @param nome o nome do local
     */
    public Local(String nome, double capacidadeMaxima, ImobiliariaDeEventos imoboliaria){
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.imobiliaria = imoboliaria;
    }

    /**
     * Retorna o nome do evento
     * @return o nome do evento
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do evento para `nome` 
     * @param nome o novo nome do evento
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna a capacidade do local
     * @return a capacidade do local
     */
    public double getCapacidade(){
        return capacidadeMaxima;
    }
    
    /**
     * Altera a capacidade máxima do local para `capacidadeMaxima` 
     * @param capacidadeMaxima a nova capacidade máxima do local
     */
    public void setCapacidade(double capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public ImobiliariaDeEventos getImobiliaria(){
        return this.imobiliaria;
    }

    public void setImobiliaria(ImobiliariaDeEventos imobiliaria){
        try {
            if (imobiliaria == null) {
                throw new IllegalArgumentException("Imobiliária não pode ser nula");
            }
            this.imobiliaria = imobiliaria;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alocarParaEvento(Evento evento) {
        try{
            if (this.capacidadeMaxima < evento.getCapacidade()){
                throw new CapacidadeInsuficienteException("local com capacidade insuficiênte");
            }
            for (Evento test : evento.getOrganizadora().getEventos()){
                if (test.getLocal().equals(this)){
                    throw new LocalIndisponivelException("Local indisponível para o evento");
                }
            }
            
            evento.setLocal(this);
        } catch (CapacidadeInsuficienteException e){
            System.out.println(e.getMessage());
        } catch (LocalIndisponivelException e){
            System.out.println(e.getMessage());
        }
    }
}
