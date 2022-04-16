package produto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Categoria implements Comparable<Categoria> {
  private int id;
  private String nome, descricao;

  public Categoria(int id, String nome, String descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  public static void cadastrarCategoria(Collection<Categoria> lista) {
    try {
      FileWriter arq = new FileWriter("db/categorias.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      for (Categoria categoria : lista) {
        gravarArq.println(categoria.getNome());
        gravarArq.println(categoria.getId());
        gravarArq.println(categoria.getDescricao());
      }
      arq.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um problema ao escrever o arquivo");
    }
  }

  public static Map<String, Categoria> lerCategorias() {
    Map<String, Categoria> resultado = new HashMap<>();
    try {
      FileReader arquivoCategorias = new FileReader("db/categorias.txt");
      BufferedReader categoriasStream = new BufferedReader(arquivoCategorias);

      String tempReg = categoriasStream.readLine();
      while (tempReg != null) {
        resultado.put(tempReg,
            new Categoria(Integer.parseInt(categoriasStream.readLine()), tempReg, categoriasStream.readLine()));

        tempReg = categoriasStream.readLine();
      }
      categoriasStream.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  @Override
  public int compareTo(Categoria cat) {
    return Integer.compare(this.id, cat.getId());
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public String toString() {
    return "Categoria: " + nome + "\nDescricao: " + descricao;
  }
}
