/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02;

import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String email;
    private ArrayList<Ingresso> ingressos;
    

    /**
     * Construtor da classe cliente
     * @param nome o nome do cliente
     * @param email o email do cliente
     */
    public Cliente(String nome, String email){
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
    public String getEmail(){
        return email;
    }

    /**
     * Altera o email do cliente para `email` 
     * @param email o novo email do cliente
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * Retorna a lista de ingressos do cliente
     * @return a lista de ingressos do cliente
     */

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
    /**
     * Altera a lista de ingressos do cliente para `ingressos` 
     * @param ingressos a nova lista de ingressos do cliente
     */

    public void setIngressos(ArrayList<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    /**
     * Adiciona um ingresso à lista de ingressos do cliente.
     * 
     * @param ingresso o ingresso a ser adicionado
     */
    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }
}