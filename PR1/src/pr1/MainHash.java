package pr1;

import java.io.*;
import java.util.*;

public class MainHash {


    public static void main(String[] args) throws IOException {
        //Usamos BufferedReader para leer el archivo
        BufferedReader br = new BufferedReader(new FileReader("datos.txt"));

        // Leemos la primera línea (número de pacientes)
        int n = Integer.parseInt(br.readLine().trim());

        // Usamos un HashMap para contar pacientes idénticos
        HashMap<PacienteHash, Integer> hospital = new HashMap<>();

        // Medimos tiempo de inicio
        long inicio = System.nanoTime();

        for (int i = 0; i < n; i++) {
            // Leemos la línea de los estudios
            String[] linea = br.readLine().trim().split("\\s+");
            int[] estudios = new int[10];
            for (int j = 0; j < 10; j++) {
                estudios[j] = Integer.parseInt(linea[j]);
            }

            // Creamos un paciente
            PacienteHash p = new PacienteHash("Paciente" + i, 30, estudios);

            // Contamos cuántos pacientes iguales existen
            hospital.put(p, hospital.getOrDefault(p, 0) + 1);
        }

        br.close(); // cerramos el archivo

        // Buscamos el máximo de pacientes idénticos
        int maxIdenticos = 0;
        for (int count : hospital.values()) {
            if (count > maxIdenticos) {
                maxIdenticos = count;
            }
        }

        // Medimos tiempo de fin
        long fin = System.nanoTime();
        double tiempoSegundos = (fin - inicio) / 1e9;

        // Mostramos resultados
        if (maxIdenticos <= 1) {
            System.out.println("no hay dos pacientes con registros idénticos");
        } else {
            System.out.println("se encontraron " + maxIdenticos + " pacientes idénticos");
        }

        System.out.printf("Tiempo de ejecución: %.6f segundos%n", tiempoSegundos);

    }
    
}
