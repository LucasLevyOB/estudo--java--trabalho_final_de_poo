package controller;

import armazem.Armazem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import produto.Categoria;

public class Categorias {

  @FXML
  private TableView<Categoria> tabela;

  @FXML
  private TableColumn<Categoria, String> colunaNome;

  @FXML
  private TableColumn<Categoria, String> colunaDescricao;

  @FXML
  private TableColumn<Categoria, Node> colunaAcoes;

  @FXML
  private TextField nomeCategoria;

  @FXML
  private TextArea descricaoCategoria;

  Armazem start = Armazem.getInstance();

  public void initialize() {
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    tabela.setItems(listaDeCategorias());
    adicionarBotaoEditar();
    adicionarBotaoDeletar();
  }

  @FXML
  protected void cadastrarCategoria(ActionEvent event) {
    start.adicionarCategoria(nomeCategoria.getText(), descricaoCategoria.getText());
    tabela.setItems(listaDeCategorias());
  }

  @FXML
  protected void limpar(ActionEvent event) {
    nomeCategoria.setText("");
    descricaoCategoria.setText("");
  }

  private void adicionarBotaoEditar() {
    TableColumn<Categoria, Void> colBtn = new TableColumn("Editar");

    Callback<TableColumn<Categoria, Void>, TableCell<Categoria, Void>> cellFactory = new Callback<TableColumn<Categoria, Void>, TableCell<Categoria, Void>>() {
      @Override
      public TableCell<Categoria, Void> call(final TableColumn<Categoria, Void> param) {
        final TableCell<Categoria, Void> cell = new TableCell<Categoria, Void>() {

          private final Button btn = new Button("Editar");

          {
            btn.setOnAction((ActionEvent event) -> {
              Categoria categoria = getTableView().getItems().get(getIndex());
              nomeCategoria.setText(categoria.getNome());
              descricaoCategoria.setText(categoria.getDescricao());
            });
          }

          @Override
          public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(btn);
            }
          }
        };
        return cell;
      }
    };

    colBtn.setCellFactory(cellFactory);

    tabela.getColumns().add(colBtn);

  }

  private void adicionarBotaoDeletar() {
    TableColumn<Categoria, Void> colBtn = new TableColumn("Deletar");

    Callback<TableColumn<Categoria, Void>, TableCell<Categoria, Void>> cellFactory = new Callback<TableColumn<Categoria, Void>, TableCell<Categoria, Void>>() {
      @Override
      public TableCell<Categoria, Void> call(final TableColumn<Categoria, Void> param) {
        final TableCell<Categoria, Void> cell = new TableCell<Categoria, Void>() {

          private final Button btn = new Button("Deletar");

          {
            btn.setOnAction((ActionEvent event) -> {
              Categoria categoria = getTableView().getItems().get(getIndex());
              start.getCategorias().remove(categoria.getNome());
              start.removerCategoria(categoria.getNome());
              tabela.setItems(listaDeCategorias());
            });
          }

          @Override
          public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(btn);
            }
          }
        };
        return cell;
      }
    };

    colBtn.setCellFactory(cellFactory);

    tabela.getColumns().add(colBtn);

  }

  private ObservableList<Categoria> listaDeCategorias() {
    return FXCollections.observableArrayList(
        start.getCategorias().values());
  }
}
