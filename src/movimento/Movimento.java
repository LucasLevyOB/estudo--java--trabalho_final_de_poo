package movimento;

import dataHora.DataHora;

public abstract class Movimento {
  private int id;
  private DataHora data;
  
  public Movimento(int id, DataHora data) {
    this.id = id;
    this.data = data;
  }

}
