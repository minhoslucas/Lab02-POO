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

        //criação de eventos
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
        int ind;
        Evento event;
        String ticket;
        switch (oper){
            case ("V"):
                System.out.println("Qual cliente quer comprar? (1/2)");
                int client = s.nextInt();
                if (client != 1 && client != 2){
                    System.err.println("Cliente inválido!");
                    System.exit(1);
                }
                System.out.println("Qual evento? (escolha um número)");
                org.showEventos();
                ind = s.nextInt();
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
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    case ("M"):
                        try {
                            event.venderIngresso(client == 2 ? cliente_2 : cliente_1, new IngressoMeia(event, event.getPrecoIngresso(), true));
                        } catch (IngressoEsgotadoException e) {
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    case ("V"):
                        try {
                            event.venderIngresso(client == 2 ? cliente_2 : cliente_1, new IngressoVip(event, event.getPrecoIngresso(), false));
                        } catch (IngressoEsgotadoException e) {
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    default:
                        System.out.println("Tipo de ingresso inválido! Tente novamente.");
                }
                System.out.println("Quer comprar mais ingressos? (s/n)");
                String answ = s.nextLine();
                if (answ.equals("n")){
                    if (cliente_1.compareTo(cliente_2)){
                        System.out.println("O cliente 1 tem os mesmos ingressos do cliente 2!");
                    } else {
                        System.out.println("O cliente 1 não tem os mesmos ingressos do cliente 2!");
                    }
                    break;
                }

            case ("F"):
                Filter<?> filtro;
                System.out.println("Que tipo de filtragem?");
                String type = s.nextLine();
                ArrayList<Evento> result;
                switch (type) {
                    case "Tipo":
                    
                        System.out.println("Qual tipo de evento?");
                        String event_type = s.nextLine();
                        switch (event_type) {
                            case "Festival":
                                filtro = new EventoPorTipoFilter(EventoFestival.class);
                                result = org.buscarEventos(filtro);
                                break;
                            case "Jogo":
                                filtro = new EventoPorTipoFilter(EventoJogo.class);
                                result = org.buscarEventos(filtro);
                                break;
                            case "Musica":
                                filtro = new EventoPorTipoFilter(EventoMusicaAoVivo.class);
                                result = org.buscarEventos(filtro);
                                break;
                            case "Show":
                                filtro = new EventoPorTipoFilter(EventoShow.class);
                                result = org.buscarEventos(filtro);
                                break;
                            default:
                                System.out.println("Tipo de evento inválido! Tente novamente");
                        }
                    
                    case "Org":
                        break;

                    case "Nome":
                        break;

                    case "Local":
                        break;

                    case "Data":
                        break;
                
                    default:
                        break;
                }
            default:
                System.out.println("Input inválido! Tente novamente");
                oper = s.nextLine();
        }
    }
}
