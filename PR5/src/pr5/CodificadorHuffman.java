/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr5;

import java.util.Map;

public class CodificadorHuffman {

    public static String codificar(String texto, Map<String, String> codigos) {

        StringBuilder sb = new StringBuilder();

        for (char c : texto.toCharArray()) {
            sb.append(codigos.get(String.valueOf(c)));
        }

        return sb.toString();
    }
}