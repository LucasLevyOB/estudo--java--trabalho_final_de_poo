package armazem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acoes.Entrada;
import dataHora.DataHora;
import movimento.Movimento;
import produto.Categoria;
import produto.Produto;
import transacao.Transacao;
import unidadeFisica.UnidadeFisica;

public class Armazem {
  private int idProximoProduto, idProximaCategoria, idProximaTransacao, idProximaUnidadeFisica;
  private Map<Integer, Transacao> transacoes;
  private Map<Integer, Categoria> categorias;
  private Map<Integer, Produto> produtos;
  private Map<Integer, UnidadeFisica> unidadesFisicas;

  // public Armazem(ArrayList<Transacao> transacoes, ArrayList<Categoria> categorias, ArrayList<Produto> produtos,
  //     ArrayList<UnidadeFisica> unidadesFisicas) {
  //   this.transacoes = transacoes;
  //   this.categorias = categorias;
  //   this.produtos = produtos;
  //   this.unidadesFisicas = unidadesFisicas;
  // }

  public Armazem() {
    this.idProximoProduto = 0;
    this.idProximaCategoria = 0;
    this.idProximaTransacao = 0;
    this.idProximaUnidadeFisica = 0;
    this.transacoes = new HashMap<Integer, Transacao>();
    this.categorias = new HashMap<Integer, Categoria>();
    this.produtos = new HashMap<Integer, Produto>();
    this.unidadesFisicas = new HashMap<Integer, UnidadeFisica>();
  }

  public void entrada(Produto produto, UnidadeFisica unidade, DataHora data) {
    Movimento acao = new Entrada(10, unidade, data);
    Transacao entrada = new Transacao(idProximaTransacao, 100, acao, produto);
    this.transacoes.put(this.idProximaTransacao, entrada);
    this.produtos.put(this.idProximoProduto, produto);
    this.idProximaTransacao++;
    this.idProximoProduto++;
  }

  public String listarEntradas() {
    String resultado = "";
    for (Object key : this.transacoes.keySet())
      if (transacoes.get(key).getAcao() instanceof Entrada)
        resultado += transacoes.get(key) + "\n";
    return resultado;
  }
}
