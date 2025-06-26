# Lojinha com Ranking de Clientes (ABC)

## Descrição

Este é um sistema de desktop simples para gerenciar uma pequena loja. Ele permite o cadastro de clientes e produtos, o registro de compras e a geração de um ranking de clientes baseado na curva ABC. Os dados são persistidos localmente em arquivos JSON.

## Funcionalidades

* **Login:** Tela de login simples para acesso ao sistema (usuário: `admin`, senha: `1234`).
* **Gestão de Clientes:** Adicionar, editar e excluir clientes.
* **Gestão de Produtos:** Adicionar, editar e excluir produtos.
* **Registro de Compras:** Associar um cliente a uma compra de um ou mais produtos.
* **Ranking ABC:** Exibe um ranking de clientes classificados em curvas A, B e C com base no valor total de suas compras.
* **Exportação de Dados:** Exporta a lista de clientes e produtos para um arquivo de texto (.txt).

## Tecnologias Utilizadas

* **Java:** Linguagem de programação principal.
* **Java Swing:** Para a construção da interface gráfica.
* **Maven:** Para gerenciamento de dependências e do build do projeto.
* **Gson:** Biblioteca do Google para manipulação de JSON, usada para salvar e carregar os dados.

## Como Executar o Projeto

### Pré-requisitos

* Java Development Kit (JDK) 8 ou superior instalado.
* Apache Maven instalado.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/lojinhacomrankingdeclientes.git](https://github.com/seu-usuario/lojinhacomrankingdeclientes.git)
    ```
2.  **Navegue até o diretório do projeto:**
    ```bash
    cd lojinhacomrankingdeclientes
    ```
3.  **Compile e empacote o projeto com o Maven:**
    ```bash
    mvn clean install
    ```
4.  **Execute o arquivo JAR gerado:**
    ```bash
    java -jar target/lojinha-ranking-1.0-SNAPSHOT.jar
    ```
    *Obs: O nome do arquivo `.jar` pode variar. Verifique o nome gerado na pasta `target/`.*

## Estrutura do Projeto

O projeto segue a estrutura padrão do Maven:

.
├── data/                  # Contém os arquivos de dados em JSON
│   ├── clientes.json
│   ├── compras.json
│   └── produtos.json
├── pom.xml                # Arquivo de configuração do Maven
└── src
└── main
└── java
└── app
├── controller     # Lógica de negócio e manipulação dos dados
│   └── DataController.java
├── Main.java      # Ponto de entrada da aplicação
├── model          # Classes de domínio (Cliente, Produto, Compra)
│   ├── Cliente.java
│   ├── Compra.java
│   └── Produto.java
└── view           # Classes da interface gráfica (Janelas e componentes)
├── ClienteView.java
├── CompraView.java
├── LoginView.java
├── MainView.java
├── ProdutoView.java
└── RankingView.java

## Como Contribuir

1.  Faça um *fork* deste repositório.
2.  Crie uma nova *branch* (`git checkout -b feature/sua-feature`).
3.  Faça o *commit* de suas alterações (`git commit -m 'Adiciona nova feature'`).
4.  Faça o *push* para a *branch* (`git push origin feature/sua-feature`).
5.  Abra um *Pull Request*.
