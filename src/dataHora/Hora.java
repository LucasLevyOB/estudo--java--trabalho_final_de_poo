package dataHora;

public class Hora {
  private byte horas, minutos;

  private boolean verificaMinutos(byte minutos) {
    if (minutos >= 0 && minutos < 60)
      return true;
    return false;
  }

  private boolean verificaHoras(byte horas) {
    if (horas >= 0 && horas < 24)
      return true;
    return false;
  }

  /**
   * Verifica se um horário no formato 24 horas está bem formado
   * @param horas
   * @param minutos
   * @return
   */
  private boolean verificaFormato(byte horas, byte minutos) {
    if (verificaHoras(horas) && verificaMinutos(minutos))
      return true;
    return false;
  }

  /**
   * Inicializa um objeto, antes chama a função de verificação de formato
   * @param horas
   * @param minutos
   */
  public Hora(byte horas, byte minutos) {
    if (verificaFormato(horas, minutos)) {
      this.horas = horas;
      this.minutos = minutos;
    } else {
      this.horas = 9;
      this.minutos = 0;
    }
  }

  public String toString() {
    return this.horas + ":" + this.minutos;
  }

}
