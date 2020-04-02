package ProductWindow;

public class BaseComponent {  //Klasse som lagrer basisinformasjonen til komponenten

    private String type;
    private String id;
    private String name;
    private double price;

    public BaseComponent(String id, String name, double price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public String getId() {
        return id;
    }
}
