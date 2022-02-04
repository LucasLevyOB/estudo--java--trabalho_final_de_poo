package dataHora;

import java.time.LocalDate;

public class DataHora {
  private LocalDate data;
  private Hora hora;
  

  public DataHora(LocalDate data, Hora hora) {
    this.data = data;
    this.hora = hora;
  }

  public LocalDate getData() {
    return data;
  }

  public Hora getHora() {
    return hora;
  }

  public String toString() {
    // SimpleDateFormat formatadorData = new SimpleDateFormat("dd-MM-yyyy");
    // return formatadorData.format(this.data) + " " + this.hora;
    return this.data + " " + this.hora;
  }

}
