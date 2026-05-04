package practica0;
/**
 *
 * @author ICA
 */
public class NodoSLista<T> {
    private T dato;
    private NodoSLista<T> siguiente;

    public NodoSLista(T dato, NodoSLista<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public NodoSLista(T dato) {
        this.dato = dato;
        this.siguiente=null;
    }

    public T getDato() {
        return dato;
    }

    public NodoSLista<T> getSiguiente() {
        return siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoSLista<T> siguiente) {
        this.siguiente = siguiente;
    }
    
        
}