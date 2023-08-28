/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

// Meal is subclass of Food
public class Meal extends Food{
     private String type = "Meal";

    Meal(String ID, String name, double price, String type) {
          super(ID, name, price);
        this.type =type;
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
