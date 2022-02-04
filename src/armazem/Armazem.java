package armazem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import acoes.Entrada;
import acoes.Saida;
import dataHora.DataHora;
import movimento.Movimento;
import produto.Categoria;
import produto.Produto;
import transacao.Transacao;
import unidadeFisica.UnidadeFisica;

public class Armazem {
  private int idProximoProduto, idProximaCategoria, idProximaTransacao, idProximaUnidadeFisica;
  private Map<Integer, Transacao> transacoes;
  private Map<String, Categoria> categorias;
  private Map<String, Produto> produtos;
  private Map<String, UnidadeFisica> unidadesFisicas;
  private static Armazem instance;

  private Armazem() {
    this.transacoes = Transacao.buscar();
    this.categorias = Categoria.lerCategorias();
    this.produtos = Produto.lerUnidades();
    this.unidadesFisicas = UnidadeFisica.lerUnidades();
    listarIds();
  }

  public static Armazem getInstance() {
    if (instance == null) {
      instance = new Armazem();
    }
    return instance;
  }

  public void entrada(int quantidade, Produto produto, UnidadeFisica unidade, DataHora data) {
    produto.setId(this.idProximoProduto);
    produto.setStatus(true);
    Movimento acao = new Entrada(unidade, data);
    Transacao entrada = new Transacao(idProximaTransacao, quantidade, acao, produto);
    this.transacoes.put(this.idProximaTransacao, entrada);
    adicionarProduto(produto);
    this.idProximaTransacao++;
    salvarTransacao(entrada);
    atuliazarIds();
  }
  
  public void adicionarProduto(Produto produto) {
    this.produtos.put(produto.getNome(), produto);
    this.idProximoProduto++;
    Produto.cadastrarUnidades(produtos.values());
    atuliazarIds();
  }

  // esse método organiza os valores da transacao, para serem salvos no arquivo txt
  private void salvarTransacao(Transacao t) {
    ArrayList<String> lista = Transacao.readList();
    if (t.getAcao() instanceof Entrada)
      lista.add("Entrada");
    else
    lista.add("Saida");
    lista.add(t.getId() + "");
    lista.add(t.getQuantidade() + "");
    lista.add(t.getProduto().getId() + "");
    lista.add(t.getProduto().getNome());
    lista.add(t.getProduto().getDescricao());
    lista.add(t.getProduto().getQuantidade() + "");
    lista.add(t.getProduto().getQuantidadeMinima() + "");
    lista.add(t.getProduto().getCategoria().getId() + "");
    lista.add(t.getProduto().getCategoria().getNome());
    lista.add(t.getProduto().getCategoria().getDescricao());
    lista.add(t.getProduto().getStatus()+"");
    if (t.getAcao() instanceof Entrada)
      lista.add(t.getEntrada().toString());
    else
      lista.add(t.getSaida().toString());
    Transacao.writeList(lista);
  }

  public void saida(String key, int quantidade, UnidadeFisica unidade, DataHora data) {
    if (this.produtos.get(key).getQuantidade() - quantidade >= 0) {
      Movimento acao = new Saida(unidade, data);
      Transacao saida = new Transacao(idProximaTransacao, quantidade, acao, this.produtos.get(key));
      this.transacoes.put(this.idProximaTransacao, saida);
      int novaQuantidade = this.produtos.get(key).getQuantidade() - quantidade;
      this.produtos.get(key).setQuantidade(novaQuantidade);
      if (this.produtos.get(key).getQuantidade() == 0)
        this.produtos.get(key).setStatus(false);
      Produto.cadastrarUnidades(produtos.values());
      this.idProximaTransacao++;
      salvarTransacao(saida);
      atuliazarIds();
    } else {
      // mensagem avisando que nao ha quantidade suficiente
    }
  }

  public ArrayList<Transacao> listarEntradas() {
    ArrayList<Transacao> resultadoEntrada = new ArrayList<Transacao>();
    for (Transacao value : this.transacoes.values())
      if (value.getAcao() instanceof Entrada)
        resultadoEntrada.add(value);
    return resultadoEntrada;
  }

  public ArrayList<Transacao> listarSaidas() {
    ArrayList<Transacao> resultadoSaida = new ArrayList<Transacao>();
    for (Transacao value : this.transacoes.values())
      if (value.getAcao() instanceof Saida) {
        resultadoSaida.add(value);
      }
    return resultadoSaida;
  }

  public void adicionarUnidadeFisica(UnidadeFisica unidadeFisica) {
    unidadeFisica.setId(idProximaUnidadeFisica);
    unidadesFisicas.put(unidadeFisica.getNome(), unidadeFisica);
    UnidadeFisica.cadastrarUnidades(unidadesFisicas.values());
    this.idProximaUnidadeFisica++;
    atuliazarIds();
  }

  public void adicionarCategoria(String nome, String descricao) {
    this.categorias.put(nome, new Categoria(idProximaCategoria, nome, descricao));
    Categoria.cadastrarCategoria(categorias.values());
    idProximaCategoria++;
    atuliazarIds();
  }

  public void removerCategoria(String chave) {
    this.categorias.remove(chave);
    Categoria.cadastrarCategoria(categorias.values());
  }

  private void listarIds() {
    try {
      FileReader arquivoIds = new FileReader("db/ids.txt");
      BufferedReader idsStream = new BufferedReader(arquivoIds);
      idProximoProduto = Integer.parseInt(idsStream.readLine());
      idProximaCategoria = Integer.parseInt(idsStream.readLine());
      idProximaTransacao = Integer.parseInt(idsStream.readLine());
      idProximaUnidadeFisica = Integer.parseInt(idsStream.readLine());
      idsStream.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nerror: No file was read");
    } catch (IOException e) {
      System.out.println("\nerror: There was a problem reading the file");
    }
  }
  
  public void atuliazarIds() {
    try {
      FileWriter arq = new FileWriter("db/ids.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      gravarArq.println(idProximoProduto);
      gravarArq.println(idProximaCategoria);
      gravarArq.println(idProximaTransacao);
      gravarArq.println(idProximaUnidadeFisica);
      arq.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um problema ao escrever o arquivo");
    }
  }

  public Map<String, Produto> getProdutos() {
    return produtos;
  }

  public Map<String, Categoria> getCategorias() {
    return categorias;
  }

  public Map<String, UnidadeFisica> getUnidades() {
    return unidadesFisicas;
  }

  public Map<Integer, Transacao> getTrasacoes() {
    return transacoes;
  }

  public Produto getProduto(String chave) {
    return produtos.get(chave);
  }

  public Categoria getCategoria(String chave) {
    return categorias.get(chave);
  }

  public UnidadeFisica getUnidadeFisica(String chave) {
    return unidadesFisicas.get(chave);
  }
}
