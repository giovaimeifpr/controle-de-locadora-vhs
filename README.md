# atv-final

Atividade Avaliativa - trabalho semestral WEB-III

Essa aplicação foi desenvolvida com vscode e utilizado banco de dados mysql com a ferramenta
gráfica mysql-workbench para monitorar os dados no banco. Utilizado framework springboot, com as dependencias Thymeleaf, Spring Web, Spring Boot devtools, Spring Data Jpa, Mysql driver e Lombok.

Para rodar esse projeto é necessário ter o spring boot na extensão do vscode para iniciar 
o host da aplicação. Assim que o vscode terminar de iniciar o projeto entrar pelo
localhost no navegador.

Foi usado o 'passo a passo' que foi disponibilizado no ava, segue uma agenda de desenvolvimento, e no final desse documento vai estar descrito as funcionalidades e resultados esperados.


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

