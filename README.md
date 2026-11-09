# Detector de Fraude Bancária

Demonstração didática de classificação em **Java 17 e Weka 3.8.6**, usando uma árvore de decisão J48 e transações sintéticas definidas no próprio código.

## Como funciona

1. Define os atributos valor, origem (`nacional` ou `internacional`) e classe (`sim` ou `nao`).
2. Adiciona 11 exemplos sintéticos de treinamento.
3. Treina o classificador J48.
4. Classifica 10 transações de exemplo e imprime os resultados no terminal.

O programa atual **não carrega dados bancários de CSV ou ARFF**. O arquivo `data/iris.arff` existe, mas não é usado pela classe principal.

## Executar

Pré-requisitos: **JDK 17 ou superior** e **Maven instalado**. O repositório não inclui Maven Wrapper.

```powershell
git clone https://github.com/Jaumbitin/DetectorDeFraudeBancaria.git
cd DetectorDeFraudeBancaria
mvn compile dependency:copy-dependencies
java -cp 'target/classes;target/dependency/*' DetectorDeFraudeBancaria
```

No Linux/macOS, use `:` no classpath: `java -cp 'target/classes:target/dependency/*' DetectorDeFraudeBancaria`.
No IntelliJ, importe o `pom.xml` e execute o método `main` da classe `DetectorDeFraudeBancaria`.

## Estrutura

```text
pom.xml                                      Dependência do Weka e configuração Java
src/main/java/DetectorDeFraudeBancaria.java    Treinamento e exemplos de classificação
data/iris.arff                               Dataset adicional, não utilizado
```

## Limitações e validação

Não há testes automatizados, separação entre treino e teste, validação cruzada ou métricas de desempenho. Os exemplos impressos demonstram o uso da biblioteca; não medem a qualidade de um detector real. O projeto não está validado para decisões financeiras ou identificação de fraude em produção.
