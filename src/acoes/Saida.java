package acoes;

import dataHora.DataHora;
import movimento.Movimento;
import unidadeFisica.UnidadeFisica;

public class Saida extends Movimento {
  private UnidadeFisica destino;

  public Saida(UnidadeFisica destino, DataHora data) {
    super(data);
    this.destino = destino;
  }

  public String getOrigem() {
    return destino.getNome();
  }

  public String getNomeOrigem() {
    return destino.getNome();
  }

  public String getDataHora() {
    return super.data.toString();
  }

  public String toString() {
    String resultado = destino.getId()
        + "\n" + destino.getNome()
        + "\n" + destino.getEnderecoObj().getCidade()
        + "\n" + destino.getEnderecoObj().getBairro()
        + "\n" + destino.getEnderecoObj().getRua()
        + "\n" + destino.getEnderecoObj().getNumero()
        + "\n" + destino.getStatus()
        + "\n" + super.getData().getData()
        + "\n" + super.getData().getHora().getHoras()
        + "\n" + super.getData().getHora().getMinutos();
    return resultado;
  }
}
