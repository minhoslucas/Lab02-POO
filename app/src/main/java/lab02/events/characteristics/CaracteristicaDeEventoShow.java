package lab02.events.characteristics;

public class CaracteristicaDeEventoShow extends CaracteristicaDeEvento{

    private String artista;

    public CaracteristicaDeEventoShow(String artista){
        this.artista = artista;
    }
    
    public String getArtista(){
        return this.artista;
    }

    public String descricao(){
        return "Artista: " + this.artista;
    }
}