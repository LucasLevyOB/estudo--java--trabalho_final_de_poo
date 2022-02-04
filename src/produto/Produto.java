package produto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Produto {
  private int id, quantidadeEmEstoque, quantidadeMinima;
  private String nome;
  private String descricao;
  private Categoria categoria;
  private Boolean status;

  public Produto(int id, String nome, String descricao, int quantidadeEmEstoque, int quantidadeMinima,
      Categoria categoria, Boolean status) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.quantidadeEmEstoque = quantidadeEmEstoque;
    this.quantidadeMinima = quantidadeMinima;
    this.categoria = categoria;
    this.status = status;
  }

  public void setQuantidade(int quantidade) {
    if (quantidade >= 0)
      this.quantidadeEmEstoque = quantidade;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public int getQuantidade() {
    return quantidadeEmEstoque;
  }

  public int getQuantidadeMinima() {
    return quantidadeMinima;
  }

  public String getNome() {
    return nome;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getDescricao() {
    return descricao;
  }

  public int getQuantidadeEmEstoque() {
    return quantidadeEmEstoque;
  }

  public Boolean getStatus() {
    return status;
  }

  public static void cadastrarUnidades(Collection<Produto> lista) {
    try {
      FileWriter arq = new FileWriter("db/produtos.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      for (Produto produto : lista) {
        gravarArq.println(produto.getId());
        gravarArq.println(produto.getNome());
        gravarArq.println(produto.getDescricao());
        gravarArq.println(produto.getQuantidadeEmEstoque());
        gravarArq.println(produto.getQuantidadeMinima());
        gravarArq.println(produto.getCategoria().getId());
        gravarArq.println(produto.getCategoria().getNome());
        gravarArq.println(produto.getCategoria().getDescricao());
        gravarArq.println(produto.getStatus());
      }
      arq.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um problema ao escrever o arquivo");
    }
  }

  public static Map<String, Produto> lerUnidades() {
    Map<String, Produto> resultado = new HashMap<String, Produto>();
    try {
      FileReader arquivo = new FileReader("db/produtos.txt");
      BufferedReader stream = new BufferedReader(arquivo);

      String tempReg = stream.readLine();
      String nome;
      while (tempReg != null) {
        nome = stream.readLine();
        resultado.put(nome,
            new Produto(Integer.parseInt(tempReg), nome, stream.readLine(), Integer.parseInt(stream.readLine()),
                Integer.parseInt(stream.readLine()),
                new Categoria(Integer.parseInt(stream.readLine()), stream.readLine(), stream.readLine()),
                Boolean.parseBoolean(stream.readLine())));
        tempReg = stream.readLine();
      }
      stream.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  public static ArrayList<String> exportar() {
    ArrayList<String> resultado = new ArrayList<String>();
    try {
      FileReader arquivo = new FileReader("db/produtos.txt");
      BufferedReader stream = new BufferedReader(arquivo);

      String tempReg = stream.readLine();
      while (tempReg != null) {
        resultado.add(tempReg + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|"
            + stream.readLine() + "|");
        tempReg = stream.readLine();
      }
      stream.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  public String toString() {
    return categoria.getNome()
        + "\n" + categoria.getDescricao()
        + "\n" + nome
        + "\n" + descricao
        + "\n" + quantidadeEmEstoque
        + "\n" + quantidadeMinima
        + "\n" + status;
  }

}
