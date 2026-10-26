# 🏦 Detector de Fraude Bancária

Este repositório contém um modelo de classificação de Machine Learning desenvolvido em **Java** utilizando a **API do Weka**. O objetivo principal do projeto é analisar transações financeiras e classificar possíveis atividades fraudulentas utilizando algoritmos baseados em árvores de decisão.

## 🚀 Tecnologias Utilizadas

- **Java** 
- **Weka API** (Waikato Environment for Knowledge Analysis)
- **IntelliJ IDEA** (Ambiente de desenvolvimento recomendado)

## ⚙️ Como o Modelo Funciona

O detector utiliza algoritmos de classificação (como Árvores de Decisão) treinados a partir de um conjunto de dados bancários (datasets em formato `.arff` ou `.csv`). Através da API do Weka integrada ao código Java, o sistema constrói um modelo de predição capaz de identificar os padrões de anomalias que caracterizam uma fraude com base nos atributos de entrada.

## 📁 Estrutura Base do Projeto

```text
📦 DetectorDeFraudeBancaria
 ┣ 📂 src               # Código-fonte em Java
 ┣ 📂 data              # Datasets de treinamento e teste
 ┣ 📂 lib               # Bibliotecas externas (weka.jar)
 ┗ 📜 README.md         # Documentação do projeto
