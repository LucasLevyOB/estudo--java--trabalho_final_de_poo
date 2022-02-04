package controller;

import armazem.Armazem;
import dataHora.DataHora;
import dataHora.Hora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import produto.Produto;
import unidadeFisica.UnidadeFisica;

public class CadastrarSaidas {

  @FXML
  private DatePicker dataSaida;
  @FXML
  private TextField horaSaida;

  @FXML
  private TextField minutoSaida;

  @FXML
  private ChoiceBox<String> destinoSaida;

  @FXML
  private ChoiceBox<String> produtoSaida;

  @FXML
  private TextField quantidadeSaida;

  private Armazem armazem = Armazem.getInstance();

  public void initialize() {
    destinoSaida.getItems().removeAll(destinoSaida.getItems());
    for (String unidade : armazem.getUnidades().keySet()) {
      destinoSaida.getItems().add(unidade);
    }
    destinoSaida.getSelectionModel().select("Selecione");

    produtoSaida.getItems().removeAll(produtoSaida.getItems());
    for (Produto produto : armazem.getProdutos().values()) {
      if (produto.getStatus())
        produtoSaida.getItems().add(produto.getNome());
    }
    produtoSaida.getSelectionModel().select("Selecione");

  }

  @FXML
  void cadastrarSaida(ActionEvent event) {
    UnidadeFisica unidade = armazem.getUnidadeFisica(destinoSaida.getValue().toString());
    DataHora data = new DataHora(dataSaida.getValue(),
        new Hora((byte) Integer.parseInt(horaSaida.getText()), (byte) Integer.parseInt(minutoSaida.getText())));
    armazem.saida(produtoSaida.getValue().toString(), Integer.parseInt(quantidadeSaida.getText()), unidade, data);
  }
  
}
