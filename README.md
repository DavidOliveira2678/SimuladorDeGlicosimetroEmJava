# :drop_of_blood: GlucaJava 

## :grey_question: Sobre o projeto
O GlucaJava é simulador de um gicosímetro desenvolvido utilizando Java na JDK 21 e Maven para gerenciamento de dependências. O projeto foi feito com finalidade de estudo, especialmente para o reforço de desenvolvimento utilizando Controllers, Services e Repositories _(ou, como no caso do GlucaJava: Entities)_.

### Sumário de navegação:
- [Tecnologias utilizadas no projeto](#gear-tecnologias-utilizadas)
- [Implementação de Listas Circulares](#repeat-implementação-de-listas-circulares)
- [Banco de dados no GlucaJava](#card_file_box-banco-de-dados)
- [Clonando o repositório](#rocket-clonar-o-repositório)

## :gear: Tecnologias utilizadas:
- **Java Swing** - para criação da interface gráfica;
- **Apache PDF Box** - para geração de PDFs;
- **MySql JDBC** - JDBC com o driver do MySql para conexão com banco de dados;
- **Dotenv Java** - para integração do .env e acesso às variáveis de ambiente.
- **CircularList e CircularLinkedList** - para melhor navegação entre medições _(implementação pessoal)_.

## :repeat: Implementação de listas circulares
Em glicosímetros convencionais, ao chegar na última medição registrada e apertar para exibir a próxima, é retornada a primeira medição que foi mostrada ao carregar a memória do aparelho.
Essa é a mesma lógica da estrutura de dados **Lista Circular**, em que o último elemento aponta para o primeiro, formando uma estrutura cíclica - e essa mesma estrutura de dados foi implementada no GlucaJava!


### Lista circular duplamente encadeada
Uma Lista Circular Duplamente Encadeada é uma estrutura de dados que possui o mesmo funcionamento de uma lista circular convencional, com a adição de que é possível navegar tanto para o próximo elemento quanto para o elemento anterior, faznendo com que o primeiro elemento aponte para o último e o último elemento aponte para o primeiro. Essa lógica foi implementada no GlucaJava para tornar a experiência o mais fiel possível aos medidores de glicose reais.

<a href="https://github.com/DavidOliveira2678/GlucaJava/tree/main/src/main/java/br/com/SimuladorDeGlicosimetro/Utils/ListaCircular">:link:Visualizar implementação</a>

## :card_file_box: Banco de dados
O Sistema de Gerenciamento de Banco de Dados _(SGBD)_ escolhido para o GlucaJava foi o MySql.

O modelo conceitual do projeto - feito no BrModelo - é simples, contendo apenas a entidade medicoes, seus atributos e uma observação para determinação do atributo multivalorado "estado".
<div align="center">
  <img src="https://github.com/DavidOliveira2678/GlucaJava/blob/main/database/GlucaJavaModeloConceitual.jpg" alt="imagem do modelo conceitual do GlucaJava">
</div>

Para criar o banco de dados em sua máquina, basta rodar o script SQL presente no arquivo <a href="https://github.com/DavidOliveira2678/GlucaJava/blob/main/database/glucaJava.sql">glucaJava.sql<a> do repositório do GlucaJava. Você pode alterar o nome do banco de dados, mas para padronização é recomendável deixar como está.

## :rocket: Clonar o repositório
Para clonar o repositório e utilizar o GlucaJava em sua máquina, basta rodar o comando abaixo em seu terminal git:
```bash
git clone https://github.com/DavidOliveira2678/GlucaJava.git
```
Mas isso não é o suficiente para rodar a aplicação.

### :hammer_and_wrench: Configurar o seu repositório local
Caso você esteja utilizando o Eclipse IDE, você deve atualizar o projeto com o Maven _(botão direito no projeto -> Maven -> Update Project)_.

Além disso, é necessário criar e configurar as variáveis de ambiente com um arquivo `.env`:
- Dentro da pasta raiz do GlucaJava, você cria um novo arquivo .env;
- Siga os padrões do <a href="https://github.com/DavidOliveira2678/GlucaJava/blob/main/.env.example">exemplo de .env<a> disponível para configurar o seu próprio arquivo .env.


`.env.example:`

```bash
NOME_BD=[NOME_DO_SEU_BANCO_DE_DADOS]
USUARIO_BD=[NOME_DA_SUA_CONEXAO_NO_MYSQL]
SENHA_BD=[SENHA_DA_SUA_CONEXAO_NO_MYSQL]
LOCALHOST_BD=[PORTA_DO_HOST_DO_SEU_MYSQL]
```

:warning: Lembrando que caso o `.env` não esteja na pasta raiz do projeto, o dotenv não conseguirá inicializar as variáveis e não será possível rodar o projeto.

</br>
Espero que você goste do GlucaJava! :purple_heart:
