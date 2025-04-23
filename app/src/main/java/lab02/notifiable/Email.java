package lab02.notifiable;

import org.checkerframework.checker.units.qual.degrees;

public class Email implements Notifiable{

    private String email;

    public Email(String email){
        this.email = email;
    }

    public String displayNotification(){
        return "EMAIL ATUALIZADO";
    }
}