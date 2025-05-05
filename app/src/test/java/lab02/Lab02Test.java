/**
 * Classe de teste para o Lab02, contendo diversos casos de teste para verificar a funcionalidade
 * do sistema de gerenciamento de eventos, incluindo notificações de clientes, filtragem de eventos,
 * tratamento de exceções e outros aspectos.
 * 
 * Esta classe de teste utiliza JUnit 5 para testar diferentes aspectos do sistema:
 * - Comportamento de notificação do cliente ao comprar ingressos
 * - Comparação de clientes com base na compra de ingressos
 * - Estrutura do evento e suas características
 * - Tratamento de exceções para eventos esgotados e cancelamentos
 * - Funcionalidade de filtragem de eventos
 * - Filtragem combinada com lógica AND
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 * 
 * @author Lucas Beserra - 281815
 * @see Cliente
 * @see Evento
 * @see Organizadora
 * @see ImobiliariaDeEventos
 * @see Local
 * @see Filter
 */
package lab02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab02.client.Cliente;
import lab02.events.Evento;
import lab02.events.EventoJogo;
import lab02.events.EventoShow;
import lab02.events.ImobiliariaDeEventos;
import lab02.events.Local;
import lab02.events.Organizadora;
import lab02.exceptions.CancelamentoNaoPermitidoException;
import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.IngressoEsgotadoException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.filter.AndFilter;
import lab02.filter.EventoPorDataFilter;
import lab02.filter.EventoPorTipoFilter;
import lab02.filter.Filter;
import lab02.notifiable.Email;
import lab02.tickets.IngressoInteira;
import lab02.tickets.IngressoMeia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

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
        /* Testa o sistema de notificação por e-mail quando um cliente compra ingressos.
         * Verifica se as mensagens de confirmação são exibidas corretamente no console
         * quando um cliente compra ingressos para dois eventos diferentes.
         * O teste valida a saída esperada no console incluindo as mensagens de sucesso
         * e as notificações por e-mail. */
        
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
        /* Testa a comparação entre clientes com base na quantidade de ingressos comprados.
         * Verifica se o método compareTo() retorna true quando o primeiro cliente tem mais ingressos
         * que o segundo cliente. O teste configura dois clientes comprando ingressos para o mesmo evento
         * e depois compara quem tem mais ingressos. */
        
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
        /* Testa a nova estrutura de características dos eventos, verificando se as informações
         * específicas de cada tipo de evento (como o artista de um show) são armazenadas e
         * recuperadas corretamente. */
        
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
        /* Testa o lançamento da exceção IngressoEsgotadoException quando tenta vender
         * um ingresso para um evento que já está lotado. Verifica se a mensagem da exceção
         * está correta e se ela é lançada nas condições apropriadas. */
        
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
        /* Testa o lançamento da exceção CancelamentoNaoPermitidoException quando tenta
         * cancelar um ingresso que não permite cancelamento. Verifica se a mensagem da
         * exceção está correta e se ela é lançada nas condições apropriadas. */
        
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

    @Test
    public void testFilter(){
        /* Testa o sistema de filtragem de eventos, verificando se os filtros por tipo e por data
         * retornam corretamente os eventos correspondentes. O teste cria diversos eventos de
         * diferentes tipos e datas e verifica se a filtragem retorna a quantidade esperada
         * de resultados para cada caso. */
        
        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 500, imob));
        imob.adicionarLocal(new Local("Arena", 300, imob));
        imob.adicionarLocal(new Local("Teatro", 800, imob));
        imob.adicionarLocal(new Local("Ginásio", 1000, imob));
        imob.adicionarLocal(new Local("Auditório", 600, imob));
        imob.adicionarLocal(new Local("Estádio Municipal", 800, imob));
        imob.adicionarLocal(new Local("Arena Olímpica", 600, imob));
        imob.adicionarLocal(new Local("Ginásio Internacional", 1000, imob));

        EventoShow event_1 = org.criaEvento("Show 1", null, 500, 150.00, org, LocalDate.of(2025, 5, 14), "Band A");
        EventoShow event_2 = org.criaEvento("Show 2", null, 300, 120.00, org, LocalDate.of(2025, 5, 14), "Band B");
        EventoShow event_3 = org.criaEvento("Show 3", null, 800, 200.00, org, LocalDate.of(2025, 5, 14), "Band C");
        EventoShow event_4 = org.criaEvento("Show 4", null, 1000, 250.00, org, LocalDate.of(2025, 8, 5), "Band D");
        EventoShow event_5 = org.criaEvento("Show 5", null, 600, 180.00, org, LocalDate.of(2025, 9, 15), "Band E");
        EventoJogo jogo1 = org.criaEvento("Final do Campeonato", null, 800, 150.00, org, LocalDate.of(2025, 5, 14), new ArrayList<String>(), "Futebol");
        EventoJogo jogo2 = org.criaEvento("Semifinal do Campeonato", null, 600, 120.00, org, LocalDate.of(2025, 5, 14), new ArrayList<String>(), "Basquete");
        EventoJogo jogo3 = org.criaEvento("Amistoso Internacional", null, 1000, 200.00, org, LocalDate.of(2025, 8, 20), new ArrayList<String>(), "Vôlei");

        try {
            imob.getLocais().get(0).alocarParaEvento(event_1); // Estádio
            imob.getLocais().get(1).alocarParaEvento(event_2); // Arena
            imob.getLocais().get(2).alocarParaEvento(event_3); // Teatro
            imob.getLocais().get(3).alocarParaEvento(event_4); // Ginásio
            imob.getLocais().get(4).alocarParaEvento(event_5); // Auditório
            imob.getLocais().get(5).alocarParaEvento(jogo1);   // Estádio Municipal
            imob.getLocais().get(6).alocarParaEvento(jogo2);   // Arena Olímpica
            imob.getLocais().get(7).alocarParaEvento(jogo3);   // Ginásio Internacional
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException e) {
            System.exit(1);
        }
        org.adicionaEvento(event_1);
        org.adicionaEvento(event_2);
        org.adicionaEvento(event_3);
        org.adicionaEvento(event_4);
        org.adicionaEvento(event_5);
        org.adicionaEvento(jogo1);
        org.adicionaEvento(jogo2);
        org.adicionaEvento(jogo3);

        Filter<?> filtro;
        ArrayList<Evento> result;

        filtro = new EventoPorTipoFilter(EventoShow.class);
        result = org.buscarEventos(filtro);
        System.out.println("Resultado:");
        for (Evento e : result){
            e.descricao();
            System.out.println();
        }
        assertEquals(5, result.size());

        filtro = new EventoPorDataFilter(LocalDate.of(2025, 5, 14));
        result = org.buscarEventos(filtro);
        System.out.println("Resultado:");
        for (Evento e : result){
            e.descricao();
            System.out.println();
        }
        assertEquals(5, result.size());
    }

    @Test
    public void testAndFilter(){
        /* Testa a combinação de filtros usando a lógica AND, verificando se o sistema
         * retorna corretamente apenas os eventos que atendem a ambos os critérios de
         * filtragem simultaneamente (no caso, eventos do tipo Show na data específica). */
        
        Organizadora org = new Organizadora("Organizadora de Eventos", 123456, "Rua do Tiziu 192");
        ImobiliariaDeEventos imob = new ImobiliariaDeEventos("Imobiliária");
        imob.adicionarLocal(new Local("Estádio", 500, imob));
        imob.adicionarLocal(new Local("Arena", 300, imob));
        imob.adicionarLocal(new Local("Teatro", 800, imob));
        imob.adicionarLocal(new Local("Ginásio", 1000, imob));
        imob.adicionarLocal(new Local("Auditório", 600, imob));
        imob.adicionarLocal(new Local("Estádio Municipal", 800, imob));
        imob.adicionarLocal(new Local("Arena Olímpica", 600, imob));
        imob.adicionarLocal(new Local("Ginásio Internacional", 1000, imob));

        EventoShow event_1 = org.criaEvento("Show 1", null, 500, 150.00, org, LocalDate.of(2025, 5, 14), "Band A");
        EventoShow event_2 = org.criaEvento("Show 2", null, 300, 120.00, org, LocalDate.of(2025, 5, 14), "Band B");
        EventoShow event_3 = org.criaEvento("Show 3", null, 800, 200.00, org, LocalDate.of(2025, 5, 14), "Band C");
        EventoShow event_4 = org.criaEvento("Show 4", null, 1000, 250.00, org, LocalDate.of(2025, 8, 5), "Band D");
        EventoShow event_5 = org.criaEvento("Show 5", null, 600, 180.00, org, LocalDate.of(2025, 9, 15), "Band E");
        EventoJogo jogo1 = org.criaEvento("Final do Campeonato", null, 800, 150.00, org, LocalDate.of(2025, 5, 14), new ArrayList<String>(), "Futebol");
        EventoJogo jogo2 = org.criaEvento("Semifinal do Campeonato", null, 600, 120.00, org, LocalDate.of(2025, 5, 14), new ArrayList<String>(), "Basquete");
        EventoJogo jogo3 = org.criaEvento("Amistoso Internacional", null, 1000, 200.00, org, LocalDate.of(2025, 8, 20), new ArrayList<String>(), "Vôlei");

        try {
            imob.getLocais().get(0).alocarParaEvento(event_1); // Estádio
            imob.getLocais().get(1).alocarParaEvento(event_2); // Arena
            imob.getLocais().get(2).alocarParaEvento(event_3); // Teatro
            imob.getLocais().get(3).alocarParaEvento(event_4); // Ginásio
            imob.getLocais().get(4).alocarParaEvento(event_5); // Auditório
            imob.getLocais().get(5).alocarParaEvento(jogo1);   // Estádio Municipal
            imob.getLocais().get(6).alocarParaEvento(jogo2);   // Arena Olímpica
            imob.getLocais().get(7).alocarParaEvento(jogo3);   // Ginásio Internacional
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException e) {
            System.exit(1);
        }
        org.adicionaEvento(event_1);
        org.adicionaEvento(event_2);
        org.adicionaEvento(event_3);
        org.adicionaEvento(event_4);
        org.adicionaEvento(event_5);
        org.adicionaEvento(jogo1);
        org.adicionaEvento(jogo2);
        org.adicionaEvento(jogo3);

        Filter<?> filtro_1, filtro_2;
        ArrayList<Evento> result;

        filtro_1 = new EventoPorTipoFilter(EventoShow.class);
        filtro_2 = new EventoPorDataFilter(LocalDate.of(2025, 5, 14));

        result = new AndFilter(filtro_1, filtro_2).filter(org.getEventos());
        System.out.println("Resultado:");
        for (Evento e : result){
            e.descricao();
            System.out.println();
        }
        assertEquals(3, result.size());
    }
}