# atv-final

Atividade Avaliativa - trabalho semestral WEB-III

Essa aplicação foi desenvolvida com vscode e utilizado banco de dados mysql com a ferramenta
gráfica mysql-workbench para monitorar os dados no banco. Utilizado framework springboot, com as dependencias Thymeleaf, Spring Web, Spring Boot devtools, Spring Data Jpa, Mysql driver e Lombok.

Para rodar esse projeto é necessário ter o spring boot na extensão do vscode para iniciar 
o host da aplicação. Assim que o vscode terminar de iniciar o projeto entrar pelo
localhost no navegador.

Funcionalidades

Ao acessar o site é necessário fazer o login para prosseguir a gestão das fitas.
- Com usuário logado a primeira tela é a listagem de fitas do banco.
- Na tela inicial é possivel pesquisar, cadastrar, editar ou excluir nova fita, gerenciar categorias ou sair.
- Na tela de gerenciar categorias é possível adicionar, editar, excluir (caso não tiver relação
com nenhum filme) categorias. Necessário começar por aqui pois os filmes tem relações com as categorias.
- Na tela de cadastro das fitas é possivel preencher os seguintes campos: 
  Identificador (Número inteiro - Seria o código da fita)
  Titulo da fita - Texto de 100 letras
  Imagem (opcional) - url da imagem da fita, um caminho de armazenamento externo ao programa.
  Diretor - Texto de 100 letras
  Categoria - Seleção criada pelo próprio usuário na tela de gerenciar categorias
  Data de cadastro - usuário seleciona a data de hoje.
  Status da fita - Usuário seleciona entre 3 opções (Disponível, Alugada, ou Indisponível)
  Botão de cadastrar fita para confirmar. (em seguida a lista de fitas já é exibida)

Resultados Esperados.

Com essa aplicação simples, é possível cadastrar, armazenar e gerir uma seleção de fitas vhs,
após concluido projeto, as implementações ficam mais fácil, se quiser adicionar um campo
localização, prateleira, corredor, etc. Estilizar com arquivo css, infinitas possibilidades.
Através do 'passo a passo' que foi disponibilizado no ava, segue uma agenda de desenvolvimento. 

Comandos git

git add . (adicionar todas as alterações na fila de envio)
git commit - m "Texto do commit" (savlar as alterações no commit)
git push origin main (enviar tudo para o git)


Dia 27/06
- Criado o repositório no git com readme
- Criado o projeto maven com as dependencias solicitadas
  Spring Web, Thymeleaf, Spring JPA, Mysql driver, Lombok e Spring boot.
- Dado git ini na pasta para vincular ao repositorio
- Feito git pull para puxar o readme e git push para enviar o projeto 
  inicial ao repositrio
- Incluido configurações do application properties para se comunicar com o banco mysql.
- Criado entidade VHS para começo da modelagem.

Dia 30/06

- Terminado a entidade vhs, foi apontado os crontroladores de cada atributo 
para se comportar com o mysqldriver.
- Criado classe categoria e feito a relação com a classe vhs, e tambem apontado 
controladores necessários.
- Criado a classe enum tapestatus.
- Criado a classe usuario, junto com seus repository e service, para implementação futura do login.
- Adicionado a dependencia segurity para encriptar a senha do usuario.
- Criado Vhs repository e service, adicionado metodos de comunicaço com o banco.
- Criado filtro para redirecionamento de telas com o login, levando em conta
o usuario session, para entrar somente se estiver logado.

Dia 01/07

- Criado uma classe de configuração de segurança para desabilitar o auto
mapeamento do spring security, assim os controllers não serão bloqueados.
- Feito o teste de cadastro de usuario.
- Corrigido um erro de relação entre a tabela categoria e vhs.
- Criado as tela de interação: Cadastro das fitas e gerenciar categorias,
foi feito os services e repositories da categoria para que se comuniquem com o banco,
e ajustado o controller para o funcionamento das novas telas.
- Estilização do front nas telas.
- Inclusão do campo pesquisa na listagem das fitas.

Dia 02/07

- Criado o CRUD, para deletar e editar/atualizar as fitas.
- Feito uma readequação dos controllers, separado em categoria e login.
- Criado os metodos controladores dessas novas açes.
- Criado o crud de categorias e as ações dos respecitovos controllers, service, repository.
- Motificado a lista das fitas para exibir imagens, através do url e ser opcional.
- Botão de excluir e editar das categorias foram desenvolvidos. Com seus respectivos
service e repository. Se houver categorias associadas as fitas não ser permitido a exclusão.
- Implementação do campo código de barras na classe vhs, e todas modificações necessárias.
- Adicionado imagens de fundo e logo para melhorar o visual.
- Feito a correção da mensagem de erro do login.


