package pr2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

// Clase Nodo: representa cada punto del árbol
class Nodo {
    private int val, id;
    private Nodo papa, hijoDer, hijoIzq;
    private static int contador = 0;

    public Nodo(int val) {
        this.val = val;
        this.id = ++contador; // Asigna un id único a cada nodo
    }

    public int getVal() {
        return val;
    }

    public int getId() {
        return id;
    }

    public Nodo getHijoDer() {
        return hijoDer;
    }

    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    public Nodo getPapa() {
        return papa;
    }

    public void setHijoDer(Nodo nodo) {
        this.hijoDer = nodo;
        if (nodo != null) nodo.papa = this;
    }

    public void setHijoIzq(Nodo nodo) {
        this.hijoIzq = nodo;
        if (nodo != null) nodo.papa = this;
    }

    public void setVal(int val) {
        this.val = val;
    }

    // Determina si un nodo es hoja
    public boolean esHoja() {
        return hijoIzq == null && hijoDer == null;
    }
}

// Clase principal del árbol de correos
public class ArbolCorreos {

    private Nodo raiz;

    public ArbolCorreos() {
        this.raiz = null;
    }

    // Construye el árbol a partir de una expresión en notación postfija
    public void construirArbol(String expresion) {
        Stack<Nodo> pila = new Stack<>();
        StringTokenizer st = new StringTokenizer(expresion);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (esNumero(token)) {
                Nodo hoja = new Nodo(Integer.parseInt(token));
                pila.push(hoja);
            } else {
                // Operador: unir los dos últimos nodos de la pila
                if (pila.size() >= 2) {
                    Nodo der = pila.pop();
                    Nodo izq = pila.pop();
                    Nodo interno = new Nodo(0); // Nodo intermedio
                    interno.setHijoIzq(izq);
                    interno.setHijoDer(der);
                    pila.push(interno);
                }
            }
        }

        if (!pila.isEmpty()) {
            raiz = pila.pop();
        }
    }

    // Verifica si el token es un número
    private boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Calcula la ruta óptima iterativamente con pilas
    public String calcularRutaOptima() {
        if (raiz == null) return "Árbol vacío";

        Stack<Nodo> pila = new Stack<>();
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
            Nodo actual = pila.pop();
            String rutaActual = rutas.pop();
            int pesoActual = pesos.pop();
            int callesActual = calles.pop();

            if (actual.esHoja()) {
                int total = pesoActual + actual.getVal();
                if (total > mejorPeso) {
                    mejorPeso = total;
                    mejorRuta = rutaActual + actual.getVal();
                    mejorCalles = callesActual + 1;
                }
            } else {
                if (actual.getHijoIzq() != null) {
                    pila.push(actual.getHijoIzq());
                    rutas.push(rutaActual + "I->");
                    pesos.push(pesoActual + actual.getVal());
                    calles.push(callesActual + 1);
                }
                if (actual.getHijoDer() != null) {
                    pila.push(actual.getHijoDer());
                    rutas.push(rutaActual + "D->");
                    pesos.push(pesoActual + actual.getVal());
                    calles.push(callesActual + 1);
                }
            }
        }

        return String.format("Ruta: %s, Calles: %d, Peso: %d", mejorRuta, mejorCalles, mejorPeso);
    }

    // Clase interna para ejecutar el programa
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rutas_semana.txt"))) {
            String linea;
            int dia = 1;

            System.out.println("=== VERSIÓN ITERATIVA CON PILA ===");

            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                ArbolCorreos arbol = new ArbolCorreos();
                arbol.construirArbol(linea);

                String resultado = arbol.calcularRutaOptima();
                System.out.printf("Día %d: %s%n", dia, resultado);
                dia++;
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}