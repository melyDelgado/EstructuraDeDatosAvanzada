/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import java.util.Stack;

public class ArbolCorreos {

    private TreeNode raiz;

    public ArbolCorreos(TreeNode raiz) {
        this.raiz = raiz;
    }

    //Versión iterativa con pila
    public String calcularRutaOptimaIterativa() {
        if (raiz == null) return "Árbol vacío";

        Stack<TreeNode> pila = new Stack<>();
        Stack<String> rutas = new Stack<>();
        Stack<Integer> pesos = new Stack<>();
        Stack<Integer> calles = new Stack<>();

        pila.push(raiz);
        rutas.push("Inicio->");
        pesos.push(0);
        calles.push(0);

        int mejorPeso = Integer.MIN_VALUE;
        String mejorRuta = "";
        int mejorCalles = 0;

        while (!pila.isEmpty()) {
            TreeNode actual = pila.pop();
            String rutaActual = rutas.pop();
            int pesoActual = pesos.pop();
            int callesActual = calles.pop();

            if (actual.left == null && actual.right == null) {
                int total = pesoActual + actual.peso;
                if (total > mejorPeso) {
                    mejorPeso = total;
                    mejorRuta = rutaActual + actual.peso;
                    mejorCalles = callesActual + 1;
                }
            } else {
                if (actual.left != null) {
                    pila.push(actual.left);
                    rutas.push(rutaActual + "I->");
                    pesos.push(pesoActual + actual.peso);
                    calles.push(callesActual + 1);
                }
                if (actual.right != null) {
                    pila.push(actual.right);
                    rutas.push(rutaActual + "D->");
                    pesos.push(pesoActual + actual.peso);
                    calles.push(callesActual + 1);
                }
            }
        }

        return String.format("Ruta: %s, Calles: %d, Peso total: %d", mejorRuta, mejorCalles, mejorPeso);
    }

    // ✅ Versión recursiva
    public String calcularRutaOptimaRecursiva() {
        Resultado res = calcularRec(raiz, "", 0, 0);
        return String.format("Ruta: %s, Calles: %d, Peso total: %d", res.ruta, res.calles, res.peso);
    }

    private Resultado calcularRec(TreeNode nodo, String ruta, int pesoAcum, int calles) {
        if (nodo == null) return new Resultado(ruta, calles, pesoAcum);

        if (nodo.left == null && nodo.right == null) {
            return new Resultado(ruta + nodo.peso, calles + 1, pesoAcum + nodo.peso);
        }

        Resultado izq = calcularRec(nodo.left, ruta + "I->", pesoAcum + nodo.peso, calles + 1);
        Resultado der = calcularRec(nodo.right, ruta + "D->", pesoAcum + nodo.peso, calles + 1);

        return (izq.peso >= der.peso) ? izq : der;
    }

    private class Resultado {
        String ruta;
        int calles;
        int peso;

        Resultado(String ruta, int calles, int peso) {
            this.ruta = ruta;
            this.calles = calles;
            this.peso = peso;
        }
    }
}