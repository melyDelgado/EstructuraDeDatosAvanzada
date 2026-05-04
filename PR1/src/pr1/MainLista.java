package pr1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class MainLista {
    public static void main(String[] args) {
        File file = new File("datos.txt");
        PacienteLista pacContenedor = new PacienteLista("contenedor", 0, new double[10]);

        try {
            Scanner scanner = new Scanner(file);

            // Ignorar primera línea si tiene número de pacientes
            if (scanner.hasNextInt()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] partes = line.split(" ");
                String nombreCompleto = partes[0] + " " + partes[1];
                int edad = Integer.parseInt(partes[2]);

                double[] datos = new double[10];
                for (int i = 0; i < 10; i++) {
                    datos[i] = Double.parseDouble(partes[3 + i]);
                }

                pacContenedor.ingresarPaciente(nombreCompleto, edad, datos);
            }
            scanner.close();
            // Contar registros iguales usando el método
            int iguales = pacContenedor.registrosIguales();
            System.out.println("Número de registros iguales: " + iguales);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Buscar duplicados y mostrarlos
        PacienteLista[] todos = pacContenedor.getPacientes();
        System.out.println("Pacientes duplicados encontrados:");
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) break;
            for (int j = i + 1; j < todos.length; j++) {
                if (todos[j] == null) 
                    break;
                if (todos[i].equals(todos[j])) {
                    System.out.println("Duplicado: " + todos[i]);
                }
            }
        }
    }

   
}
   