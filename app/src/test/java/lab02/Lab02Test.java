package lab02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab02.client.Cliente;
import lab02.events.Evento;
import lab02.events.EventoShow;
import lab02.events.ImobiliariaDeEventos;
import lab02.events.Local;
import lab02.events.Organizadora;
import lab02.exceptions.CancelamentoNaoPermitidoException;
import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.IngressoEsgotadoException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.notifiable.Email;
import lab02.tickets.IngressoInteira;
import lab02.tickets.IngressoMeia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class Lab02Test{
    private PrintStream original;
    private ByteArrayOutputStream saida = new ByteArrayOutputStream();

    @BeforeEach
    public void fixOutput(){
        System.setOut(new PrintStream(this.saida));
    }

    @AfterEach
    public void restoreOutput(){
        System.setOut(this.original);
    }

    @Test
    public void testClientNotifiable(){
        

        Cliente client = new Cliente("Lucas Beserra", new Email("lucas@gmail.com"));
        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 1500, imob));
        imob.adicionarLocal(new Local("Arena", 2000, imob));
        imob.adicionarLocal(new Local("Balada", 1000, imob));
        Evento event_1, event_2;
        event_1 = org.criaEvento("Show 1", null, 1000, 199.90, org, LocalDate.of(2025, 5, 14), "Ana Frango Eletrico");
        event_2 = org.criaEvento("Show 2", null, 1000, 99.90, org, LocalDate.of(2025, 5, 14), "Deftones");
        try{
            imob.getLocais().get(0).alocarParaEvento(event_1);
            imob.getLocais().get(1).alocarParaEvento(event_2);
            event_1.venderIngresso(client, new IngressoInteira(event_1, event_1.getPrecoIngresso(), true));
            event_2.venderIngresso(client, new IngressoInteira(event_2, event_2.getPrecoIngresso(), true));
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException | IngressoEsgotadoException e){
            System.exit(1);
        }
        
        client.getEmail().displayNotification();
        assertEquals("Ingresso Vendido com Sucesso!\r\nIngresso Vendido com Sucesso!\r\n" +
                    "E-mail: Novo evento comprado: Show 1\r\nE-mail: Novo evento comprado: Show 2\r\n", saida.toString());
    }

    @Test
    public void testClientComparable(){
        Cliente client_1 = new Cliente("Lucas", new Email("lucas@gmail.com"));
        Cliente client_2 = new Cliente("Pedro", new Email("pedro@gmail.com"));

        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 1500, imob));
        EventoShow event_1 = org.criaEvento("Show 1", null, 1000, 199.90, org, LocalDate.of(2025, 5, 14), "Ana Frango Eletrico");
        try{
            imob.getLocais().get(0).alocarParaEvento(event_1);
            event_1.venderIngresso(client_1, new IngressoInteira(event_1, event_1.getPrecoIngresso(), true));
            event_1.venderIngresso(client_2, new IngressoInteira(event_1, event_1.getPrecoIngresso(), true));
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException | IngressoEsgotadoException e){
            System.exit(1);
        }

        boolean result =  client_1.compareTo(client_2);
        assertEquals(true, result);
    }

    @Test
    public void testNewStructure(){
        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 1500, imob));
        EventoShow event_1 = org.criaEvento("Show 1", null, 1000, 199.90, org, LocalDate.of(2025, 5, 14), "Ana Frango Eletrico");
        try{
            imob.getLocais().get(0).alocarParaEvento(event_1);
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException e){
            System.exit(1);
        }

        assertEquals("Ana Frango Eletrico", event_1.getCaracteristicas().getArtista());
    }

    @Test
    public void testIngressoEsgotadoException(){
        Cliente client = new Cliente("Lucas", new Email("lucas@gmail.com"));

        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 1, imob));
        EventoShow event_1 = org.criaEvento("Show 1", null, 1, 199.90, org, LocalDate.of(2025, 5, 14), "Ana Frango Eletrico");
        try{
            imob.getLocais().get(0).alocarParaEvento(event_1);
            event_1.venderIngresso(client, new IngressoInteira(event_1, event_1.getPrecoIngresso(), true));
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException | IngressoEsgotadoException e){
            System.exit(1);
        } 

            Exception exception = assertThrows(IngressoEsgotadoException.class, () -> event_1.venderIngresso(client, new IngressoMeia(event_1, event_1.getPrecoIngresso(), false)));
            assertEquals("EVENTO LOTADO", exception.getMessage());
    }

    @Test
    public void testCancelamentoNaoPermitidoException(){
        Cliente client = new Cliente("Lucas", new Email("lucas@gmail.com"));

        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 1, imob));
        EventoShow event_1 = org.criaEvento("Show 1", null, 1, 199.90, org, LocalDate.of(2025, 5, 14), "Ana Frango Eletrico");
        try{
            imob.getLocais().get(0).alocarParaEvento(event_1);
            event_1.venderIngresso(client, new IngressoInteira(event_1, event_1.getPrecoIngresso(), false));
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException | IngressoEsgotadoException e){
            System.exit(1);
        } 

        Exception exception = assertThrows(CancelamentoNaoPermitidoException.class, () -> client.cancelarIngresso(client.getIngressos().get(0)));
        assertEquals("O ingresso não pode ser cancelado", exception.getMessage());
    }
}