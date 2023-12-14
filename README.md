# Curso-Java-Rocketseat

## Descrição

Aplicativo simples de to-do-list, onde a pessoa cadastrar o títudo da terefa, descrição da tarefa, a data postada e a data final para concluir a tarefa. <br> <br> 
Esse aplicativo é uma simples versão de uma API usando o Spring-boot java versão 17, ele é apenas a versão back-end do sistema, pois o intuito desse aplicativo é a dockerização e a implatanção no kubernets usando o helm e o kind e o banco de dados da Postgresql. <br>

### Funcionamento
Já como é uma API usando apenas o back-end, para testar as requisições é necessário as seguintes ferramentas.
<br>
Ferramentas necessárias:
- [Insomnia](https://insomnia.rest/download)
- [Postman](https://www.postman.com/downloads/)
  
Requisições disponíveis são as `GET`, `POST`, `DELETE`, `PATH` <br><br>

### Funcionamento do código
  O código funciona com 4 classes e mais main, a classe. <br> <br>
  `Task.java` é o mapeamento da minha tabela do meu banco de dados postgres.  
  `TaskController.java` é a classe responsável pelo mapeamento das requisições na API.
  `TaskService.java` é a classe responsável pelo os dados a serem gravados no banco de dados.<br>
  `TaskRepository.java` essa interface fornece uma série de métodos úteis para realizar operações comuns de CRUD (Create, Read, Update, Delete) no banco de dados para a entidade Task.

### Funcionamento do Dockerfile
  `FROM openjdk:17` Puxa a imagem do java 17 do dockerhub <br>
  `VOLUME /tmp` É um volume criado para os dados persistirem no banco de dados <br>
  `EXPOSE 8080` Informa que a aplicação vai estar escutando na porta 8080 <br>
  `RUN mkdir -p /app` Cria um diretório dentro do sistema de arquivos do contêiner <br>
  `APP target/to-do-list-0.0.1-SNAPSHOT.jar` adiciona o .jar da aplicação no diretório criado /app e depois é renomeado o .jar para app.jar <br>
  `ENTRYPOINT[...]` É o ponto de entrada padrão para o contêiner executando os seguintes comandos.<br>
  - `java` Iniciar a JVM <br>
  - `-Djava.security.egd=file:/dev/./urandom` Configura a propriedade de segurança da JVM para usar. <br>
  - `-Dspring.profiles.active=container` Configura a propriedade de perfil para iniciar com o contêiner. <br>
  - `-jar /app/app.jar` Executa a aplicação atravéz do docker. <br>
     
### Funcionamento do docker-compose
  - ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/4a3c4016-f3e5-40f1-83dc-599c606438de) <br>

  Este serviço define um contêiner para o banco de dados PostgreSQL. <br> <br>
`image: postgres:latest` Usa a imagem oficial do PostgreSQL na versão mais recente.<br>
`network_mode: bridge:` Define o modo de rede como bridge. <br>
`container_name: postgres:` Define o nome do contêiner como "postgres". <br>
`volumes: - postgres-data:/var/lib/postgresql/data:` Cria um volume chamado "postgres-data" para persistir os dados do PostgreSQL. <br>
`expose: - 5432:` Exponha a porta 5432 internamente. <br>
`ports: - 5432:5432:` Mapeia a porta 5432 do host para a porta 5432 do contêiner, permitindo a conexão externa ao banco de dados. <br>
`environment: [...]:` Configura variáveis de ambiente para a instância do PostgreSQL, como senha, usuário e banco de dados. <br>
`restart: unless-stopped:` Garante que o contêiner seja reiniciado, a menos que seja explicitamente parado. <br>

![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/c4889e76-3968-42ed-9a2a-71a63d67b360) <br><br>
  Este serviço define um contêiner para a sua aplicação "dev-ops". <br><br>
  `image: dev-ops:latest:` Usa a imagem "dev-ops" na versão mais recente. <br>
  `network_mode: bridge:` Define o modo de rede como bridge. <br>
  `container_name: dev-ops:` Define o nome do contêiner como "dev-ops". <br>
  `expose: - 8080:` Exponha a porta 8080 internamente. <br>
  `ports: - 8080:8080:` Mapeia a porta 8080 do host para a porta 8080 do contêiner. <br>
  `restart: unless-stopped:` Garante que o contêiner seja reiniciado, a menos que seja explicitamente parado.<br>
  `depends_on: - postgres:` Define uma dependência em relação ao serviço "postgres", garantindo que o banco de dados esteja disponível antes de iniciar este serviço. <br>
  `links: - postgres:` Cria um link para o serviço "postgres", permitindo que o serviço "dev-ops" se comunique com o serviço do PostgreSQL usando o nome do contêiner "postgres". <br> <br>
  `Volumnes:` Define um volume para o contêiner <br>
  `postgres-data:` É usado pelo serviço Postgres para a persistência dos dados.

### Executar a aplicação com o docker-composer
  `docker build -t gabrielnathan/dev-ops:v9 .`
  `docker push gabrielnathan/dev-ops:v9` <br>
  `docker-compose up -d` <br>
  ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/ef198025-be4c-4b19-a7cf-9fe04cc99a49)
<br>
- Configuração do application.properties

### Usando o Helm Kubernetes
  Foi adicionados apenas 2 arquivos a mais na pasta que foi criado usando o comando do helm e algumas configurações no values.yaml, Chart.yaml e no deployment.yaml. <br> <br>
  `config-map.yaml`  Separa as configurações do pod e dos componentes, o que ajuda a manter as cargas de trabalho portáteis.  <br><br>
  `secrect.yaml` Evita que você tenha de incluir dados confidenciais no seu código  <br><br>
  `deployment.yaml` Configurações <br><br>
  ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/65b36c9d-8262-4ed1-97cc-34996f7fe32d)
   <br><br>
  `Values.yaml` Configurações adicionadas no final do arquivo <br><br>
  ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/45b0a5ab-1c8c-4eaf-a4ac-c9ef2f317ae8)  <br><br>
  `Chart.yaml` Adiciona a depência do postgres no arquivo <br><br>
  ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/dda63aff-b2bd-471a-865f-a7929bf0afb9)

### Executando com Helm e Kubernetes
  `helm install dev-ops ./dev-ops` <br>
  `$POD_NAME = kubectl get pods --namespace default -l "app.kubernetes.io/name=dev-ops,app.kubernetes.io/instance=dev-ops" -o jsonpath="{.items[0].metadata.name}"` <br>
  `$CONTAINER_PORT = kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}"` <br>
  `kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT` <br><br>
  ![image](https://github.com/GabrielNathan12/Curso-Java-Rocketseat/assets/76185909/5ee4c016-d039-47c4-aed4-5a2f34d1aa81)
  
