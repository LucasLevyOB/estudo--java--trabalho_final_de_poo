import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import acoes.Entrada;
import dataHora.DataHora;
import dataHora.Hora;
import movimento.Movimento;
import produto.Categoria;
import produto.Produto;
import transacao.Transacao;
import unidadeFisica.Endereco;
import unidadeFisica.UnidadeFisica;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        // ArrayList transacoes = new ArrayList<Transacao>();
        while (true) {
            // try {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("gerarEntrada")) {
                // Endereco endereco = new Endereco("Canindé", "Alto Guaramiranga", "São José", 450);
                // UnidadeFisica origem = new UnidadeFisica(12, "Mercantil São João", endereco, true);
                // DataHora data = new DataHora(new Date(), new Hora((byte) 13, (byte) 30));
                // Movimento acao = new Entrada(10, origem, data);
                // Produto produto = new Produto(10, "Bolacha Recheada", "Pacote grande com 10 unidades", 130, 10, new Categoria(10, "Alimentos", "Categoria de alimentos"), true);
                // Transacao entrada = new Transacao(14, 130, acao, produto);
                // transacoes.add(entrada);
            } else if (ui[0].equals("gerarSaida")) {
            } else if (ui[0].equals("listarEntradas")) {
            } else if (ui[0].equals("listarSaidas")) {
            } else if (ui[0].equals("listarEntradasSaidas")) {
            } else if (ui[0].equals("listarProdutos")) {
            } else if (ui[0].equals("listarUnidades")) {
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
