package controller;

import armazem.Armazem;
import dataHora.DataHora;
import dataHora.Hora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import produto.Categoria;
import produto.Produto;
import unidadeFisica.UnidadeFisica;

public class CadastrarEntradas {

  @FXML
  private DatePicker dataEntrada;
  @FXML
  private TextField horaEntrada;
  @FXML
  private TextField minutoEntrada;
  @FXML
  private TextField quantidadeEntrada;
  @FXML
  private ChoiceBox<String> origemEntrada;
  @FXML
  private ChoiceBox<String> produtoEntrada;
  @FXML
  private TextField nomeProduto;
  @FXML
  private TextField quantidadeMinimaProduto;
  @FXML
  private TextArea descricaoProduto;
  @FXML
  private ChoiceBox<String> categoriaProduto;

  private Armazem start = Armazem.getInstance();

  public void initialize() {

    produtoEntrada.getItems().removeAll(produtoEntrada.getItems());
    for (String startedProd : start.getProdutos().keySet()) {
      produtoEntrada.getItems().add(startedProd);
    }
    produtoEntrada.getSelectionModel().select("Selecione");

    categoriaProduto.getItems().removeAll(categoriaProduto.getItems());
    for (String startedCat : start.getCategorias().keySet()) {
      categoriaProduto.getItems().add(startedCat);
    }
    categoriaProduto.getSelectionModel().select("Selecione");

    origemEntrada.getItems().removeAll(origemEntrada.getItems());
    for (String startedUni : start.getUnidades().keySet()) {
      origemEntrada.getItems().add(startedUni);
    }
    origemEntrada.getSelectionModel().select("Selecione");
  }

  @FXML
  protected void cadastrarEntrada(ActionEvent event) {
    if (!origemEntrada.getValue().equals("Selecione") || !produtoEntrada.getValue().equals("Selecione")
        || !categoriaProduto.getValue().equals("Selecione")) {
      if (!produtoEntrada.getValue().equals("Selecione")) {
        entradaSemCadastroProduto();
      } else
        entradaComCadastroProduto();
    }
  }

  private void entradaSemCadastroProduto() {
    Produto produto = start.getProduto(produtoEntrada.getValue().toString());
    produto.setQuantidade(produto.getQuantidade() + Integer.parseInt(quantidadeEntrada.getText()));
    UnidadeFisica unidade = start.getUnidadeFisica(origemEntrada.getValue().toString());
    DataHora data = new DataHora(dataEntrada.getValue(),
        new Hora((byte) Integer.parseInt(horaEntrada.getText()), (byte) Integer.parseInt(minutoEntrada.getText())));
    start.entrada(Integer.parseInt(quantidadeEntrada.getText()), produto, unidade, data);
  }

  private void entradaComCadastroProduto() {
    Categoria categoria = start.getCategoria(categoriaProduto.getValue().toString());
    Produto produto = new Produto(0, nomeProduto.getText(), descricaoProduto.getText(),
        Integer.parseInt(quantidadeEntrada.getText()), Integer.parseInt(quantidadeMinimaProduto.getText()), categoria,
        true);
    UnidadeFisica unidade = start.getUnidadeFisica(origemEntrada.getValue().toString());
    DataHora data = new DataHora(dataEntrada.getValue(),
        new Hora((byte) Integer.parseInt(horaEntrada.getText()), (byte) Integer.parseInt(minutoEntrada.getText())));
    start.entrada(Integer.parseInt(quantidadeEntrada.getText()), produto, unidade, data);
  }
}
