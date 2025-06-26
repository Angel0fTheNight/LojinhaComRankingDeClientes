package app.view;

import app.controller.DataController;
import app.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProdutoView extends JFrame {
    private DataController data;

    public ProdutoView(DataController data) {
        this.data = data;
        setTitle("Produtos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(1, 3, 5, 5));
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JButton addBtn = new JButton("Adicionar");

        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("Preço:"));
        form.add(precoField);
        form.add(addBtn);
        add(form, BorderLayout.NORTH);

        String[] colunas = {"Nome", "Preço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela(model);

        addBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            try {
                double preco = Double.parseDouble(precoField.getText());
                if (!nome.isEmpty()) {
                    data.adicionarProduto(new Produto(nome, preco));
                    atualizarTabela(model);
                    nomeField.setText("");
                    precoField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço inválido.");
            }
        });

        setVisible(true);
    }

    private void atualizarTabela(DefaultTableModel model) {
        model.setRowCount(0);
        for (Produto p : data.getProdutos()) {
            model.addRow(new Object[]{p.getNome(), String.format("R$ %.2f", p.getPreco())});
        }
    }
}
