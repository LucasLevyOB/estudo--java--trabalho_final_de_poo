package controller;

import armazem.Armazem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
  private Label mensagemNomeUnidade;
  
  @FXML
  private TextArea enderecoUnidadeFisica;

  @FXML
  private Label mensagemEnderecoUnidade;

  Armazem armazem = Armazem.getInstance();

  public void initialize() {
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    colunaAtiva.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getStatus() ? "Sim" : "Não"));
    tabela.setItems(listaDeUnidadesFisicas());
  }

  private String verificaEndereco() {

    if (enderecoUnidadeFisica.getText().equals(""))
      return "O campo está vazio";
    else if (!enderecoUnidadeFisica.getText().matches("^\\w+, \\w+, \\w+, \\w+$"))
      return "Veja se o endereço está formato como no exemplo";
    else if (!enderecoUnidadeFisica.getText().split(", ")[3].matches("^[0-9]*$"))
      return "O último valor deve ser um número positivo";
    return "";
  }

  private String verificaNome() {
    if (nomeUnidadeFisica.getText().equals(""))
      return "O campo está vazio";
    return "";
  }

  @FXML
  protected void cadastrarUnidadeFisica(ActionEvent event) {
    String verificaNomeRes = verificaNome();
    String verificaEnderecoRes = verificaEndereco();
    if (verificaNomeRes.equals("") && verificaEnderecoRes.equals("")) {
      Armazem armazem = Armazem.getInstance();
      String[] arrayEndereco = enderecoUnidadeFisica.getText().split(", ");
      Endereco endereco = new Endereco(arrayEndereco[0], arrayEndereco[1], arrayEndereco[2],
          Integer.parseInt(arrayEndereco[3]));
      armazem.adicionarUnidadeFisica(new UnidadeFisica(0, nomeUnidadeFisica.getText(), endereco, true));
      tabela.setItems(listaDeUnidadesFisicas());
      mensagemNomeUnidade.setText("");
      mensagemEnderecoUnidade.setText("");
    } else {
      mensagemNomeUnidade.setText(verificaNomeRes);
      mensagemEnderecoUnidade.setText(verificaEnderecoRes);
    }
  }

  private ObservableList<UnidadeFisica> listaDeUnidadesFisicas() {
    return FXCollections.observableArrayList(
        armazem.getUnidadesOrdenadas());
  }
}
