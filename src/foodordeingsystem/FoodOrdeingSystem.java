/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package foodordeingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author qiqi0
 */
public class FoodOrdeingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        DB.connect();
        Login loginPage = new Login();
       loginPage.setVisible(true); 
    }
    
}
