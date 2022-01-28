package unidadeFisica;

public class UnidadeFisica {
  int id;
  String nome;
  Endereco endereco;
  Boolean status;

  public UnidadeFisica(int id, String nome, Endereco endereco, Boolean status) {
    this.id = id;
    
    this.nome = nome;
    this.endereco = endereco;
    this.status = status;
  }

  public int getId() {
    return this.id;
  }

  public String toString() {
    return "Nome: " + nome + "\n Status: " + status + "\n Endereço: " + endereco;
  }
}
