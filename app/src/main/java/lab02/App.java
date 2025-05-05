/*
* Material usado na disciplina MC322 - Programação orientada a objetos.
*/

package lab02;  // Pacote da aplicação.

import java.time.LocalDate;  // Para manipulação de datas.
import java.util.ArrayList;  // Para trabalhar com listas dinâmicas.
import java.util.Scanner;  // Para capturar entrada do usuário.

import lab02.client.Cliente;  // Classe Cliente.
// Para gerenciar locais de eventos.
// Definir locais para eventos.
// Organiza eventos.
import lab02.events.*;  // Classes de eventos.
import lab02.filter.*;  // Filtros para eventos.
import lab02.exceptions.*;  // Exceções usadas no sistema.
import lab02.notifiable.Email;  // Para enviar e-mails.
import lab02.tickets.*;  // Classes de ingressos.

/**
 * Contém a estrutura de implementação da aplicação.
* 
* @author Lucas Beserra - 281815
*/
public class App {

    /**
     * Método principal da aplicação.
    * 
    * @param args Argumentos passados pela linha de comando.
    */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);  // Criação de scanner para entrada do usuário.

        // Criação de dois clientes.
        Cliente cliente_1 = new Cliente("Cliente 1", new Email("cliente_1@gmail.com"));
        Cliente cliente_2 = new Cliente("Cliente 2", new Email("cliente_2@gmail.com"));

        // Criação de imobiliária e organizadora de eventos.
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliaria freitas");
        Organizadora org = new Organizadora("Eventos e Afins", 123456789, "Rua da Paz 123");

        // Adiciona locais para eventos na imobiliária.
        imob.adicionarLocal(new Local("Autodromo", 500, imob));
        imob.adicionarLocal(new Local("Estadio Municipal", 1000, imob));
        imob.adicionarLocal(new Local("Praça Central", 300, imob));
        imob.adicionarLocal(new Local("Teatro Municipal", 200, imob));
        imob.adicionarLocal(new Local("Centro de Convencoes", 1500, imob));
        imob.adicionarLocal(new Local("Ginasio Poliesportivo", 800, imob));
        imob.adicionarLocal(new Local("Parque da Cidade", 10000, imob));
        imob.adicionarLocal(new Local("Auditorio Central", 300, imob));

        // Criação de uma lista de artistas para o evento de festival.
        ArrayList<String> lineup = new ArrayList<>();
        lineup.add("Artist 1");
        lineup.add("Artist 2");
        lineup.add("Artist 3");

        // Criação de uma lista de músicas para o evento de música ao vivo.
        ArrayList<String> setlist = new ArrayList<>();
        setlist.add("Song 1");
        setlist.add("Song 2");
        setlist.add("Song 3");

        // Tentativa de criar um evento de Festival.
        try {
            EventoFestival festival = org.criaEvento("LoolaPalooza", 
                null,  // Local do Evento
                300,   // Capacidade de 300 pessoas.
                199.90,  // Preço do ingresso.
                org,  // Organizadora.
                LocalDate.of(2025, 6, 22),  // Data do evento.
                lineup,  // Lista de artistas.
                6);  // Duração do evento.

            // Aloca o evento no local "Autódromo".
            imob.getLocais().get(0).alocarParaEvento(festival);
            org.adicionaEvento(festival);  // Adiciona o evento à organizadora.
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Festival: " + e.getMessage());
        }

        // Tentativa de criar um evento de Jogo.
        try {
            EventoJogo jogo = org.criaEvento("Final do Campeonato", 
                null,  // Local do Evento
                800,   // Capacidade de 800 pessoas.
                150.00,  // Preço do ingresso.
                org,  // Organizadora.
                LocalDate.of(2025, 7, 15),  // Data do evento.
                new ArrayList<String>(),  // Lista de artistas vazia.
                "Futebol");  // Tipo de evento (Futebol).

            // Aloca o evento no local "Estádio Municipal".
            imob.getLocais().get(1).alocarParaEvento(jogo);
            org.adicionaEvento(jogo);  // Adiciona o evento à organizadora.
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Jogo: " + e.getMessage());
        }

        // Tentativa de criar um evento de Música ao Vivo.
        try {
            EventoMusicaAoVivo musicaAoVivo = org.criaEvento("Concerto de Verao", 
                null,  // Local do Evento
                250,   // Capacidade de 250 pessoas.
                75.50,  // Preço do ingresso.
                org,  // Organizadora.
                LocalDate.of(2025, 8, 10),  // Data do evento.
                2,  // Duração do evento em horas.
                setlist);  // Setlist de músicas.

            // Aloca o evento no local "Praça Central".
            imob.getLocais().get(2).alocarParaEvento(musicaAoVivo);
            org.adicionaEvento(musicaAoVivo);  // Adiciona o evento à organizadora.
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Música ao Vivo: " + e.getMessage());
        }

        // Tentativa de criar um evento de Show de Stand-up Comedy.
        try {
            EventoShow show = org.criaEvento("Stand-up Comedy Night", 
                null,  // Local do Evento
                180,   // Capacidade de 180 pessoas.
                120.00,  // Preço do ingresso.
                org,  // Organizadora.
                LocalDate.of(2025, 8, 10),  // Data do evento.
                "Ana Beatriz");  // Nome do comediante.

            // Aloca o evento no local "Teatro Municipal".
            imob.getLocais().get(3).alocarParaEvento(show);
            org.adicionaEvento(show);  // Adiciona o evento à organizadora.
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Show: " + e.getMessage());
        }

        // Solicita ao usuário o que ele deseja fazer (comprar ingresso, filtrar eventos ou gerar evento).
        System.out.println("quer comprar ingresso e comparar clientes, filtrar eventos ou gerar um novo evento? (c/f/g)");
        String oper = s.nextLine();  // Captura a resposta do usuário.

        // Declaração de variáveis para manipulação de ingressos e clientes.
        int ind;
        Evento event;  // Evento selecionado.
        String ticket_type, answ;  // Tipo de ingresso e resposta do usuário.
        Ingresso ticket = null;  // Ingresso inicializado como nulo.
        Cliente client = cliente_1;  // Inicializa o cliente padrão como cliente_1.
        /**
         * Bloco responsável por permitir a compra de ingressos e a interação com clientes.
         * O usuário pode escolher comprar ingressos para eventos, cancelar ingressos e comparar clientes.
         */
        switch (oper){
            case ("c"):
                while (true){
                    // Solicita ao usuário escolher qual cliente irá comprar o ingresso
                    System.out.println("Qual cliente quer comprar? (1/2)");
                    int client_num = Integer.parseInt(s.nextLine());
                    // Verifica se o cliente é válido
                    if (client_num != 1 && client_num != 2){
                        System.err.println("Cliente inválido!");
                        System.exit(1);
                    } else if (client_num == 2) {
                        client = cliente_2;
                    } else {
                        client = cliente_1;
                    }

                    // Solicita ao usuário escolher o evento
                    System.out.println("Qual evento? (escolha um número)");
                    org.showEventos();
                    ind = Integer.parseInt(s.nextLine());
                    // Verifica se o evento escolhido é válido
                    if (ind > org.getEventos().size() || ind <= 0){
                        System.err.println("Evento Inválido!");
                        System.exit(1);
                    }
                    event = org.getEventos().get(ind - 1);
                    // Solicita ao usuário escolher o tipo de ingresso
                    System.out.println("Inteira, meia ou VIP? (i/m/v)");
                    ticket_type = s.nextLine();
                    // Cria o ingresso conforme o tipo escolhido
                    switch (ticket_type) {
                        case ("i"):
                            ticket = new IngressoInteira(event, event.getPrecoIngresso(), true);
                            break;

                        case ("m"):
                            ticket = new IngressoMeia(event, event.getPrecoIngresso(), true);
                            break;

                        case ("v"):
                            ticket = new IngressoVip(event, event.getPrecoIngresso(), false);
                            break;

                        default:
                            System.out.println("Tipo de ingresso inválido! Tente novamente.");
                            System.exit(1);
                    }
                    // Tenta vender o ingresso para o cliente
                    try {
                        event.venderIngresso(client, ticket);
                    } catch (IngressoEsgotadoException e) {
                        System.err.println(e.getMessage() + " Escolha outro evento!");
                        continue;
                    }
                    // Pergunta ao usuário se ele quer cancelar algum ingresso
                    System.out.println("Quer cancelar algum ingresso? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("s")){
                        // Solicita ao usuário escolher qual cliente irá cancelar o ingresso
                        System.out.println("Qual cliente quer cancelar? (1/2)");
                        client_num = Integer.parseInt(s.nextLine());
                        // Verifica se o cliente é válido
                        if (client_num != 1 && client_num != 2){
                            System.err.println("Cliente inválido!");
                            System.exit(1);
                        } else if (client_num == 2){
                            client = cliente_2;
                        } else {
                            client = cliente_1;
                        }
                        // Permite ao usuário cancelar ingressos
                        while (true){
                            try{
                                client.getNumIngressos();
                            } catch (ClienteSemIngressosException e){
                                System.err.println(e.getMessage());
                                break;
                            }
                            client.showIngressos();
                            System.out.println("Escolha um ingresso (escolha o número) ou digite '0' para sair");
                            ind = Integer.parseInt(s.nextLine());
                            // Verifica se a escolha do ingresso é válida
                            if (ind > client.getIngressos().size() || ind < 0){
                                System.err.println("Input inválido!");
                                System.exit(1);
                            }
                            if (ind == 0){
                                break;
                            }
                            // Tenta cancelar o ingresso escolhido
                            try{
                                client.cancelarIngresso(client.getIngressos().get(ind-1));
                            } catch (CancelamentoNaoPermitidoException | IngressoNaoEncontradoException e){
                                System.err.println(e.getMessage() + " Tente novamente!");
                                continue;
                            } 
                            // Pergunta ao usuário se quer cancelar mais ingressos
                            System.out.println("Quer cancelar mais ingressos? (s/n)");
                            answ = s.nextLine();
                            if (answ.equals("s")){
                                continue;
                            } else if (answ.equals("n")){
                                break;
                            } else {
                                System.err.println("Input inválido!");
                                System.exit(1);
                            }
                        }
                    } else if (!answ.equals("n")){
                        System.err.println("Input inválido!");
                        System.exit(1);
                    }
                    // Pergunta ao usuário se deseja comprar mais ingressos
                    System.out.println("Quer comprar mais ingressos? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("n")){
                        // Tenta comparar os ingressos dos dois clientes
                        try{
                            cliente_1.getNumIngressos();
                            cliente_2.getNumIngressos();
                            if (cliente_1.compareTo(cliente_2)){
                                System.out.println("O cliente 1 vai aos mesmos eventos cliente 2!");
                            } else {
                                System.out.println("O cliente 1 não vai aos mesmos eventos cliente 2!");
                            }
                        } catch (ClienteSemIngressosException e) {
                            System.out.println("Comparação inválida: " + e.getMessage());
                        }
                        break;
                    } else if (answ.equals("s")){
                        continue;
                    } else{
                        System.err.println("Input inválido!");
                        System.exit(1);
                    }
                }
                // Exibe os ingressos dos dois clientes
                System.out.println("Cliente 1:\n");
                cliente_1.showIngressos();
                System.out.println("Cliente 2:\n");
                cliente_2.showIngressos();
                
                // Exibe os e-mails dos clientes
                System.err.println("\nE-mail do cliente 1:");
                cliente_1.getEmail().displayNotification();
                System.err.println("\nE-mail do cliente 2:");
                cliente_2.getEmail().displayNotification();
                break;

            case ("f"):
                boolean and_filter = false;
                // Pergunta ao usuário se deseja usar um filtro do tipo AND
                System.out.println("Usar AndFilter? (s/n)");
                answ = s.nextLine();
                if (answ.equals("s")){
                    and_filter = true;
                } else if (!answ.equals("n")){
                    System.err.println("Input Inválido! Tente novamente");
                    System.exit(1);
                }
                ArrayList<Filter<?>> filters = new ArrayList<Filter<?>>();
                Filter<?> filtro;
                ArrayList<Evento> result = new ArrayList<Evento>();
                while (true) {
                    // Solicita ao usuário o tipo de filtragem
                    System.out.println("Que tipo de filtragem?");
                    String type = s.nextLine();
                    switch (type) {
                        case "Tipo":
                            // Solicita ao usuário o tipo de evento
                            System.out.println("Qual tipo de evento?");
                            String event_type = s.nextLine();
                            switch (event_type) {
                                case "Festival":
                                    filtro = new EventoPorTipoFilter(EventoFestival.class);
                                    filters.add(filtro);
                                    result = org.buscarEventos(filtro);
                                    break;
                                case "Jogo":
                                    filtro = new EventoPorTipoFilter(EventoJogo.class);
                                    filters.add(filtro);
                                    result = org.buscarEventos(filtro);
                                    break;
                                case "Musica":
                                    filtro = new EventoPorTipoFilter(EventoMusicaAoVivo.class);
                                    filters.add(filtro);
                                    result = org.buscarEventos(filtro);
                                    break;
                                case "Show":
                                    filtro = new EventoPorTipoFilter(EventoShow.class);
                                    filters.add(filtro);
                                    result = org.buscarEventos(filtro);
                                    break;
                                default:
                                    System.err.println("Tipo de evento inválido!");
                                    System.exit(1);
                            }
                            break;
                        
                        case "Org":
                            filtro = new EventoPorOrganizadoraFilter(org);
                            filters.add(filtro);
                            result = org.buscarEventos(filtro);
                            break;

                        case "Nome":
                            // Solicita ao usuário o nome do evento
                            System.out.println("Digite um nome:");
                            String name = s.nextLine();
                            filtro = new EventoPorNomeFilter(name);
                            filters.add(filtro);
                            result = org.buscarEventos(filtro);
                            break;

                        case "Local":
                            // Solicita ao usuário escolher um local para filtrar eventos
                            System.out.println("Escolha um local:");
                            try{
                                Local local = imob.buscaLocal(s.nextLine());
                                filtro = new EventoPorLocalFilter(local);
                                filters.add(filtro);
                                result = org.buscarEventos(filtro);
                                break;
                            } catch (LocalNaoEncontradoException e){
                                System.err.println(e.getMessage());
                                System.exit(1);
                            }

                        case "Data":
                            // Solicita ao usuário escolher uma data para filtrar eventos
                            System.out.println("Escolha uma data (DIA/MÊS/ANO)");
                            String[] data = s.nextLine().split("/");
                            int[] data_format = new int[data.length];
                            for (int i = 0; i < data.length; i++){
                                data_format[i] = Integer.parseInt(data[i]);
                            }
                            filtro = new EventoPorDataFilter(LocalDate.of(data_format[2], data_format[1], data_format[0]));
                            filters.add(filtro);
                            result = org.buscarEventos(filtro);
                            break;
                    
                        default:
                            System.err.println("Filtro Inválido!");
                            System.exit(1);
                    }
                    // Se o filtro AND não for usado ou já houver dois filtros, termina o loop
                    if (!and_filter || filters.size() == 2){
                        break;
                    }
                }
                // Se o filtro AND for usado, aplica o filtro
                if(and_filter){
                    result = new AndFilter(filters.get(0), filters.get(1)).filter(org.getEventos());
                }
                // Exibe o resultado da filtragem
                System.out.println("Resultado:");
                for (Evento e : result){
                    e.descricao();
                    System.out.println();
                }
                break;
            
                case "g":
                while (true){
                    event = null;
                    // Solicita ao usuário escolher o tipo de evento
                    System.out.println("Que tipo de evento (escolha um número)");
                    System.out.println("1> Festival\n2> Jogo\n3> Musica ao Vivo\n4> Show");
                    ind = Integer.parseInt(s.nextLine());
            
                    // Criação do evento de acordo com a escolha do usuário
                    switch (ind) {
                        case 1:
                            // Solicita o nome do evento para o tipo Festival
                            System.out.println("Escolha um nome:");
                            answ = s.nextLine();
                            event = org.criaEvento(answ, 
                            null,
                            300,
                            199.90,
                            org,
                            LocalDate.of(2025, 6, 22),
                            lineup, 6);
                            break;
            
                        case 2:
                            // Solicita o nome do evento para o tipo Jogo
                            System.out.println("Escolha um nome:");
                            answ = s.nextLine();
                            event = org.criaEvento(answ, 
                            null,
                            800,
                            150.00,
                            org,
                            LocalDate.of(2025, 7, 15),
                            new ArrayList<String>(), "Futebol");
                            break;
            
                        case 3:
                            // Solicita o nome do evento para o tipo Música ao Vivo
                            System.out.println("Escolha um nome:");
                            answ = s.nextLine();
                            event = org.criaEvento(answ, 
                            null,
                            250,
                            75.50,
                            org,
                            LocalDate.of(2025, 8, 10),
                            2,
                            setlist);
                            break;
            
                        case 4:
                            // Solicita o nome do evento para o tipo Show
                            System.out.println("Escolha um nome:");
                            answ = s.nextLine();
                            event = org.criaEvento(answ, 
                            null,
                            180,
                            120.00,
                            org,
                            LocalDate.of(2025, 8, 10),
                            "Ana Beatriz");
                            break;
            
                        // Caso o evento escolhido seja inválido
                        default:
                            System.err.println("Evento inválido!");
                            System.exit(1);
                            break;
                    }
            
                    // Alocação de local para o evento
                    while (true){
                        // Solicita ao usuário escolher um local
                        System.out.println("Escolha um local (escolha um número)");
                        imob.showLocais();
                        ind = Integer.parseInt(s.nextLine());
                        // Verifica se a escolha do local é válida
                        if (ind > imob.getLocais().size() || ind < 1){
                            System.err.println("Input inválido!");
                            System.exit(1);
                        }
                        try{
                            // Tenta alocar o evento no local escolhido
                            imob.getLocais().get(ind - 1).alocarParaEvento(event);
                            break;
                        } catch (LocalIndisponivelException e){
                            // Caso o local não esteja disponível, exibe a mensagem de erro
                            System.err.println(e.getMessage());
                        } catch (CapacidadeInsuficienteException e){
                            // Caso a capacidade do local seja insuficiente, exibe a mensagem de erro
                            System.err.println(e.getMessage());
                        }
                    }
            
                    // Adiciona o evento criado à organizadora de eventos
                    org.adicionaEvento(event);
                    System.out.println("Evento criado com sucesso! quer criar outro? (s/n)");
                    answ = s.nextLine();
                    // Pergunta ao usuário se deseja criar outro evento
                    if (answ.equals("n")){
                        break;
                    } else if (answ.equals("s")){
                        continue;
                    } else{
                        System.err.println("Input inválido!");
                        System.exit(1);
                    }
                }
            
                // Exibe os eventos criados
                org.showEventos();
                break;
            
            default:
                // Caso a escolha do usuário seja inválida, exibe uma mensagem de erro
                System.err.println("Input inválido! Tente novamente");
                System.exit(1);
            }
            
        // Fecha o scanner após o uso
        s.close();
    }
}
