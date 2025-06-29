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

## Como Executar o Projeto (Para Desenvolvedores)

### Pré-requisitos

* Java Development Kit (JDK) 8 ou superior instalado.
* Apache Maven instalado.
* Git instalado.

### Passos

1.  **Clone o repositório:**
    *Abra um terminal ou Prompt de Comando e use o comando `git clone`.*
    ```bash
    git clone [https://github.com/Angel0fTheNight/LojinhaComRankingDeClientes.git](https://github.com/Angel0fTheNight/LojinhaComRankingDeClientes.git)
    ```
2.  **Navegue até o diretório do projeto Maven:**
    *O projeto Maven está dentro de algumas subpastas. Use este comando para chegar ao local correto.*
    ```bash
    cd LojinhaComRankingDeClientes/mnt/data/LojinhaRankingMaven
    ```
3.  **Compile e empacote o projeto com o Maven:**
    *Este comando irá gerar um arquivo `.jar` executável com todas as dependências incluídas.*
    ```bash
    mvn clean package
    ```
4.  **Execute o arquivo JAR gerado:**
    *O nome do arquivo `.jar` a ser executado termina com `jar-with-dependencies`.*
    ```bash
    java -jar target/lojinha-ranking-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

## Como Usar o Executável (Arquivo .jar)

Esta seção é para quem quer apenas usar o programa, sem precisar compilar o código.

### Pré-requisitos

* Apenas o Java (versão 8 ou superior) instalado no computador.

### Passos

1.  **Obtenha o arquivo `.jar`:**
    * Após o processo de compilação, localize o arquivo `lojinha-ranking-1.0-SNAPSHOT-jar-with-dependencies.jar` dentro da pasta `target`.

2.  **Prepare a Pasta de Execução:**
    * Copie o arquivo `.jar` para qualquer pasta de sua preferência (ex: Área de Trabalho).
    * Na mesma pasta onde você colou o `.jar`, crie uma nova pasta chamada **`data`**. O programa usará esta pasta para salvar os dados dos clientes e produtos.

3.  **Execute o Programa:**
    * Dê um **duplo-clique** no arquivo `lojinha-ranking-1.0-SNAPSHOT-jar-with-dependencies.jar` para iniciar a aplicação.
    * (Alternativa) Se o duplo-clique não funcionar, abra um Prompt de Comando nessa pasta e execute: `java -jar lojinha-ranking-1.0-SNAPSHOT-jar-with-dependencies.jar`.

## Estrutura do Projeto

A estrutura de arquivos do projeto é a seguinte:
LojinhaComRankingDeClientes/
└── mnt/
└── data/
└── LojinhaRankingMaven/
├── data/
│   ├── clientes.json
│   ├── compras.json
│   └── produtos.json
├── pom.xml
└── src/
└── main/
└── java/
└── app/
├── controller/
├── model/
└── view/
## 📥 Como baixar e executar o Lojinha.exe

1. Acesse a página principal do repositório:  
   [https://github.com/Angel0fTheNight/LojinhaComRankingDeClientes](https://github.com/Angel0fTheNight/LojinhaComRankingDeClientes)

2. Clique na aba **Tags** ou **Releases** (ela fica no topo do repositório, ao lado de *Code* e *Issues*).

3. Na lista de versões/tags, selecione a versão mais recente (por exemplo: `v1.0`).

4. Dentro da página da tag ou release, role para baixo até a seção **Assets**.

5. Você verá um link para baixar o arquivo `Lojinha.exe` (ou um arquivo compactado contendo o executável). Clique para fazer o download.

6. Extraia (se necessário) e execute o arquivo `Lojinha.exe` diretamente no seu computador (Windows).  
   > ⚠️ **Atenção:** Execute apenas em um ambiente confiável, já que é um executável.

7. O programa vai abrir com a interface da lojinha, permitindo:
   - Cadastrar produtos e clientes
   - Registrar compras
   - Ver o ranking dos melhores clientes

