package dataHora;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHora {
  private Date data;
  private Hora hora;
  

  public DataHora(Date data, Hora hora) {
    this.data = data;
    this.hora = hora;
  }

  public String toString() {
    SimpleDateFormat formatadorData = new SimpleDateFormat("dd-MM-yyyy");
    return formatadorData.format(this.data) + " " + this.hora;
  }

}
