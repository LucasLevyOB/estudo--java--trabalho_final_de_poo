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

  public Movimento getAcao() {
    return this.acao;
  }

  public String toString() {
    String resultado = "Produto: [" + produto + "\n]\n Quantidade: " + quantidade + "\n Dados "
        + acao;
    return resultado;
  }
  
}
