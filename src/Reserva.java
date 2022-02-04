import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import dataHora.DataHora;
import dataHora.Hora;
import produto.Categoria;
import produto.Produto;
import unidadeFisica.Endereco;
import unidadeFisica.UnidadeFisica;

public class Reserva {
  public static void main(String[] args) throws Exception {
    Locale.setDefault(Locale.US);
    Scanner scanner = new Scanner(System.in);
    // ArrayList transacoes = new ArrayList<Transacao>();
    // Armazem armazem = new Armazem();
    while (true) {
    // try {
    String line = scanner.nextLine();
    System.out.println("$" + line);
    String ui[] = line.split(" ");
    if (line.equals("end")) {
    break;
    } else if (ui[0].equals("gerarEntrada")) {
    Endereco endereco = new Endereco("Canindé", "Alto Guaramiranga", "São José",
    450);
    UnidadeFisica origem = new UnidadeFisica(12, "Mercantil São João", endereco,
    true);
    DataHora data = new DataHora(LocalDate.of(2020, 2, 23), new Hora((byte) 13, (byte) 30));
    Produto produto = new Produto(10, "Bolacha Recheada", "Pacote grande com 10 unidades", 130, 10, new Categoria(10, "Alimentos", "Categoria de alimentos"),
    true);
    // armazem.entrada(produto, origem, data);
    } else if (ui[0].toLowerCase().equals("gerarsaida")) {
    } else if (ui[0].toLowerCase().equals("listarentradas")) {
    // System.out.println(armazem.listarEntradas());
    } else if (ui[0].toLowerCase().equals("listarsaidas")) {
    // System.out.println(armazem.listarSaidas());
    } else if (ui[0].toLowerCase().equals("listartodastransacoes")) {
    // System.out.println(armazem.listarTodasTransacoes());
    } else if (ui[0].toLowerCase().equals("listarprodutos")) {
    // System.out.println(armazem.listarProdutos());
    } else if (ui[0].toLowerCase().equals("listarunidades")) {
    // System.out.println(armazem.listarUnidadesFisicas());
    } else if (ui[0].toLowerCase().equals("ajuda")) {
    System.out.println("Lista de Comandos:");
    } else {
    System.out.println("fail: comando invalido");
    }
    // } catch (AccountException ae) {
    // System.out.println(ae.getMessage());
    // }
    }
    scanner.close();
  }
}
