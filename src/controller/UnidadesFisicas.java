package controller;

import armazem.Armazem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import unidadeFisica.Endereco;
import unidadeFisica.UnidadeFisica;

public class UnidadesFisicas {

  @FXML
  private TableView<UnidadeFisica> tabela;

  @FXML
  private TableColumn<UnidadeFisica, String> colunaNome;

  @FXML
  private TableColumn<UnidadeFisica, String> colunaEndereco;
  @FXML
  private TableColumn<UnidadeFisica, String> colunaAtiva;

  @FXML
  private TextField nomeUnidadeFisica;

  @FXML
  private TextArea enderecoUnidadeFisica;

  Armazem armazem = Armazem.getInstance();

  public void initialize() {
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    colunaAtiva.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getStatus() ? "Sim" : "Não"));
    tabela.setItems(listaDeUnidadesFisicas());
  }

  @FXML
  protected void cadastrarUnidadeFisica(ActionEvent event) {
    Armazem armazem = Armazem.getInstance();
    String[] arrayEndereco = enderecoUnidadeFisica.getText().split(", ");
    Endereco endereco = new Endereco(arrayEndereco[0], arrayEndereco[1], arrayEndereco[2],
        Integer.parseInt(arrayEndereco[3]));
    armazem.adicionarUnidadeFisica(new UnidadeFisica(0, nomeUnidadeFisica.getText(), endereco, true));
    tabela.setItems(listaDeUnidadesFisicas());
  }

  private ObservableList<UnidadeFisica> listaDeUnidadesFisicas() {
    return FXCollections.observableArrayList(
        armazem.getUnidades().values());
  }
}
