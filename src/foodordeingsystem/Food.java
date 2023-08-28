/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

/**
superClass and abstract for Food class
 */
public abstract class Food {
     private String ID;
    private String name;
    private double price;


    Food (String ID, String name, double price){
         this.ID=ID;
        this.name=name;
         this.price=price;
    }


   public String getID() {
       return ID;
   }

   public void setID(String ID) {
       this.ID = ID;
   }
   public String getName() {
       return name;
   }
   public double getPrice() {
       return price;
   }
   public void setName(String name) {
       this.name = name;
   }

   public void setPrice(double price) {
       this.price = price;
   }
}
