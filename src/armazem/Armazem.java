package armazem;

import java.util.ArrayList;

import produto.Categoria;
import produto.Produto;
import transacao.Transacao;
import unidadeFisica.UnidadeFisica;

public class Armazem {
  ArrayList<Transacao> transacoes;
  ArrayList<Categoria> categorias;
  ArrayList<Produto> produtos;
  ArrayList<UnidadeFisica> unidadesFisicas;

  public Armazem(ArrayList<Transacao> transacoes, ArrayList<Categoria> categorias, ArrayList<Produto> produtos,
      ArrayList<UnidadeFisica> unidadesFisicas) {
    this.transacoes = transacoes;
    this.categorias = categorias;
    this.produtos = produtos;
    this.unidadesFisicas = unidadesFisicas;
  }

  public Armazem() {
    this.transacoes = new ArrayList<Transacao>();
    this.categorias = new ArrayList<Categoria>();
    this.produtos = new ArrayList<Produto>();
    this.unidadesFisicas = new ArrayList<UnidadeFisica>();
  }

  
}
