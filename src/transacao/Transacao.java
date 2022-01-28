package transacao;

import movimento.Movimento;
import produto.Produto;

public class Transacao {
  private int id, quantidade;
  private Movimento acao;
  private Produto produto;

  public Transacao(int id, int quantidade, Movimento acao, Produto produto) {
    this.id = id;
    this.quantidade = quantidade;
    this.acao = acao;
    this.produto = produto;
  }
  
}
