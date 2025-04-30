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
import lab02.exceptions.IngressoEsgotadoException;
import lab02.exceptions.LocalNaoEncontradoException;
import lab02.notifiable.Email;
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
        Cliente cliente_1 = new Cliente("Lucas Beserra", new Email("lucas@gmail.com"));
        Cliente cliente_2 = new Cliente("Carlos Fernandes", new Email("carlos@gmail.com"));

        //criação de eventos e locais
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliaria freitas");
        Organizadora org = new Organizadora("Eventos e Afins", 123456789, "Rua da Paz 123");

        imob.adicionarLocal(new Local("Autodromo", 500, imob));
        imob.adicionarLocal(new Local("Estádio Municipal", 1000, imob));
        imob.adicionarLocal(new Local("Praça Central", 300, imob));
        imob.adicionarLocal(new Local("Teatro Municipal", 200, imob));
        imob.adicionarLocal(new Local("Centro de Convenções", 1500, imob));
        imob.adicionarLocal(new Local("Ginásio Poliesportivo", 800, imob));
        imob.adicionarLocal(new Local("Parque da Cidade", 10000, imob));
        imob.adicionarLocal(new Local("Auditório Central", 300, imob));

        EventoFestival festival = org.criaEvento("LoolaPalooza", 
        null,
        300,
        199.90,
        org,
        LocalDate.of(2025, 6, 22),
        new ArrayList<String>(), 6);
        imob.getLocais().get(0).alocarParaEvento(festival);
        org.adicionaEvento(festival);

        EventoJogo jogo = org.criaEvento("Final do Campeonato", 
        null,
        800,
        150.00,
        org,
        LocalDate.of(2025, 7, 15),
        new ArrayList<String>(), "Futebol");
        imob.getLocais().get(1).alocarParaEvento(jogo);
        org.adicionaEvento(jogo);

        EventoMusicaAoVivo musicaAoVivo = org.criaEvento("Concerto de Verão", 
        null,
        250,
        75.50,
        org,
        LocalDate.of(2025, 8, 10),
        2,
        new ArrayList<String>());
        imob.getLocais().get(2).alocarParaEvento(musicaAoVivo);
        org.adicionaEvento(musicaAoVivo);

        EventoShow show = org.criaEvento("Stand-up Comedy Night", 
        null,
        180,
        120.00,
        org,
        LocalDate.of(2025, 9, 5),
        "Ana Beatriz");
        imob.getLocais().get(3).alocarParaEvento(show);
        org.adicionaEvento(show);

        System.out.println("quer comprar ingresso e comparar clientes ou filtrar eventos? (V/F)");
        String oper = s.nextLine();
        Evento event;
        String ticket, answ;
        switch (oper){
            case ("V"):
                while (true){
                    System.out.println("Qual cliente quer comprar? (1/2)");
                    int client = Integer.parseInt(s.nextLine());
                    if (client != 1 && client != 2){
                        System.err.println("Cliente inválido!");
                        System.exit(1);
                    }
                    System.out.println("Qual evento? (escolha um número)");
                    org.showEventos();
                    int ind = Integer.parseInt(s.nextLine());
                    if (ind > org.getEventos().size() || ind <= 0){
                        System.err.println("Evento Inválido!");
                        System.exit(1);
                    }
                    event = org.getEventos().get(ind - 1);
                    System.out.println("Inteira, meia ou VIP? (I/M/V)");
                    ticket = s.nextLine();
                    switch (ticket) {
                        case ("I"):
                            try {
                                event.venderIngresso(client == 2 ? cliente_2 : cliente_1, new IngressoInteira(event, event.getPrecoIngresso(), true));
                            } catch (IngressoEsgotadoException e) {
                                System.err.println(e.getMessage());
                                System.exit(1);
                            }
                            break;
    
                        case ("M"):
                            try {
                                event.venderIngresso(client == 2 ? cliente_2 : cliente_1, new IngressoMeia(event, event.getPrecoIngresso(), true));
                            } catch (IngressoEsgotadoException e) {
                                System.err.println(e.getMessage());
                                System.exit(1);
                            }
                            break;
    
                        case ("V"):
                            try {
                                event.venderIngresso(client == 2 ? cliente_2 : cliente_1, new IngressoVip(event, event.getPrecoIngresso(), false));
                            } catch (IngressoEsgotadoException e) {
                                System.err.println(e.getMessage());
                                System.exit(1);
                            }
                            break;
    
                        default:
                            System.out.println("Tipo de ingresso inválido! Tente novamente.");
                    }
                    System.out.println("Quer comprar mais ingressos? (s/n)");
                    answ = s.nextLine();
                    if (answ.equals("n")){
                        if (cliente_1.compareTo(cliente_2)){
                            System.out.println("O cliente 1 tem os mesmos ingressos do cliente 2!");
                        } else {
                            System.out.println("O cliente 1 não tem os mesmos ingressos do cliente 2!");
                        }
                        break;
                    }
                }
                cliente_1.showIngressos();
                cliente_2.showIngressos();
                break;

            case ("F"):
                boolean and_filter = false;
                System.out.println("Usar AndFilter? (s/n)");
                answ = s.nextLine();
                if (answ.equals("s")){
                    and_filter = true;
                }
                ArrayList<Filter<?>> filters = new ArrayList<Filter<?>>();
                Filter<?> filtro;
                System.out.println("Que tipo de filtragem?");
                String type = s.nextLine();
                ArrayList<Evento> result = new ArrayList<Evento>();
                while (true) {
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
                
            default:
                System.err.println("Input inválido! Tente novamente");
                System.exit(1);
        }
        s.close();
    }
}
