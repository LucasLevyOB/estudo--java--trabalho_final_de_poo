package acoes;

import dataHora.DataHora;
import movimento.Movimento;
import unidadeFisica.UnidadeFisica;

public class Entrada extends Movimento {
  private UnidadeFisica origem;

  public Entrada(UnidadeFisica origem, DataHora data) {
    super(data);
    this.origem = origem;
  }

  public String getOrigem() {
    return origem.getNome();
  }

  public String getNomeOrigem() {
    return origem.getNome();
  }

  public String getDataHora() {
    return super.data.toString();
  }

  public String toString() {
    return origem.getId()
        + "\n" + origem.getNome()
        + "\n" + origem.getEnderecoObj().getCidade()
        + "\n" + origem.getEnderecoObj().getBairro()
        + "\n" + origem.getEnderecoObj().getRua()
        + "\n" + origem.getEnderecoObj().getNumero()
        + "\n" + origem.getStatus()
        + "\n" + super.getData().getData()
        + "\n" + super.getData().getHora().getHoras()
        + "\n" + super.getData().getHora().getMinutos();
  }
}
