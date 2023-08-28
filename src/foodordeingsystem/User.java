/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

/**
 *abstract class for User
 */
public abstract class User {
    private static String username;
    private String password;
    private String role;
    

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.role = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String roles) {
        this.role = role;
    }

  

  

    
    
    
     
}
