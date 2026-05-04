import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author melydelgado
 */
public class MainHamPD {

    public static void main(String[] args) {

        String archivo = "datos.txt"; // Archivo de entrada con los valores t, m y n

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");

                if (partes.length != 3) {
                    System.out.println("Línea inválida: " + linea);
                    continue;
                }

                int t = Integer.parseInt(partes[0]);
                int m = Integer.parseInt(partes[1]);
                int n = Integer.parseInt(partes[2]);

                System.out.println("\nCaso: t = " + t + " | m = " + m + " | n = " + n);

                // Inicia el temporizador
                long inicio = System.currentTimeMillis();

                // Llamamos a la clase HamburguesasPD
                HamburguesasPD.resultadoHamburguesas(t, m, n);

                // Termina el temporizador
                long fin = System.currentTimeMillis();
                double tiempoSegundos = (fin - inicio) / 1000.0;

                // Verificamos si tardó menos o más de 3 segundos
                if (tiempoSegundos < 3) {
                    System.out.println("Tiempo de ejecución: " + tiempoSegundos + " segundos");
                } else {
                    System.out.println("Tiempo límite para resolver el problema excedido.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}