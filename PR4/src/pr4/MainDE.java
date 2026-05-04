package pr4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainDE {
    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Uso: java pr4.Main <archivoA> <archivoB>");
            return;
        }

        // Leer archivos
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();

        try (BufferedReader brA = new BufferedReader(new FileReader(args[0]));
             BufferedReader brB = new BufferedReader(new FileReader(args[1]))) {

            String linea;
            while ((linea = brA.readLine()) != null) {
                sbA.append(linea).append("\n");
            }
            while ((linea = brB.readLine()) != null) {
                sbB.append(linea).append("\n");
            }
        }

        String A = sbA.toString();
        String B = sbB.toString();

        System.out.println("Longitud del documento A: " + A.length());
        System.out.println("Longitud del documento B: " + B.length());

        // Versión recursiva simple
        System.out.println("\n--- Versión recursiva sin memoización ---");
        long inicio = System.nanoTime();
        try {
            int dist1 = recursiva(A, B);
            long fin = System.nanoTime();
            double tiempo = (fin - inicio) / 1_000_000_000.0;
            System.out.printf("D(A,B) = %d, D(B,A) = %d, Tiempo = %.4f s%n", dist1, dist1, tiempo);
            if (tiempo > 10)
                System.out.println("Tiempo límite para resolver el problema excedido");
        } catch (StackOverflowError e) {
            System.out.println("Error: recursión demasiado profunda.");
        }

        // Versión recursiva con memoización
        System.out.println("\n--- Versión recursiva con memoización ---");
        inicio = System.nanoTime();
        int dist2 = DistanciaEdición.memoizacion(A, B);
        long fin = System.nanoTime();
        double tiempo2 = (fin - inicio) / 1_000_000_000.0;
        System.out.printf("D(A,B) = %d, D(B,A) = %d, Tiempo = %.4f s%n", dist2, dist2, tiempo2);
        if (tiempo2 > 10)
            System.out.println("Tiempo límite para resolver el problema excedido");

        // Versión iterativa con programación dinámica
        System.out.println("\n--- Versión iterativa con programación dinámica ---");
        inicio = System.nanoTime();
        int dist3 = DistanciaEdición.ProgDin(A, B);
        fin = System.nanoTime();
        double tiempo3 = (fin - inicio) / 1_000_000_000.0;
        System.out.printf("D(A,B) = %d, D(B,A) = %d, Tiempo = %.4f s%n", dist3, dist3, tiempo3);
        if (tiempo3 > 10)
            System.out.println("Tiempo límite para resolver el problema excedido");
    }

    // Versión recursiva sin memoización
    public static int recursiva(String A, String B) {
        return rec(A, B, A.length(), B.length());
    }

    private static int rec(String A, String B, int i, int j) {
        if (i == 0) return j * DistanciaEdición.delta;
        if (j == 0) return i * DistanciaEdición.delta;

        if (A.charAt(i - 1) == B.charAt(j - 1))
            return rec(A, B, i - 1, j - 1);

        int insercion = rec(A, B, i, j - 1) + DistanciaEdición.delta;
        int eliminacion = rec(A, B, i - 1, j) + DistanciaEdición.delta;
        int reemplazo = rec(A, B, i - 1, j - 1) + DistanciaEdición.alfa;

        return Math.min(insercion, Math.min(eliminacion, reemplazo));
    }
}