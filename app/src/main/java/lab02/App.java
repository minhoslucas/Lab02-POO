/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import lab02.client.Cliente;
import lab02.events.*;
import lab02.filter.*;
import lab02.events.ImobiliariaDeEventos;
import lab02.events.Local;
import lab02.events.Organizadora;
import lab02.exceptions.CancelamentoNaoPermitidoException;
import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.ClienteSemIngressosException;
import lab02.exceptions.IngressoEsgotadoException;
import lab02.exceptions.IngressoNaoEncontradoException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.exceptions.LocalNaoEncontradoException;
import lab02.notifiable.Email;
import lab02.tickets.Ingresso;
import lab02.tickets.IngressoInteira;
import lab02.tickets.IngressoMeia;
import lab02.tickets.IngressoVip;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Lucas Beserra - 281815
 */
public class App {

    /**
     * Aplicação principal
     * @param args
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        //criação de clientes
        Cliente cliente_1 = new Cliente("Cliente 1", new Email("cliente_1@gmail.com"));
        Cliente cliente_2 = new Cliente("Cliente 2", new Email("cliente_2@gmail.com"));

        //criação de eventos e locais
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliaria freitas");
        Organizadora org = new Organizadora("Eventos e Afins", 123456789, "Rua da Paz 123");

        imob.adicionarLocal(new Local("Autodromo", 500, imob));
        imob.adicionarLocal(new Local("Estadio Municipal", 1000, imob));
        imob.adicionarLocal(new Local("Praça Central", 300, imob));
        imob.adicionarLocal(new Local("Teatro Municipal", 200, imob));
        imob.adicionarLocal(new Local("Centro de Convencoes", 1500, imob));
        imob.adicionarLocal(new Local("Ginasio Poliesportivo", 800, imob));
        imob.adicionarLocal(new Local("Parque da Cidade", 10000, imob));
        imob.adicionarLocal(new Local("Auditorio Central", 300, imob));
        ArrayList<String> lineup = new ArrayList<>();
        lineup.add("Artist 1");
        lineup.add("Artist 2");
        lineup.add("Artist 3");
        ArrayList<String> setlist = new ArrayList<>();
        setlist.add("Song 1");
        setlist.add("Song 2");
        setlist.add("Song 3");
        try {
            EventoFestival festival = org.criaEvento("LoolaPalooza", 
            null,
            300,
            199.90,
            org,
            LocalDate.of(2025, 6, 22),
            lineup, 6);
            imob.getLocais().get(0).alocarParaEvento(festival);
            org.adicionaEvento(festival);
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Festival: " + e.getMessage());
        }

        try {
            EventoJogo jogo = org.criaEvento("Final do Campeonato", 
            null,
            800,
            150.00,
            org,
            LocalDate.of(2025, 7, 15),
            new ArrayList<String>(), "Futebol");
            imob.getLocais().get(1).alocarParaEvento(jogo);
            org.adicionaEvento(jogo);
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Jogo: " + e.getMessage());
        }

        try {
            EventoMusicaAoVivo musicaAoVivo = org.criaEvento("Concerto de Verao", 
            null,
            250,
            75.50,
            org,
            LocalDate.of(2025, 8, 10),
            2,
            setlist);
            imob.getLocais().get(2).alocarParaEvento(musicaAoVivo);
            org.adicionaEvento(musicaAoVivo);
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Música ao Vivo: " + e.getMessage());
        }

        try {
            EventoShow show = org.criaEvento("Stand-up Comedy Night", 
            null,
            180,
            120.00,
            org,
            LocalDate.of(2025, 8, 10),
            "Ana Beatriz");
            imob.getLocais().get(3).alocarParaEvento(show);
            org.adicionaEvento(show);
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Erro ao criar ou alocar evento Show: " + e.getMessage());
        }

        System.out.println("quer comprar ingresso e comparar clientes, filtrar eventos ou gerar um novo evento? (c/f/g)");
        String oper = s.nextLine();
        int ind;
        Evento event;
        String ticket_type, answ;
        Ingresso ticket = null;
        Cliente client = cliente_1;
        switch (oper){
            case ("c"):
                while (true){
                    System.out.println("Qual cliente quer comprar? (1/2)");
                    int client_num = Integer.parseInt(s.nextLine());
                    if (client_num != 1 && client_num != 2){
                        System.err.println("Cliente inválido!");
                        System.exit(1);
                    } else if (client_num == 2) {
                        client = cliente_2;
                    } else {
                        client = cliente_1;
                    }

                    System.out.println("Qual evento? (escolha um número)");
                    org.showEventos();
                    ind = Integer.parseInt(s.nextLine());
                    if (ind > org.getEventos().size() || ind <= 0){
                        System.err.println("Evento Inválido!");
                        System.exit(1);
                    }
                    event = org.getEventos().get(ind - 1);
                    System.out.println("Inteira, meia ou VIP? (i/m/v)");
                    ticket_type = s.nextLine();
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
                    try {
                        event.venderIngresso(client, ticket);
                    } catch (IngressoEsgotadoException e) {
                        System.err.println(e.getMessage() + " Escolha outro evento!");
                        continue;
                    }
                    System.out.println("Quer cancelar algum ingresso? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("s")){
                        System.out.println("Qual cliente quer cancelar? (1/2)");
                        client_num = Integer.parseInt(s.nextLine());
                        if (client_num != 1 && client_num != 2){
                            System.err.println("Cliente inválido!");
                            System.exit(1);
                        } else if (client_num == 2){
                            client = cliente_2;
                        } else {
                            client = cliente_1;
                        }
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
                            if (ind > client.getIngressos().size() || ind < 0){
                                System.err.println("Input inválido!");
                                System.exit(1);
                            }
                            if (ind == 0){
                                break;
                            }
                            try{
                                client.cancelarIngresso(client.getIngressos().get(ind-1));
                            } catch (CancelamentoNaoPermitidoException | IngressoNaoEncontradoException e){
                                System.err.println(e.getMessage() + " Tente novamente!");
                                continue;
                            } 
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
                    System.out.println("Quer comprar mais ingressos? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("n")){
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
                System.out.println("Cliente 1:\n");
                cliente_1.showIngressos();
                System.out.println("Cliente 2:\n");
                cliente_2.showIngressos();
                
                System.err.println("\nE-mail do cliente 1:");
                cliente_1.getEmail().displayNotification();
                System.err.println("\nE-mail do cliente 2:");
                cliente_2.getEmail().displayNotification();
                break;

            case ("f"):
                boolean and_filter = false;
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
                    System.out.println("Que tipo de filtragem?");
                    String type = s.nextLine();
                    switch (type) {
                        case "Tipo":
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
                            System.out.println("Digite um nome:");
                            String name = s.nextLine();
                            filtro = new EventoPorNomeFilter(name);
                            filters.add(filtro);
                            result = org.buscarEventos(filtro);
                            break;
    
                        case "Local":
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
                    if (!and_filter || filters.size() == 2){
                        break;
                    }
                }
                if(and_filter){
                    result = new AndFilter(filters.get(0), filters.get(1)).filter(org.getEventos());
                }
                System.out.println("Resultado:");
                for (Evento e : result){
                    e.descricao();
                    System.out.println();
                }
                break;
            
            case "g":
                while (true){
                    event = null;
                    System.out.println("Que tipo de evento (escolha um número)");
                    System.out.println("1> Festival\n2> Jogo\n3> Musica ao Vivo\n4> Show");
                    ind = Integer.parseInt(s.nextLine());

                    switch (ind) {
                        case 1:
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
                    
                        default:
                            System.err.println("Evento inválido!");
                            System.exit(1);
                            break;
                    }
                    while (true){
                        System.out.println("Escolha um local (escolha um número)");
                        imob.showLocais();
                        ind = Integer.parseInt(s.nextLine());
                        if (ind > imob.getLocais().size() || ind < 1){
                            System.err.println("Input inválido!");
                            System.exit(1);
                        }try{
                            imob.getLocais().get(ind - 1).alocarParaEvento(event);
                            break;
                        } catch (LocalIndisponivelException e){
                            System.err.println(e.getMessage());
                        } catch (CapacidadeInsuficienteException e){
                            System.err.println(e.getMessage());
                        }
                    }
                    org.adicionaEvento(event);
                    System.out.println("Evento criado com sucesso! quer criar outro? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("n")){
                        break;
                    } else if (answ.equals("s")){
                        continue;
                    } else{
                        System.err.println("Input inválido!");
                        System.exit(1);
                    }
                }
            org.showEventos();
            break;
            default:
                System.err.println("Input inválido! Tente novamente");
                System.exit(1);
        }
        s.close();
    }
}
