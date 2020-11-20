# Atualização de contas na Receita

## Pré-Requisito
 * Java 11
 * Gradle
 
## Descrição
Projeto para leitura de arquivo CSV com o nome 'conta'
 
## Estrutura
Estruturação de packages por feature, divididas entre:

```
* common
* input
* output
```
Os packages input e output possuem seus próprios packages dedicados 
com as classes específicas para entrada e saída de dados.
O package common, possui packages e classes comuns ao uso de input e output, 
e ainda os recursos utilizados pela aplicação.  

### Build
 * Pelo terminal, navegue até a pasta do projeto e rode o comando:
```
 ./gradlew clean build
```

### Run
* Novamente pelo terminal, já dentro da pasta do projeto, navegue até a pasta buil/libs, 
e execute o comando:  
```
java -jar evaluation-0.0.1-SNAPSHOT.jar
```

O arquivo será processado, e a aplicção ficará monitorando contas não atualizadas, 
para reprocessar novamente.