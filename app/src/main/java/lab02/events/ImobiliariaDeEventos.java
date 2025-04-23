/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02.events;

import java.util.ArrayList;

import lab02.exceptions.LocalNaoEncontradoException;

public class ImobiliariaDeEventos {
    
    private ArrayList<Local> locais;
    private String nome;


    /**
     * Construtor da classe ImobiliariaDeEventos
     * @param nome o nome da imobiliária de eventos
     */
    public ImobiliariaDeEventos(String nome) {
        this.nome = nome;
        this.locais = new ArrayList<Local>();
    }

    /**
     * Adiciona um local à lista de locais disponíveis
     * @param local o local a ser adicionado
     */
    public void adicionarLocal(Local local) {
        this.locais.add(local);
    }
    /**
     * Retorna a lista de locais disponíveis
     * @return a lista de locais disponíveis
     */

    public String getNome() {
        return this.nome;
    }
    
    /**
     * Altera o nome da imobiliária de eventos para `nome` 
     * @param nome o novo nome da imobiliária de eventos
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Local> buscaLocal(String nome) throws LocalNaoEncontradoException{
        ArrayList<Local> locais = new ArrayList<Local>();
        for (Local test : this.locais) {
            if (test.getNome().equals(nome)) {
                locais.add(test);
            }
        }
        if (locais.size() == 0) {
            throw new LocalNaoEncontradoException("Local não encontrado");
        }
        return locais;
    }

    public ArrayList<Local> buscaLocal(int capacidade_max) throws LocalNaoEncontradoException {
        ArrayList<Local> locais = new ArrayList<Local>();
        for (Local test : this.locais) {
            if (test.getCapacidade() <= capacidade_max){
                locais.add(test);
            }
        }
        if (locais.size() == 0) {
            throw new LocalNaoEncontradoException("Local não encontrado");
        }
        return locais;
    }

}
