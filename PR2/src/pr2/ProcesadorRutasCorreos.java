
package pr2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesadorRutasCorreos {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rutas_semana.txt"))) {
            String linea;
            int dia = 1;

            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                TreeNode raiz = ArmarArbol.armarArbolDesdeString(linea);
                ArbolCorreos arbol = new ArbolCorreos(raiz);

                System.out.println("=== Día " + dia + " ===");
                System.out.println("Iterativa → " + arbol.calcularRutaOptimaIterativa());
                System.out.println("Recursiva → " + arbol.calcularRutaOptimaRecursiva());
                System.out.println();

                dia++;
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}