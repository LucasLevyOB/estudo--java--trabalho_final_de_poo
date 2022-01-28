import java.util.Date;

import dataHora.DataHora;
import dataHora.Hora;

public class App {
    public static void main(String[] args) throws Exception {
        DataHora data = new DataHora(new Date(), new Hora((byte) 10, (byte) 45));
        System.out.println(data);
    }
}
