package unidadeFisica;

public class Endereco {
  private String cidade, bairro, rua;
  private int numero;

  public Endereco(String cidade, String bairro, String rua, int numero) {
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
    this.numero = numero;
    
  }

  

  public String getCidade() {
    return cidade;
  }

  public String getBairro() {
    return bairro;
  }

  public String getRua() {
    return rua;
  }

  public int getNumero() {
    return numero;
  }

  public String toString() {
    return cidade + ", " + bairro + ", " + rua + ", " + numero;
  }

  


}
