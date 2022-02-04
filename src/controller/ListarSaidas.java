package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import armazem.Armazem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import transacao.Transacao;

public class ListarSaidas {

  @FXML
  private TableView<Transacao> tabela;

  @FXML
  private TableColumn<Transacao, String> colunaDataHora;

  @FXML
  private TableColumn<Transacao, String> colunaDestino;

  @FXML
  private TableColumn<Transacao, String> colunaProduto;

  @FXML
  private TableColumn<Transacao, Integer> colunaQuantidade;

  Armazem armazem = Armazem.getInstance();

  public void initialize() {
    colunaDataHora.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getData()));
    colunaDestino.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getSaida().getNomeOrigem()));
    colunaProduto.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getProduto().getNome()));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    tabela.setItems(listaDeTransacoes());
  }

  @FXML
  protected void getTheUserFilePath(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage stage = new Stage();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialFileName("relatorio de saida de produtos.csv");
    File file = fileChooser.showSaveDialog(stage);
    ArrayList<String> dados = Transacao.lerEspecifico("Saida");
    if (file != null) {
      saveTextToFile(dados, file);
    }
  }
  
  private void saveTextToFile(ArrayList<String> content, File file) {
    try {
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.append("Id Saida|");
        writer.append("Quantidade de Produtos|");
        writer.append("Id Produto|");
        writer.append("Nome Produto|");
        writer.append("Descricao Produto|");
        writer.append("Quantidade em Estoque|");
        writer.append("Quantidade Minima|");
        writer.append("Id Categoria|");
        writer.append("Nome Categoria|");
        writer.append("Descricao Categoria|");
        writer.append("Produto Ativo|");
        writer.append("Id Unidade Destino|");
        writer.append("Nome Unidade Destino|");
        writer.append("Cidade Unidade Destino|");
        writer.append("Bairro Unidade Destino|");
        writer.append("Rua Unidade Destino|");
        writer.append("Numero Unidade Destino|");
        writer.append("Unidade Destino Ativa|");
        writer.append("Data/Hora da Saida|");
        writer.append("\n");
        for (String linha : content) {
          writer.append(linha);
          writer.append("\n");
        }
        writer.close();
    } catch (IOException ex) {
        System.out.println(ex);
    }
  }

  private ObservableList<Transacao> listaDeTransacoes() {
    return FXCollections.observableArrayList(
        armazem.listarSaidas());
  }
}
