package produto;

public class Produto {
  private int id, quantidadeEmEstoque, quantidadeMinima;
  private String nome, descricao;
  private Categoria categoria;
  private Boolean status;

  public Produto(int id, String nome, String descricao, int quantidadeEmEstoque, int quantidadeMinima, Categoria categoria, Boolean status) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.quantidadeEmEstoque = quantidadeEmEstoque;
    this.quantidadeMinima = quantidadeMinima;
    this.categoria = categoria;
    this.status = status;
  }
  
  public String toString() {
    return "\n\tNome: " + nome + "\n\tDescrição: " + descricao + "\n\tCategoria: " + categoria.getNome() + "\n\tQuantidade: " + quantidadeEmEstoque + "\n\tquantidade Mínima: " + quantidadeMinima + ", Ativo:" + (status ? "sim" : "não");
  }

  
}
