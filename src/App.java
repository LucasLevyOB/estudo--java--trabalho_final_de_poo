import java.lang.System.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application {

  @FXML
  public Stage stage;

  @FXML
  private TextField usuario;

  @FXML
  private TextField senha;
  
  @FXML
  private Label mensagem;

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
    Parent login = FXMLLoader.load(getClass().getResource("view/login.fxml"));
    Scene scene = new Scene(login);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Sistema de Estoque");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  @FXML
  void entar(ActionEvent event) throws Exception{
    if (usuario.getText().equals("Root") && senha.getText().equals("12345")) {
      Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    mensagem.setText("Erro ao entrar, lembre do seu login e senha.");
  }
}
