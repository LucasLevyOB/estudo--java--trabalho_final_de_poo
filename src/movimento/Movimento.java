package movimento;

import dataHora.DataHora;

public abstract class Movimento {
  protected DataHora data;
  
  public Movimento(DataHora data) {
    this.data = data;
  }

  public DataHora getData() {
    return data;
  }

}
