package app.model;

public class Compra {
    private String clienteEmail;
    private String produtoNome;
    private int quantidade;

    public Compra(String clienteEmail, String produtoNome, int quantidade) {
        this.clienteEmail = clienteEmail;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
    }

    public String getClienteEmail() { return clienteEmail; }
    public String getProdutoNome() { return produtoNome; }
    public int getQuantidade() { return quantidade; }
}
