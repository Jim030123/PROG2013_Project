/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

/**
 *
 * @author qiqi0
 */
public class Management extends User{
    private String role;  

    public Management( String username, String password,String role) {
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
