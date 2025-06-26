package app.view;

import app.controller.DataController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Login - Lojinha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Login no sistema", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        form.add(new JLabel("Usuário:"));
        form.add(userField);
        form.add(new JLabel("Senha:"));
        form.add(passField);
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(form, BorderLayout.CENTER);

        JButton loginButton = new JButton("Entrar");
        add(loginButton, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (user.equals("admin") && pass.equals("1234")) {
                dispose();
                new MainView(new DataController());
            } else {
                JOptionPane.showMessageDialog(this, "Login inválido");
            }
        });

        setVisible(true);
    }
}
