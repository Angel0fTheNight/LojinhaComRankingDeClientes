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
        clientes = carregar(CLIENTES_FILE, new TypeToken<List<Cliente>>(){}.getType());
        produtos = carregar(PRODUTOS_FILE, new TypeToken<List<Produto>>(){}.getType());
        compras = carregar(COMPRAS_FILE, new TypeToken<List<Compra>>(){}.getType());
    }

    private <T> List<T> carregar(String caminho, java.lang.reflect.Type type) {
        try (Reader reader = new FileReader(caminho)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
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

    public void adicionarProduto(Produto p) {
        produtos.add(p);
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
