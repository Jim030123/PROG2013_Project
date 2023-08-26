/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author qiqi0
 */


public class SQLTableExample extends JFrame {

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            displayTable();
        });
    }

    public static void displayTable() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/food_ordering_system";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT user_name, password, role FROM user";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            int rowCount = 0; // Counter for the number of rows

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }

            Object[][] data = new Object[0][columnCount];
            while (resultSet.next()) {
                rowCount++;
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                data = Arrays.copyOf(data, data.length + 1);
                data[data.length - 1] = rowData;
            }

            System.out.println("Number of rows returned: " + rowCount); // Display in console

            TableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            JFrame frame = new JFrame("User Data Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.setSize(800, 600);
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
