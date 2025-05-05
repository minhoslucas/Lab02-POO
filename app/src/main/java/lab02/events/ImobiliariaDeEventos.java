/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

 package lab02.events;

 import java.util.ArrayList;
 
 import lab02.exceptions.LocalNaoEncontradoException;
 
 /**
  * Representa uma imobiliária responsável por gerenciar locais disponíveis para eventos.
  * Permite adicionar, buscar e listar locais cadastrados.
  * 
  * @author Lucas Beserra - 281815  
  * Comentários feitos por IA e revisados posteriormente
  */
 public class ImobiliariaDeEventos {
     
     private ArrayList<Local> locais;
     private String nome;
 
     /**
      * Construtor da classe ImobiliariaDeEventos.
      * 
      * @param nome o nome da imobiliária de eventos
      */
     public ImobiliariaDeEventos(String nome) {
         this.nome = nome;
         this.locais = new ArrayList<Local>();
     }
 
     /**
      * Adiciona um local à lista de locais disponíveis.
      * 
      * @param local o local a ser adicionado
      */
     public void adicionarLocal(Local local) {
         this.locais.add(local);
     }
 
     /**
      * Retorna o nome da imobiliária.
      * 
      * @return o nome da imobiliária
      */
     public String getNome() {
         return this.nome;
     }
 
     /**
      * Altera o nome da imobiliária de eventos.
      * 
      * @param nome o novo nome da imobiliária
      */
     public void setNome(String nome) {
         this.nome = nome;
     }
 
     /**
      * Retorna a lista de locais cadastrados.
      * 
      * @return uma lista com os locais disponíveis
      */
     public ArrayList<Local> getLocais() {
         return this.locais;
     }
 
     /**
      * Busca um local pelo nome.
      * 
      * @param nome o nome do local a ser buscado
      * @return o local correspondente ao nome
      * @throws LocalNaoEncontradoException se o local não for encontrado
      */
     public Local buscaLocal(String nome) throws LocalNaoEncontradoException {
         Local local = null;
         for (Local test : this.locais) {
             if (test.getNome().equals(nome)) {
                 local = test;
                 break;
             }
         }
         if (local == null) {
             throw new LocalNaoEncontradoException("Local não encontrado");
         }
         return local;
     }
 
     /**
      * Busca todos os locais com capacidade menor ou igual à especificada.
      * 
      * @param capacidade_max a capacidade máxima desejada
      * @return uma lista de locais compatíveis com a capacidade informada
      * @throws LocalNaoEncontradoException se nenhum local atender ao critério
      */
     public ArrayList<Local> buscaLocal(int capacidade_max) throws LocalNaoEncontradoException {
         ArrayList<Local> locais = new ArrayList<Local>();
         for (Local test : this.locais) {
             if (test.getCapacidade() <= capacidade_max) {
                 locais.add(test);
             }
         }
         if (locais.size() == 0) {
             throw new LocalNaoEncontradoException("Local não encontrado");
         }
         return locais;
     }
 
     /**
      * Exibe os locais cadastrados e suas capacidades no console.
      */
     public void showLocais() {
         int i = 0;
         for (Local local : this.locais) {
             System.out.println(i + "> " + local.getNome() + " Capacidade: " + local.getCapacidade());
             i++;
         }
     }
 }
 