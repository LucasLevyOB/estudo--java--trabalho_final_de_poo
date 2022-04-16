package transacao;

public class TransacaoException extends RuntimeException{
  public TransacaoException(String mensagem) {
    super(mensagem);
  }
}
