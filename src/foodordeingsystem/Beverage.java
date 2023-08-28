/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

// Beverage is subclass of Food
public class Beverage extends Food{
  


    private String type = "Beverage";
    

    Beverage(String ID, String name, double price, String type){
  super(type, name, price);
        this.type =type;
    }
    
    
    
public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}


   
    
}

    
