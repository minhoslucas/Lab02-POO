package lab02.notifiable;

import java.util.ArrayList;

public class Email implements Notifiable{

    private String email;
    private ArrayList<String> message_list;

    public Email(String email){
        this.email = email;
        this.message_list = new ArrayList<String>();
    }

    public String getEmailText(){
        return this.email;
    }

    public void addNotification(String message){
        this.message_list.add(message);
    }

    public void displayNotification(){
        if (message_list.size() == 0){
            System.out.println("E-mail Limpo!");
            return;
        }
        for (String message : message_list) {
            System.out.println("E-mail: " + message);
        }
    }
}