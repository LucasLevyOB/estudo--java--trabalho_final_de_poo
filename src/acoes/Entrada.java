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

  public String toString() {
    String resultado = "de Origem: [" + origem + "\n\t Data: " + super.data + "\n]";
    return resultado; 
  }
}
