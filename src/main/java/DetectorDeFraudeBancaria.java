import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Instances;

        // Ferramentas de log usadas para ocultar avisos do Weka(não afetam oo funcionamento do codigo)
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

public class DetectorDeFraudeBancaria {

    private Classifier classificador;
    private Instances dadosTreinamento;

    //atributos do conjuntoo de dados

    private Attribute atributoValor;
    private Attribute atributoOrigem;
    private Attribute atributoFraude;

    public void etapa2_definirAtributos() {

        atributoValor = new Attribute("atributoValor");

        ArrayList<String> valoresOrigem = new ArrayList<>();
        valoresOrigem.add("internacional");
        valoresOrigem.add("nacional");
        atributoOrigem = new Attribute("Origem", valoresOrigem);

        ArrayList<String> valoresFraude = new ArrayList<>();
        valoresFraude.add("sim");
        valoresFraude.add("nao");
        atributoFraude = new Attribute("Fraude", valoresFraude);

        ArrayList<Attribute> atributos = new ArrayList<>();
        atributos.add(atributoValor);
        atributos.add(atributoOrigem);
        atributos.add(atributoFraude);

        dadosTreinamento = new Instances("transacoes", atributos, 0);

        dadosTreinamento.setClassIndex(dadosTreinamento.numAttributes() - 1);
    }
        // metodo auxiliar para criar e adicionar uma nova transação ao dataset de treino
        /* singifica que aqui sera criada uma nova instancia de dados(uma linha)
        com a mesma quantidade de colunas(atributos) do dataset dadosTreinamento
        e como criar uma nova linha em branco em uma planilha, onde você ainda vai
        preencher as colunas: valor, origem e fraude
         */

    private void adicionarTransacao(double valor, String origem, String fraude) {
        Instance instancia = new DenseInstance(dadosTreinamento.numAttributes());
        instancia.setDataset(dadosTreinamento);
        instancia.setValue(atributoValor, valor);
        instancia.setValue(atributoOrigem, origem);
        instancia.setValue(atributoFraude, fraude);
        dadosTreinamento.add(instancia);
    }

    public void etapa4_adicionarExemplos(){

        adicionarTransacao(10000.0, "internacional", "sim");
        adicionarTransacao(5000.0, "internacional", "sim");
        adicionarTransacao(7000.0, "internacional", "sim");
        adicionarTransacao(9000.0, "internacional", "sim");
        adicionarTransacao(150.0, "nacional", "nao");
        adicionarTransacao(200.0, "nacional", "nao");
        adicionarTransacao(300.0, "nacional", "nao");
        adicionarTransacao(400.0, "nacional", "nao");

        //exemplos adicionais para melhorar a acurácia do modelo
        adicionarTransacao(1000.0, "nacional", "sim");
        adicionarTransacao(1500.0, "nacional", "sim");
        adicionarTransacao(20000.0, "nacional", "sim");
    }

    public void etapa5_treinarModelo() throws Exception {
        classificador = new J48();
        classificador.buildClassifier(dadosTreinamento);
    }

    public String classificarTransacao(double valor, String origem) throws Exception {
        Instance novaInstancia = new DenseInstance(dadosTreinamento.numAttributes());
        novaInstancia.setDataset(dadosTreinamento);
        novaInstancia.setValue(atributoValor, valor);
        novaInstancia.setValue(atributoOrigem, origem);

        double previsao = classificador.classifyInstance(novaInstancia);
        return "Fraude: " + dadosTreinamento.classAttribute().value((int) previsao);
    }
    public static void main(String[] args) {
        // Ocultar avisos do Weka
        Logger.getLogger("com.github.fommil.netlib").setLevel(Level.SEVERE);

        DetectorDeFraudeBancaria detectorDeFraudeBancaria = new DetectorDeFraudeBancaria();

        try {
            detectorDeFraudeBancaria.etapa2_definirAtributos();
            detectorDeFraudeBancaria.etapa4_adicionarExemplos();
            detectorDeFraudeBancaria.etapa5_treinarModelo();

            // Testando a classificação de uma nova transação
            String resultado1 = detectorDeFraudeBancaria.classificarTransacao(3000.0, "internacional");
            String resultado2 = detectorDeFraudeBancaria.classificarTransacao(200.0, "nacional");
            String resultado3 = detectorDeFraudeBancaria.classificarTransacao(2000.0, "internacional");
            String resultado4 = detectorDeFraudeBancaria.classificarTransacao(150.0, "nacional");
            String resultado5 = detectorDeFraudeBancaria.classificarTransacao(10000.0, "internacional");
            String resultado6 = detectorDeFraudeBancaria.classificarTransacao(300.0, "nacional");
            String resultado7 = detectorDeFraudeBancaria.classificarTransacao(7500.0, "internacional");
            String resultado8 = detectorDeFraudeBancaria.classificarTransacao(400.0, "nacional");
            String resultado9 = detectorDeFraudeBancaria.classificarTransacao(401.0, "nacional");
            String resultado10 = detectorDeFraudeBancaria.classificarTransacao(1.0, "internacional");


            System.out.println(resultado1);
            System.out.println(resultado2);
            System.out.println(resultado3);
            System.out.println(resultado4);
            System.out.println(resultado5);
            System.out.println(resultado6);
            System.out.println(resultado7);
            System.out.println(resultado8);

            System.out.println("Teste 9: " + resultado9);
            System.out.println("Teste 10: " + resultado10);


        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}