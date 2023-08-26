import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Histograma {

    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> histograma = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("divina_comedia_sp.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                for (String palabra : linea.split(" ")) {
                    int longitud = palabra.length();
                    if (longitud >= 2 && longitud <= 10) {
                        histograma.put(longitud, histograma.getOrDefault(longitud, 0) + 1);
                    }
                }
            }
        }
        System.out.println("~Tabla de frecuencias~");

        System.out.println("Longitud | Total");
        for (int i = 2; i <= 10; i++) {
            System.out.printf("%4d   |   %4d\n", i, histograma.getOrDefault(i, 0));
        }
    }
}