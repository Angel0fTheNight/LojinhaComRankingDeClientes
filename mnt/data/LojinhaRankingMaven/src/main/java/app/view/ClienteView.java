package app.view;

import app.controller.DataController;
import app.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClienteView extends JFrame {
    private DataController data;

    public ClienteView(DataController data) {
        this.data = data;
        setTitle("Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(1, 4, 5, 5));
        JTextField nomeField = new JTextField();
        JTextField emailField = new JTextField();
        JButton addBtn = new JButton("Adicionar");

        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(addBtn);
        add(form, BorderLayout.NORTH);

        String[] colunas = {"Nome", "Email", "Total Gasto"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela(model);

        addBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            if (!nome.isEmpty() && !email.isEmpty()) {
                data.adicionarCliente(new Cliente(nome, email));
                atualizarTabela(model);
                nomeField.setText("");
                emailField.setText("");
            }
        });

        setVisible(true);
    }

    private void atualizarTabela(DefaultTableModel model) {
        model.setRowCount(0);
        for (Cliente c : data.getClientes()) {
            model.addRow(new Object[]{c.getNome(), c.getEmail(), String.format("R$ %.2f", c.getTotalGasto())});
        }
    }
}
