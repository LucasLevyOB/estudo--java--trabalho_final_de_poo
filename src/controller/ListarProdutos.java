package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import armazem.Armazem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import produto.Produto;

public class ListarProdutos implements Initializable {

  @FXML
  private TableView<Produto> tabela;

  @FXML
  private TableColumn<Produto, String> colunaNome;

  @FXML
  private TableColumn<Produto, String> colunaCategoria;

  @FXML
  private TableColumn<Produto, String> colunaDescricao;

  @FXML
  private TableColumn<Produto, Integer> colunaQuantidade;

  @FXML
  private TableColumn<Produto, String> colunaAtivo;

  Armazem armazem = Armazem.getInstance();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

    // colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    colunaCategoria.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCategoria().getNome()));

    colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEmEstoque"));

    colunaAtivo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getStatus() ? "Sim" : "Não"));
    ;

    tabela.setItems(listaDeClientes());
  }

  @FXML
  protected void getTheUserFilePath(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage stage = new Stage();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialFileName("relatorio de produtos.csv");
    File file = fileChooser.showSaveDialog(stage);
    ArrayList<String> dados = Produto.exportar();
    if (file != null) {
      saveTextToFile(dados, file);
    }
  }
  
  private void saveTextToFile(ArrayList<String> content, File file) {
    try {
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.append("Id Produto|");
        writer.append("Nome Produto|");
        writer.append("Descricao Produto|");
        writer.append("Quantidade em Estoque|");
        writer.append("Quantidade Minima|");
        writer.append("Id Categoria|");
        writer.append("Nome Categoria|");
        writer.append("Descricao Categoria|");
        writer.append("Produto Ativo|");
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

  private ObservableList<Produto> listaDeClientes() {
    return FXCollections.observableArrayList(
        armazem.getProdutosOrdenados());
  }
}
