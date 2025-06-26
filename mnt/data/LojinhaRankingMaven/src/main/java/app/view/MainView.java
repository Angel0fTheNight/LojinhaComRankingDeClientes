package app.view;

import app.controller.DataController;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private DataController data;

    public MainView(DataController data) {
        this.data = data;
        setTitle("Lojinha - Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 15, 15));

        JButton btnClientes = new JButton("Clientes");
        JButton btnProdutos = new JButton("Produtos");
        JButton btnCompras = new JButton("Registrar Compra");
        JButton btnRanking = new JButton("Ranking ABC");

        btnClientes.addActionListener(e -> new ClienteView(data));
        btnProdutos.addActionListener(e -> new ProdutoView(data));
        btnCompras.addActionListener(e -> new CompraView(data));
        btnRanking.addActionListener(e -> new RankingView(data));

        add(btnClientes);
        add(btnProdutos);
        add(btnCompras);
        add(btnRanking);

        setVisible(true);
    }
}
