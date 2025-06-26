package app.view;

import app.controller.DataController;
import app.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class RankingView extends JFrame {
    public RankingView(DataController data) {
        setTitle("Ranking ABC de Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Nome", "Email", "Total Gasto", "Classe"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        List<Cliente> clientes = data.getClientes();
        clientes.sort(Comparator.comparingDouble(Cliente::getTotalGasto).reversed());

        double total = clientes.stream().mapToDouble(Cliente::getTotalGasto).sum();
        double acumulado = 0;

        for (Cliente c : clientes) {
            double gasto = c.getTotalGasto();
            acumulado += gasto;
            double perc = acumulado / total;
            String classe = perc <= 0.8 ? "A" : perc <= 0.95 ? "B" : "C";
            model.addRow(new Object[]{c.getNome(), c.getEmail(), String.format("R$ %.2f", gasto), classe});
        }

        setVisible(true);
    }
}
