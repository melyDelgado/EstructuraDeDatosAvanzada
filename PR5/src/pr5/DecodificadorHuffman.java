/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr5;

public class DecodificadorHuffman {

    public static String decodificar(String codificado, NodoHuffman raiz) {

        StringBuilder resultado = new StringBuilder();
        NodoHuffman actual = raiz;

        for (char bit : codificado.toCharArray()) {
            actual = (bit == '0') ? actual.izq : actual.der;

            if (actual.esHoja()) {
                resultado.append(actual.simbolo);
                actual = raiz;
            }
        }

        return resultado.toString();
    }
}