package acoes;

import dataHora.DataHora;
import movimento.Movimento;
import unidadeFisica.UnidadeFisica;

public class Saida extends Movimento{
  private UnidadeFisica destino;

  public Saida(int id, UnidadeFisica destino, DataHora data) {
    super(id, data);
    this.destino = destino;
  }

  
}
