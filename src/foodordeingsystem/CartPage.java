/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CartPage extends JFrame {

    private JTable cartTable;
    private DefaultTableModel cartTableModel;

    public CartPage(ArrayList<String> cartItems) {
        setTitle("Cart Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("Food Name");
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);

        for (String item : cartItems) {
            cartTableModel.addRow(new Object[]{item});
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(cartScrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
    
