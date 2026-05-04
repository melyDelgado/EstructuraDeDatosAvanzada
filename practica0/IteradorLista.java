/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica0;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author HP
 */
public class IteradorLista<T> implements Iterator<T>{
    private NodoSLista<T> actual;

    public IteradorLista(NodoSLista<T> actual) {
        this.actual = actual;
    }

    public IteradorLista() {
    }

    @Override
    public boolean hasNext() {
        return actual!=null;
    }

    @Override
    public T next() {
        if(hasNext()) {
            T resultado=actual.getDato();
            actual=actual.getSiguiente();
            return resultado;
        }
        throw new NoSuchElementException();
    }
    
}
