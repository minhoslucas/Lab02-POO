/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02.client;

import java.util.ArrayList;

import lab02.exceptions.CancelamentoNaoPermitidoException;
import lab02.exceptions.IngressoNaoEncontradoException;
import lab02.exceptions.ClienteSemIngressosException;
import lab02.notifiable.Email;
import lab02.tickets.Ingresso;

public class Cliente implements Comparable<Cliente>{

    private String nome;
    private Email email;
    private ArrayList<Ingresso> ingressos;
    

    /**
     * Construtor da classe cliente
     * @param nome o nome do cliente
     * @param email o email do cliente
     */
    public Cliente(String nome, Email email){
        this.nome = nome;
        this.email = email;
        this.ingressos = new ArrayList<Ingresso>();
    }

    /**
     * Retorna o nome do cliente
     * @return o nome do cliente
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do evento para `nome` 
     * @param nome o novo nome do cliente
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o email do cliente
     * @return o email do cliente
     */
    public Email getEmail(){
        return email;
    }

    /**
     * Altera o email do cliente para `email` 
     * @param email o novo email do cliente
     */
    public void setEmail(Email email){
        this.email = email;
    }
    /**
     * Retorna a lista de ingressos do cliente
     * @return a lista de ingressos do cliente
     */

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public int getNumIngressos() throws ClienteSemIngressosException{
        if (this.ingressos.size() == 0){
            throw new ClienteSemIngressosException("Cliente de e-mail " + this.email.getEmailText() + " sem ingressos!");
        }
        return this.ingressos.size();
    }

    /**
     * Adiciona um ingresso à lista de ingressos do cliente.
     * 
     * @param ingresso o ingresso a ser adicionado
     */
    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    public void adicionarIngresso(ArrayList<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    private void removerIngresso(Ingresso ingresso) {
        this.ingressos.remove(ingresso);
    }

    public void cancelarIngresso(Ingresso ingresso) throws CancelamentoNaoPermitidoException, IngressoNaoEncontradoException {
        boolean found = false;
        if (!ingresso.isCancelable()){
            throw new CancelamentoNaoPermitidoException("O ingresso não pode ser cancelado");
        }
        for (Ingresso test : ingressos){
            if (test.equals(ingresso)){
                found = true;
                break;
            }
        }
        if (!found){
            throw new IngressoNaoEncontradoException("Ingresso não encontrado");
        }
        this.removerIngresso(ingresso);
        ingresso.getEvento().cancelarIngresso(ingresso);
        this.email.addNotification("Novo ingresso cancelado: " + ingresso.getEvento().getNome());
        System.out.println("Ingresso cancelado com sucesso");
    }

    public void showIngressos(){
        try {
            this.getNumIngressos();
        } catch (ClienteSemIngressosException e){
            System.out.println("Cliente sem Ingressos!");
        }
        int i = 1;
        for (Ingresso ticket: this.ingressos){
            System.out.println(i + "> " + ticket);
            i++;
        }
    }

    private boolean compareTickets(Ingresso ingresso1, Ingresso ingresso2){
        boolean evento;
        evento = ingresso1.getEvento().equals(ingresso2.getEvento());
        return evento;
    }

    public boolean compareTo(Cliente cliente){
        if (this.ingressos.size() != cliente.getIngressos().size()){
            return false;
        }
        for (int i = 0; i < this.ingressos.size(); i++) {
            if (!this.compareTickets(cliente.getIngressos().get(i), this.ingressos.get(i))){
                return false;
            }
        }
        return true;
    }
}
