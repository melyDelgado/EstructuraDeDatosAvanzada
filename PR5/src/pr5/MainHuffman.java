/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr5;
import java.util.HashMap;
import java.util.Map;

public class MainHuffman {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Uso: java MainHuffman <archivo_huffman.txt>");
            return;
        }

        String archivo = args[0];

        long t1 = System.nanoTime();
        DatosHuffman datos = LectorArchivoHuffman.leerArchivo(archivo);

        NodoHuffman raiz = ArbolHuffman.construirArbol(datos.simbolos, datos.frecuencias);

        Map<String, String> codigos = new HashMap<>();
        ArbolHuffman.generarCodigos(raiz, "", codigos);
        long t2 = System.nanoTime();

        long tiempoArbol = t2 - t1;

        long t3 = System.nanoTime();
        String textoCodificado = CodificadorHuffman.codificar(datos.texto, codigos);
        long t4 = System.nanoTime();

        long tiempoCodificacion = t4 - t3;

        String textoDecodificado = DecodificadorHuffman.decodificar(textoCodificado, raiz);

        // --- SALIDA REQUERIDA ---
        System.out.println(String.join(" ", datos.simbolos));

        for (String s : datos.simbolos) {
            System.out.print(codigos.get(s) + " ");
        }
        System.out.println();

        int bitsCodificado = textoCodificado.length();
        int bitsUTF8 = datos.texto.length() * 8;

        System.out.println(datos.texto.length() + " " + bitsCodificado + " " + bitsUTF8);

        System.out.println("Tiempo árbol (ns): " + tiempoArbol);
        System.out.println("Tiempo codificación (ns): " + tiempoCodificacion);

        System.out.println(textoCodificado);
        System.out.println(textoDecodificado);
    }
}