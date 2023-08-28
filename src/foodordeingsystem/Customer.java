/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

// Customer is subclass of User
public class Customer extends User{
    private String role;  

    public Customer(String role, String username, String password) {
       super(username, password, role);
        this.role = role;
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
     
   
}
