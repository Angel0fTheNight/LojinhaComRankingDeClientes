package app.view;

import app.controller.DataController;
import app.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class ClienteView extends JFrame {
    private DataController data;
    private JTable tabela;
    private DefaultTableModel model;

    public ClienteView(DataController data) {
        this.data = data;
        setTitle("Clientes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário no topo
        JPanel formPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JTextField nomeField = new JTextField();
        JTextField emailField = new JTextField();
        JButton addBtn = new JButton("Adicionar");
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(addBtn);
        add(formPanel, BorderLayout.NORTH);

        // Tabela no centro
        String[] colunas = {"Nome", "Email", "Total Gasto"};
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

        // Ação para adicionar cliente
        addBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            if (!nome.isEmpty() && !email.isEmpty()) {
                data.adicionarCliente(new Cliente(nome, email));
                atualizarTabela();
                nomeField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Nome e Email são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para editar cliente
        editBtn.addActionListener(e -> editarCliente());

        // Ação para excluir cliente
        deleteBtn.addActionListener(e -> excluirCliente());

        setVisible(true);
    }

    private void editarCliente() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String emailOriginal = (String) model.getValueAt(selectedRow, 1);
        Optional<Cliente> clienteOpt = data.getClientes().stream().filter(c -> c.getEmail().equals(emailOriginal)).findFirst();

        if (clienteOpt.isPresent()) {
            Cliente clienteOriginal = clienteOpt.get();

            JTextField nomeField = new JTextField(clienteOriginal.getNome());
            JTextField emailField = new JTextField(clienteOriginal.getEmail());

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Editar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String novoNome = nomeField.getText();
                String novoEmail = emailField.getText();
                if (!novoNome.isEmpty() && !novoEmail.isEmpty()) {
                    data.atualizarCliente(clienteOriginal, novoNome, novoEmail);
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Nome e Email não podem ser vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void excluirCliente() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String email = (String) model.getValueAt(selectedRow, 1);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o cliente " + email + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            data.getClientes().stream()
                    .filter(c -> c.getEmail().equals(email))
                    .findFirst()
                    .ifPresent(cliente -> {
                        data.excluirCliente(cliente);
                        atualizarTabela();
                    });
        }
    }

    private void atualizarTabela() {
        model.setRowCount(0);
        for (Cliente c : data.getClientes()) {
            model.addRow(new Object[]{c.getNome(), c.getEmail(), String.format("R$ %.2f", c.getTotalGasto())});
        }
    }
}