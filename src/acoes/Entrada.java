package acoes;

import dataHora.DataHora;
import movimento.Movimento;
import unidadeFisica.UnidadeFisica;

public class Entrada extends Movimento{
  private UnidadeFisica origem;

  public Entrada(int id, UnidadeFisica origem, DataHora data) {
    super(id, data);
    this.origem = origem;
  }

  
}
