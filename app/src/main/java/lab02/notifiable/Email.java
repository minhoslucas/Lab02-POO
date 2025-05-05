package lab02.notifiable;

import java.util.ArrayList;

/**
 * Implementação concreta da interface Notifiable que envia notificações por e-mail.
 * 
 * @author Lucas Beserra - 281815
 * 
 * Comentários feitos por IA e corrigidos posteriormente
 */
public class Email implements Notifiable {

    private String email;
    private ArrayList<String> message_list;

    /**
     * Construtor da classe Email
     * @param email O endereço de e-mail que receberá as notificações
     */
    public Email(String email) {
        this.email = email;
        this.message_list = new ArrayList<String>();
    }

    /**
     * Retorna o endereço de e-mail
     * @return O endereço de e-mail cadastrado
     */
    public String getEmailText() {
        return this.email;
    }

    /**
     * Adiciona uma nova mensagem à lista de notificações
     * @param message A mensagem a ser adicionada
     */
    public void addNotification(String message) {
        this.message_list.add(message);
    }

    /**
     * Exibe todas as notificações pendentes e limpa a lista
     */
    @Override
    public void displayNotification() {
        if (message_list.size() == 0) {
            System.out.println("E-mail Limpo!");
            return;
        }
        for (String message : message_list) {
            System.out.println("E-mail: " + message);
        }
        message_list.clear();
    }
}