
package practica0;

/**
 * Clase genérica Nodo<T> que representa un nodo de un árbol binario de búsqueda.
 * Cada nodo almacena un dato genérico, así como indicadores para referenciar a su hijo derecho, izquierdo y a su papa.
 */

import java.util.*;

public class Nodo<T extends Comparable<T>> {
    private T dato;
    private Nodo<T> izq, der, papa;
    
    /**
     * Constructor que inicializa el nodo con un dato y los indicadpres se inicializan en null.
     * @param dato 
     */
    public Nodo(T dato) {
        this.dato=dato;
        this.izq=null;
        this.der=null;
        this.papa=null;
    }
    //Métodos getters
    public T getDato() {
        return dato;
    }
    
    public Nodo<T> getIzq() {
        return izq;
    }
    
    public Nodo<T> getDer() {
        return der;
    }
    
    public Nodo<T> getPapa() {
        return papa;
    }
    //Métodos setters
    public void setDato(T dato){
        this.dato=dato;
    }
    
    public void setIzq(Nodo<T> izq) {
        this.izq=izq;
    }
    
    public void setDer(Nodo<T> der) {
        this.der=der;
    }
    
    public void setPapa(Nodo<T> papa) {
        this.papa=papa;
    }
    
    /**
     * Genera un código hash único para el nodo
     */
   @Override
    public int hashCode() {
       return Objects.hash(dato, izq, der, papa);
    }

    /**
     * Compara este nodo con otro para verificar si son iguales. 
     * @param obj
     * @return true si son iguales, false si son diferentes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nodo<?> other = (Nodo<?>) obj;
        return Objects.equals(this.dato, other.dato);
    }
    
    /**
     * Representación en cadena del nodo en una cadena. 
     */
    @Override
    public String toString() {
        return "Nodo{" + "dato=" + dato + '}';
    }
    
}
