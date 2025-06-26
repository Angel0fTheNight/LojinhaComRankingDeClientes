package app.controller;

import app.model.Cliente;
import app.model.Produto;
import app.model.Compra;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DataController {
    private static final String CLIENTES_FILE = "data/clientes.json";
    private static final String PRODUTOS_FILE = "data/produtos.json";
    private static final String COMPRAS_FILE = "data/compras.json";

    private List<Cliente> clientes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Compra> compras = new ArrayList<>();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DataController() {
        carregarDados();
    }

    private void carregarDados() {
        // Garante que o diretório 'data' exista
        new File("data").mkdirs();

        clientes = carregar(CLIENTES_FILE, new TypeToken<List<Cliente>>(){}.getType());
        produtos = carregar(PRODUTOS_FILE, new TypeToken<List<Produto>>(){}.getType());
        compras = carregar(COMPRAS_FILE, new TypeToken<List<Compra>>(){}.getType());
    }

    private <T> List<T> carregar(String caminho, java.lang.reflect.Type type) {
        try (Reader reader = new FileReader(caminho)) {
            List<T> dados = gson.fromJson(reader, type);
            return dados == null ? new ArrayList<>() : dados;
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Se o arquivo não existe, retorna lista vazia
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T> void salvar(String caminho, List<T> lista) {
        try (Writer writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> getClientes() { return clientes; }
    public List<Produto> getProdutos() { return produtos; }
    public List<Compra> getCompras() { return compras; }

    public void adicionarCliente(Cliente c) {
        clientes.add(c);
        salvar(CLIENTES_FILE, clientes);
    }

    public void atualizarCliente(Cliente clienteOriginal, String novoNome, String novoEmail) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equals(clienteOriginal.getEmail())) {
                Cliente clienteAtualizado = new Cliente(novoNome, novoEmail);
                clienteAtualizado.adicionarGasto(clienteOriginal.getTotalGasto()); // Mantém o total gasto
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
        salvar(CLIENTES_FILE, clientes);
    }

    public void excluirCliente(Cliente c) {
        clientes.removeIf(cliente -> cliente.getEmail().equals(c.getEmail()));
        salvar(CLIENTES_FILE, clientes);
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
        salvar(PRODUTOS_FILE, produtos);
    }

    public void atualizarProduto(Produto produtoOriginal, String novoNome, double novoPreco) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(produtoOriginal.getNome())) {
                produtos.set(i, new Produto(novoNome, novoPreco));
                break;
            }
        }
        salvar(PRODUTOS_FILE, produtos);
    }

    public void excluirProduto(Produto p) {
        produtos.removeIf(produto -> produto.getNome().equals(p.getNome()));
        salvar(PRODUTOS_FILE, produtos);
    }

    public void registrarCompra(Compra c) {
        compras.add(c);
        for (Cliente cli : clientes) {
            if (cli.getEmail().equals(c.getClienteEmail())) {
                for (Produto p : produtos) {
                    if (p.getNome().equals(c.getProdutoNome())) {
                        cli.adicionarGasto(p.getPreco() * c.getQuantidade());
                    }
                }
            }
        }
        salvar(COMPRAS_FILE, compras);
        salvar(CLIENTES_FILE, clientes);
    }
}