/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.ArrayList;

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

    public Local buscaLocal(String nome) {
        Local local = null;
        for (Local test : this.locais) {
            if (test.getNome().equals(nome)) {
                local = test;
            }
            if (local == null) {
                // throw new LocalNaoEncontradoException("Local não encontrado");
            }
            //NAO SEI FAZER ISSO NGM ME EXPLICOU COMO FAZ ISSO VOU ME MATAR
        }
        return local;
    }
}
