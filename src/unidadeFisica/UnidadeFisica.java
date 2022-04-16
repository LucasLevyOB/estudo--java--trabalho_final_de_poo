package unidadeFisica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnidadeFisica implements Comparable<UnidadeFisica>{
  private int id;
  private String nome;
  private Endereco endereco;
  private Boolean status;

  public UnidadeFisica(int id, String nome, Endereco endereco, Boolean status) {
    this.id = id;
    this.nome = nome;
    this.endereco = endereco;
    this.status = status;
  }

  public static void cadastrarUnidades(Collection<UnidadeFisica> lista) {
    try {
      FileWriter arq = new FileWriter("db/unidadesFisicas.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      for (UnidadeFisica unidade : lista) {
        gravarArq.println(unidade.getNome());
        gravarArq.println(unidade.getId());
        gravarArq.println(unidade.getEnderecoObj().getCidade());
        gravarArq.println(unidade.getEnderecoObj().getBairro());
        gravarArq.println(unidade.getEnderecoObj().getRua());
        gravarArq.println(unidade.getEnderecoObj().getNumero());
        gravarArq.println(unidade.getStatus());
      }
      arq.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um problema ao escrever o arquivo");
    }
  }

  public static Map<String, UnidadeFisica> lerUnidades() {
    Map<String, UnidadeFisica> resultado = new HashMap<String, UnidadeFisica>();
    try {
      FileReader arquivo = new FileReader("db/unidadesFisicas.txt");
      BufferedReader stream = new BufferedReader(arquivo);

      String tempReg = stream.readLine();
      while (tempReg != null) {
        resultado.put(tempReg,
            new UnidadeFisica(Integer.parseInt(stream.readLine()), tempReg, new Endereco(stream.readLine(),
                stream.readLine(), stream.readLine(), Integer.parseInt(stream.readLine())),
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
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return this.id;
  }

  public String getNome() {
    return nome;
  }

  public String getEndereco() {
    return endereco.toString();
  }

  public Endereco getEnderecoObj() {
    return endereco;
  }

  public Boolean getStatus() {
    return status;
  }

  @Override
  public int compareTo(UnidadeFisica uni) {
    return Integer.compare(this.id, uni.getId());
  }

  public String toString() {
    return "\n\tNome: " + nome + "\n\t Unidade Ativa: " + (status ? "sim" : "não") + "\n\t Endereço: " + endereco;
  }
}
