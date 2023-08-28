package foodordeingsystem;

// Snack is subclass of Food
public class Snack extends Food {
    private String type = "Snack";

    Snack(String ID, String name, double price, String type) {
        super(ID, name, price);

        type= this.type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
