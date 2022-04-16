package transacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import acoes.Entrada;
import acoes.Saida;
import dataHora.DataHora;
import dataHora.Hora;
import movimento.Movimento;
import produto.Categoria;
import produto.Produto;
import unidadeFisica.Endereco;
import unidadeFisica.UnidadeFisica;

public class Transacao implements Comparable<Transacao>{
  private int id, quantidade;
  private Movimento acao;
  private Produto produto;

  public Transacao(int id, int quantidade, Movimento acao, Produto produto) {
    this.id = id;
    this.quantidade = quantidade;
    this.acao = acao;
    this.produto = produto;
  }

  public static void writeList(ArrayList<String> lista) {
    try {
      FileWriter arq = new FileWriter("db/transacoes.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      for (String value : lista)
        gravarArq.println(value);
      // for(Transacao value : transacoes){
      // gravarArq.println(value.getProduto());
      // gravarArq.println(value.getQuantidade());
      // gravarArq.println(value.getAcao());
      // }
      arq.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um problema ao escrever o arquivo");
    }
  }

  public static ArrayList<String> readList() {
    ArrayList<String> resultado = new ArrayList<String>();
    try {
      FileReader carFile = new FileReader("db/transacoes.txt");
      BufferedReader carStream = new BufferedReader(carFile);

      String tempReg = carStream.readLine();

      while (tempReg != null) { // a null string indicates end of file
        resultado.add(tempReg);
        tempReg = carStream.readLine();
      }
      carFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  public static HashMap<Integer, Transacao> buscar() {
    HashMap<Integer, Transacao> resultado = new HashMap<Integer, Transacao>();
    try {
      FileReader arquivo = new FileReader("db/transacoes.txt");
      BufferedReader stream = new BufferedReader(arquivo);

      String tempReg = stream.readLine();
      String id;
      String quantidade;
      while (tempReg != null) { // a null string indicates end of file
        id = stream.readLine();
        quantidade = stream.readLine();
        Produto produto = new Produto(Integer.parseInt(stream.readLine()), stream.readLine(), stream.readLine(),
            Integer.parseInt(stream.readLine()), Integer.parseInt(stream.readLine()),
            new Categoria(Integer.parseInt(stream.readLine()), stream.readLine(), stream.readLine()),
            Boolean.parseBoolean(stream.readLine()));
        UnidadeFisica unidade = new UnidadeFisica(Integer.parseInt(stream.readLine()), stream.readLine(),
            new Endereco(stream.readLine(), stream.readLine(), stream.readLine(), Integer.parseInt(stream.readLine())),
            Boolean.parseBoolean(stream.readLine()));
        DataHora data = new DataHora(LocalDate.parse(stream.readLine()),
            new Hora((byte) Integer.parseInt(stream.readLine()), (byte) Integer.parseInt(stream.readLine())));
        Movimento movimento;
        if (tempReg.equals("Entrada")) {
          movimento = new Entrada(unidade, data);
        } else {
          movimento = new Saida(unidade, data);
        }
        Transacao transacao = new Transacao(Integer.parseInt(id), Integer.parseInt(quantidade), movimento,
            produto);
        resultado.put(Integer.parseInt(id), transacao);
        tempReg = stream.readLine();
      }
      arquivo.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  public static ArrayList<String> lerEspecifico(String tipo) {
    ArrayList<String> resultado = new ArrayList<String>();
    try {
      FileReader arquivoTransacoes = new FileReader("db/transacoes.txt");
      BufferedReader transacoesStream = new BufferedReader(arquivoTransacoes);

      String tempReg = transacoesStream.readLine();

      while (tempReg != null) {
        if (tempReg.equals(tipo)) {
          String linha = transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + "|"
              + transacoesStream.readLine() + " - "
              + transacoesStream.readLine() + ":"
              + transacoesStream.readLine() + "|";
          resultado.add(linha);
        } else {
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
          transacoesStream.readLine();
        }
        tempReg = transacoesStream.readLine();
      }
      transacoesStream.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
    return resultado;
  }

  public int getId() {
    return this.id;
  }

  public Movimento getAcao() {
    return this.acao;
  }

  public String getData() {
    return acao.getData().toString();
  }

  public Entrada getEntrada() {
    Entrada entrada = (Entrada) acao;
    return entrada;
  }

  public Saida getSaida() {
    Saida entrada = (Saida) acao;
    return entrada;
  }

  public Produto getProduto() {
    return produto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  @Override
  public int compareTo(Transacao tran) {
    return Integer.compare(this.id, tran.getId());
  }

  public String toString() {
    String resultado = "Produto: [" + produto + "\n]\n Quantidade: " + quantidade + "\n Dados "
        + acao;
    return resultado;
  }

}
