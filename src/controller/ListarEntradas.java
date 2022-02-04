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

public class ListarEntradas {

  @FXML
  private TableView<Transacao> tabela;

  @FXML
  private TableColumn<Transacao, String> colunaDataHora;

  @FXML
  private TableColumn<Transacao, String> colunaOrigem;

  @FXML
  private TableColumn<Transacao, String> colunaProduto;

  @FXML
  private TableColumn<Transacao, Integer> colunaQuantidade;

  Armazem armazem = Armazem.getInstance();

  public void initialize() {
    colunaDataHora.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getData()));
    colunaOrigem
        .setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getEntrada().getNomeOrigem()));
    colunaProduto.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getProduto().getNome()));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    tabela.setItems(listaDeTransacoes());
  }

  private ObservableList<Transacao> listaDeTransacoes() {
    return FXCollections.observableArrayList(
        armazem.listarEntradas());
  }

  @FXML
  protected void getTheUserFilePath(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage stage = new Stage();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialFileName("relatorio de entrada de produtos.csv");
    File file = fileChooser.showSaveDialog(stage);
    ArrayList<String> dados = Transacao.lerEspecifico("Entrada");
    if (file != null) {
      saveTextToFile(dados, file);
    }
  }
  
  private void saveTextToFile(ArrayList<String> content, File file) {
    try {
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.append("Id Entrada|");
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
        writer.append("Id Unidade Origem|");
        writer.append("Nome Unidade Origem|");
        writer.append("Cidade Unidade Origem|");
        writer.append("Bairro Unidade Origem|");
        writer.append("Rua Unidade Origem|");
        writer.append("Numero Unidade Origem|");
        writer.append("Unidade Origem Ativa|");
        writer.append("Data/Hora da Entrada|");
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
}
