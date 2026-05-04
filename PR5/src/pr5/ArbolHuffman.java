/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ArbolHuffman {

    public static NodoHuffman construirArbol(String[] simbolos, int[] frecuencias) {

        PriorityQueue<NodoHuffman> pq = new PriorityQueue<>();

        for (int i = 0; i < simbolos.length; i++) {
            pq.add(new NodoHuffman(simbolos[i], frecuencias[i]));
        }

        while (pq.size() > 1) {
            NodoHuffman n1 = pq.poll();
            NodoHuffman n2 = pq.poll();

            NodoHuffman nuevo = new NodoHuffman(null, n1.frecuencia + n2.frecuencia);
            nuevo.izq = n1;
            nuevo.der = n2;

            pq.add(nuevo);
        }

        return pq.poll();
    }

    public static void generarCodigos(NodoHuffman nodo, String prefijo, Map<String, String> mapa) {
        if (nodo == null) return;

        if (nodo.esHoja()) {
            mapa.put(nodo.simbolo, prefijo);
            return;
        }

        generarCodigos(nodo.izq, prefijo + "0", mapa);
        generarCodigos(nodo.der, prefijo + "1", mapa);
    }
}