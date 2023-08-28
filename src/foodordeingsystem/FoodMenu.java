package foodordeingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class FoodMenu extends JFrame {

    private JTable foodTable;
    private DefaultTableModel tableModel;
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private JButton orderButton;
    private JButton deleteButton;
    private JButton paymentButton;
    private JLabel totalLabel;

    private HashMap<String, Cart> cartItems = new HashMap<>();
    private double total = 0.0;

    public FoodMenu() {
        setTitle("Food Ordering System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID");
        tableModel.addColumn("Food Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Type");

        foodTable = new JTable(tableModel);
        foodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane foodScrollPane = new JScrollPane(foodTable);

        cartTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cartTableModel.addColumn("Food Name");
        cartTableModel.addColumn("Price");
        cartTableModel.addColumn("Quantity");
        cartTableModel.addColumn("Subtotal");
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);

        // addToCart Button
        orderButton = new JButton("Add to Cart");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = foodTable.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) foodTable.getValueAt(selectedRow, 1);
                    String priceStr = (String) foodTable.getValueAt(selectedRow, 2);
                    double price = Double.parseDouble(priceStr);
// if  button is clicked, increment the quantity,
                    Cart cartItem = cartItems.get(name);
                    if (cartItem == null) {
                        cartItem = new Cart(name, price, 1);
                        cartItems.put(name, cartItem);
                    } else {
                        cartItem.incrementQuantity();
                    }

                    updateCartTable();
                    total += price;
                    updateTotalLabel();

                    JOptionPane.showMessageDialog(FoodMenu.this, name + " added to cart.");
                } else {
                    JOptionPane.showMessageDialog(FoodMenu.this, "Please select a food item from the list.");
                }
            }
        });

        deleteButton = new JButton("Delete from Cart");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cartTable.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) cartTable.getValueAt(selectedRow, 0);
                    double price = (Double) cartTable.getValueAt(selectedRow, 1);
                    int quantity = (Integer) cartTable.getValueAt(selectedRow, 2);

                    Cart cartItem = cartItems.get(name);
                    if (cartItem != null) {
                        if (quantity > 1) {
                            cartItem.decrementQuantity();
                        } else {
                            cartItems.remove(name);
                        }
                        updateCartTable();
                        total -= price;
                        updateTotalLabel();
                    }
                } else {
                    JOptionPane.showMessageDialog(FoodMenu.this, "Please select an item to delete from the cart.");
                }
            }
        });
// Makepayment
        paymentButton = new JButton("Make Payment");
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] paymentMethods = {"TNG", "Cash", "Credit Card"};
                String selectedMethod = (String) JOptionPane.showInputDialog(
                        FoodMenu.this,
                        "Select a payment method:",
                        "Payment Method",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        paymentMethods,
                        paymentMethods[0]);

                if (selectedMethod != null) {
                    navigateToLoginScreen();
                    sendOrderToDatabase(selectedMethod);
                }
            }
        });
// Total Label GUI
        totalLabel = new JLabel("Total: RM " + total);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(orderButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(paymentButton);

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.add(totalLabel, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new GridLayout(1, 2));
        tablePanel.add(foodScrollPane);
        tablePanel.add(cartScrollPane);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(totalPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);

        populateTable();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTotalLabel() {
        totalLabel.setText("Total: RM " + String.format("%.2f", total));
    }

    //get item from Cart Table
    private void updateCartTable() {
        cartTableModel.setRowCount(0);
        for (Cart item : cartItems.values()) {
            double totalPerItem = item.getPrice() * item.getQuantity();
            cartTableModel.addRow(new Object[]{item.getName(), item.getPrice(), item.getQuantity(), totalPerItem});
        }
    }

    private void navigateToLoginScreen() {
        dispose(); // Close the FoodMenu window

        // Open a new instance of the Login screen
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
// Send order to Database
    private void sendOrderToDatabase(String paymentMethod) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system", "root", "")) {
            String insertOrderQuery = "INSERT INTO orders (payment_method, total, payment_method) VALUES (?, ?,?,?)";

            try (PreparedStatement insertStatement = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, paymentMethod);
                insertStatement.setDouble(2, total);

                int affectedRows = insertStatement.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                        for (Cart item : cartItems.values()) {
                            String insertOrderItemQuery = "INSERT INTO orders(order_id, food_name, price, quantity) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement itemInsertStatement = connection.prepareStatement(insertOrderItemQuery)) {
                                itemInsertStatement.setInt(1, orderId);
                                itemInsertStatement.setString(2, item.getName());
                                itemInsertStatement.setDouble(3, item.getPrice());
                                itemInsertStatement.setInt(4, item.getQuantity());
                                itemInsertStatement.executeUpdate();

                            }
                        }

                        JOptionPane.showMessageDialog(FoodMenu.this, "Order sent to database with ID: " + orderId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// perform CRUD

    private void populateTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system", "root", "")) {
            String query = "SELECT * FROM food";

            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String price = resultSet.getString("price");
                    String foodType = resultSet.getString("type");

                    tableModel.addRow(new Object[]{id, name, price, foodType});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        SwingUtilities.invokeLater(() -> new FoodMenu());
    }

}
