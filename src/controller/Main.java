package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class Main {
  @FXML
  private ScrollPane main;
  @FXML
  private Button cadastrarSaidas;

  public void initialize() throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/cadastrarEntradas.fxml")));
  }

  @FXML
  void navegarParaCadastrarEntradas(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/cadastrarEntradas.fxml")));
  }

  @FXML
  void navegarParaListarEntradas(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/listarEntradas.fxml")));
  }

  @FXML
  void navegarParaCadastrarSaidas(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/cadastrarSaidas.fxml")));
  }

  @FXML
  void navegarParaListarSaidas(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/listarSaidas.fxml")));
  }

  @FXML
  void navegarParaListarProdutos(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/listarProdutos.fxml")));
  }

  @FXML
  void navegarParaUnidadesFisicas(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/unidadesFisicas.fxml")));
  }

  @FXML
  void navegarParaCategorias(ActionEvent event) throws Exception {
    main.setContent(FXMLLoader.load(getClass().getResource("../view/categorias.fxml")));
  }
}
