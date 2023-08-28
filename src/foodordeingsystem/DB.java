/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodordeingsystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.*;


// Database
public class DB {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/food_ordering_system";
    static String dbUsername = "root";
    static String dbPassword = "";

    static void connect() {

        try {
            Connection connection = java.sql.DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            System.out.println("Connected to the database!");

            // Your database operations here
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    // AuthLogin

    static boolean AuthLogin(String username, String password) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // If a row is returned, credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    // register and record in databse

    static void register(User user) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "INSERT INTO user (user_name, password, role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRoles());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Registration successful!");

                Login loginPage = new Login();
                loginPage.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Registration failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
        }

    }

    // get role before after sign in
    static String getRoleByUsername(String username) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "SELECT role FROM user WHERE user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if username not found or an error occurred
    }
    
    
//add Food in cart
    
    static void addFood(Food food, String selectedFoodType) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "INSERT INTO food (id, name, price, type) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, food.getID());
            statement.setString(2, food.getName());
            statement.setDouble(3, food.getPrice());
            statement.setString(4, selectedFoodType);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Add Food in Menu successful!");

            } else {
                JOptionPane.showMessageDialog(null, "Registration failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
        }

    }
    
    // getAllUser from DataBase

    static void getAllUser() {
        String query = "SELECT user_name, password, role FROM user";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

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
            frame.setLayout(new BorderLayout());

            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
            
            JButton goBackButton = new JButton("Go Back");
            goBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    ManagementMenu managementPage = new ManagementMenu();
                    
                    managementPage.setVisible(true);
                }
            });
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(goBackButton);

            frame.add(tablePanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setSize(800, 600);
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void getAllFood() {
        String query = "SELECT * FROM food";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

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
