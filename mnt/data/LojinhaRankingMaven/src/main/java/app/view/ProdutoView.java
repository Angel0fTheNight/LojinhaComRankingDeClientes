package app.view;

import app.controller.DataController;
import app.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class ProdutoView extends JFrame {
    private DataController data;
    private JTable tabela;
    private DefaultTableModel model;

    public ProdutoView(DataController data) {
        this.data = data;
        setTitle("Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário no topo
        JPanel formPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JButton addBtn = new JButton("Adicionar");
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Preço:"));
        formPanel.add(precoField);
        formPanel.add(addBtn);
        add(formPanel, BorderLayout.NORTH);

        // Tabela no centro
        String[] colunas = {"Nome", "Preço"};
        model = new DefaultTableModel(colunas, 0);
        tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        // Painel de botões de ação na parte inferior
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editBtn = new JButton("Editar");
        JButton deleteBtn = new JButton("Excluir");
        actionPanel.add(editBtn);
        actionPanel.add(deleteBtn);
        add(actionPanel, BorderLayout.SOUTH);

        atualizarTabela();

        // Ação para adicionar produto
        addBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            try {
                double preco = Double.parseDouble(precoField.getText());
                if (!nome.isEmpty()) {
                    data.adicionarProduto(new Produto(nome, preco));
                    atualizarTabela();
                    nomeField.setText("");
                    precoField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para editar produto
        editBtn.addActionListener(e -> editarProduto());

        // Ação para excluir produto
        deleteBtn.addActionListener(e -> excluirProduto());

        setVisible(true);
    }

    private void editarProduto() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nomeOriginal = (String) model.getValueAt(selectedRow, 0);
        Optional<Produto> produtoOpt = data.getProdutos().stream().filter(p -> p.getNome().equals(nomeOriginal)).findFirst();

        if (produtoOpt.isPresent()) {
            Produto produtoOriginal = produtoOpt.get();

            JTextField nomeField = new JTextField(produtoOriginal.getNome());
            JTextField precoField = new JTextField(String.valueOf(produtoOriginal.getPreco()));

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Preço:"));
            panel.add(precoField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Editar Produto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String novoNome = nomeField.getText();
                try {
                    double novoPreco = Double.parseDouble(precoField.getText());
                    if (!novoNome.isEmpty()) {
                        data.atualizarProduto(produtoOriginal, novoNome, novoPreco);
                        atualizarTabela();
                    } else {
                        JOptionPane.showMessageDialog(this, "Nome não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void excluirProduto() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nome = (String) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto " + nome + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            data.getProdutos().stream()
                    .filter(p -> p.getNome().equals(nome))
                    .findFirst()
                    .ifPresent(produto -> {
                        data.excluirProduto(produto);
                        atualizarTabela();
                    });
        }
    }

    private void atualizarTabela() {
        model.setRowCount(0);
        for (Produto p : data.getProdutos()) {
            model.addRow(new Object[]{p.getNome(), String.format("R$ %.2f", p.getPreco())});
        }
    }
}