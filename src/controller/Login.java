package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {
  @FXML
  private TextField usuario;

  @FXML
  private TextField senha;
  
  @FXML
  private Label mensagem;

  public void initialize() throws Exception {
    
  }

  @FXML
  void entar(ActionEvent event) throws Exception{
    if (usuario.getText().equals("Root") && senha.getText().equals("12345")) {
      Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    mensagem.setText("Erro ao entrar, lembre do seu login e senha.");
  }
}
