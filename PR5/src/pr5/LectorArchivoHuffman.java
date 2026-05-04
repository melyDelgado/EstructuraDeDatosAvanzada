/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorArchivoHuffman {

    public static DatosHuffman leerArchivo(String ruta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String[] simbolos = br.readLine().trim().split(" ");
            String[] fStr = br.readLine().trim().split(" ");

            int[] frecuencias = new int[fStr.length];
            for (int i = 0; i < fStr.length; i++) {
                frecuencias[i] = Integer.parseInt(fStr[i]);
            }

            String texto = br.readLine().trim();

            return new DatosHuffman(simbolos, frecuencias, texto);
        }
    }
}