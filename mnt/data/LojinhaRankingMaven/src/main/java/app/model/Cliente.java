package app.model;

public class Cliente {
    private String nome;
    private String email;
    private double totalGasto;

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.totalGasto = 0.0;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public double getTotalGasto() { return totalGasto; }

    public void adicionarGasto(double valor) {
        this.totalGasto += valor;
    }
}
