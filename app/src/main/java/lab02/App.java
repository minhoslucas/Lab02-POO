/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import lab02.client.Cliente;
import lab02.events.*;
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

        System.out.println("quer comprar ingresso? (s/n)");
        String buy = s.nextLine();
        int ind;
        Evento event = null;
        String ticket;
        switch (buy){
            case ("s"):
                System.out.println("Qual cliente quer comprar? (1/2)");
                String client = s.nextLine();
                switch (client) {
                    case "1":
                        System.out.println("Qual evento? (escolha um número)");
                        org.showEventos();
                        ind = s.nextInt();
                        if (ind > org.getEventos().size()){
                            System.out.println("ERRO");
                            System.exit(1);
                        }
                        event = org.getEventos().get(ind);
                        break;

                    case "2":
                        System.out.println("Qual evento? (escolha um número)");
                        org.showEventos();
                        ind = s.nextInt();
                        if (ind > org.getEventos().size()){
                            System.out.println("ERRO");
                            System.exit(1);
                        }
                        event = org.getEventos().get(ind);
                        break;             
                    default:
                        System.out.println("Cliente inválido, tente novamente");;
                }
                System.out.println("Inteira, meia ou VIP? (I/M/V)");
                ticket = s.nextLine();
                switch (ticket) {
                    case ("I"):
                        try {
                            event.venderIngresso(client.equals("2") ? cliente_2 : cliente_1, new IngressoInteira(event, event.getPrecoIngresso(), false));
                        } catch (IngressoEsgotadoException e) {
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    case ("M"):
                        try {
                            event.venderIngresso(client.equals("2") ? cliente_2 : cliente_1, new IngressoMeia(event, event.getPrecoIngresso(), true));
                        } catch (IngressoEsgotadoException e) {
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    case ("V"):
                        try {
                            event.venderIngresso(client.equals("2") ? cliente_2 : cliente_1, new IngressoVip(event, event.getPrecoIngresso() * 2, false));
                        } catch (IngressoEsgotadoException e) {
                            System.err.println("Evento esgotado!");
                            System.exit(1);
                        }
                        break;

                    default:
                        System.out.println("Tipo de ingresso inválido! Tente novamente.");
                }
            case ("n"):
                //not slay
            default:
                System.out.println("Input inválido! Tente novamente");
                buy = s.nextLine();
        }
    }
}
