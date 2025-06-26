package app.view;

import app.controller.DataController;
import javax.swing.*;
// Precisamos desses imports para a janela de seleção de arquivo.
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

// Tela principal, serve como um hub para as outras funcionalidades.
public class MainView extends JFrame {
    private DataController data;

    public MainView(DataController data) {
        this.data = data;
        setTitle("Lojinha - Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        // Um grid layout simples pra organizar os botões.
        // Aumentei as linhas de 2 para 3 pra caber o novo botão.
        setLayout(new GridLayout(3, 2, 15, 15));

        // Botões para cada funcionalidade principal.
        JButton btnClientes = new JButton("Clientes");
        JButton btnProdutos = new JButton("Produtos");
        JButton btnCompras = new JButton("Registrar Compra");
        JButton btnRanking = new JButton("Ranking ABC");
        // Novo botão de exportação.
        JButton btnExportar = new JButton("Exportar para TXT");

        // Action listeners para abrir cada tela.
        // Usando lambdas pra deixar o código mais enxuto.
        btnClientes.addActionListener(e -> new ClienteView(data));
        btnProdutos.addActionListener(e -> new ProdutoView(data));
        btnCompras.addActionListener(e -> new CompraView(data));
        btnRanking.addActionListener(e -> new RankingView(data));
        // Ação para o novo botão.
        btnExportar.addActionListener(e -> exportarDados());

        // Adiciona os botões na tela.
        add(btnClientes);
        add(btnProdutos);
        add(btnCompras);
        add(btnRanking);
        add(btnExportar); // Não esquecer de adicionar o botão ao frame.

        setVisible(true);
    }

    // Abre uma janela para o usuário escolher onde salvar o arquivo.
    private void exportarDados() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar relatório TXT");
        // Filtro para mostrar apenas a opção de salvar como .txt, pra facilitar pro usuário.
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de Texto (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(this);

        // Se o usuário clicou em "Salvar" (e não em "Cancelar")...
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoParaSalvar = fileChooser.getSelectedFile();
            String caminho = arquivoParaSalvar.getAbsolutePath();

            // Garante que o nome do arquivo termine com .txt, mesmo que o usuário não digite.
            if (!caminho.toLowerCase().endsWith(".txt")) {
                caminho += ".txt";
            }

            // Chama o controller que faz o trabalho pesado.
            data.exportarParaTXT(caminho);

            // Um feedback para o usuário saber que deu tudo certo.
            JOptionPane.showMessageDialog(this, "Dados exportados com sucesso para:\n" + caminho, "Exportação Concluída", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}