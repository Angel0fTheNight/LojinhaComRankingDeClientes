package app.view;

import app.controller.DataController;
import app.model.Cliente;
import app.model.Produto;
import app.model.Compra;

import javax.swing.*;
import java.awt.*;

public class CompraView extends JFrame {
    private DataController data;

    public CompraView(DataController data) {
        this.data = data;
        setTitle("Registrar Compra");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JComboBox<String> clienteBox = new JComboBox<>();
        JComboBox<String> produtoBox = new JComboBox<>();
        JTextField quantidadeField = new JTextField();
        JButton registrarBtn = new JButton("Registrar");

        for (Cliente c : data.getClientes()) {
            clienteBox.addItem(c.getEmail());
        }

        for (Produto p : data.getProdutos()) {
            produtoBox.addItem(p.getNome());
        }

        add(new JLabel("Cliente:"));
        add(clienteBox);
        add(new JLabel("Produto:"));
        add(produtoBox);
        add(new JLabel("Quantidade:"));
        add(quantidadeField);
        add(new JLabel(""));
        add(registrarBtn);

        registrarBtn.addActionListener(e -> {
            String email = (String) clienteBox.getSelectedItem();
            String produto = (String) produtoBox.getSelectedItem();
            try {
                int qtd = Integer.parseInt(quantidadeField.getText());
                if (qtd > 0) {
                    data.registrarCompra(new Compra(email, produto, qtd));
                    JOptionPane.showMessageDialog(this, "Compra registrada!");
                    quantidadeField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            }
        });

        setVisible(true);
    }
}
