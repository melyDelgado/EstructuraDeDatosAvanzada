
package practica0;

/**
 * Clase genérica para implementar los métodos principales de un árbol binario de búsqueda, inserta, busca y borra.
 * Además tenemos otros dos métodos para imprimir el arbol inOrden.
 * @author melydelgado
 */

import java.util.*;

public class BST<T extends Comparable<T>> {
    Nodo<T> raiz;
    int cont;

    /**
     * Constructor dónde la raíz es null y nuestro contador empieza en 0.
     */
    public BST() {
        raiz=null;
        cont=0;
    }
    
    //getters
    public Nodo<T> getRaiz() {
        return raiz;
    }
    
    public int getCont() {
        return cont;
    }
    
    //setters
    public void setRaiz(Nodo<T> raiz) {
        this.raiz=raiz;
    } 
    
    public void setCont(int cont) {
        this.cont=cont;
    }
    
    /**
     * Insertamos el nodo dado el elemento
     * @param elem 
     */
    public void inserta(T elem) {
    Nodo<T> actual=raiz; 
    Nodo<T> papa=null, nuevo;

    if (raiz == null) {
      raiz=new Nodo<T>(elem); //Si el árbol está vacío, nuestro primer nodo se convierte en la raíz.
      cont++;
      return;
    }
    //Comparamos la información con el nodo actual para colocarlo correctamente ya sea por la derecha si es mayor o a la izquierda si es menor
    while (actual != null) {
      papa= actual;
      if (elem.compareTo(actual.getDato()) <= 0)
        actual=actual.getIzq();
      else
        actual=actual.getDer();
    }
    nuevo=new Nodo<T>(elem);
    if (elem.compareTo(papa.getDato())<= 0) {
      papa.setIzq(nuevo);
    } else {
      papa.setDer(nuevo);
    }
    nuevo.setPapa(papa);
    cont++;
  }
    
    /**
     * Buscamos al nodo dado su elmento. Tenemos un método recursivo. 
     * @param elem
     * @return 
     */
    public Nodo<T> busca(T elem) {
    if (raiz == null)
      return null;

    return busca(raiz, elem);
  }

    private Nodo<T> busca(Nodo<T> actual, T elem) {
      if (actual == null)
        return null;

      if (actual.getDato().equals(elem))
        return actual;

      if (actual.getDato().compareTo(elem) > 0)
        return busca(actual.getIzq(), elem);
      else
        return busca(actual.getDer(), elem);

    }
    
      /**
       * Borramos al nodo dado su elemento. 
       * @param elem 
       */
      public void borra(T elem) {
      Nodo<T> actual = busca(elem);

      if (actual == null)
        return;

      // Existen 3 casos:
      // 1. Solo es una hoja.
      if (actual.getIzq() == null && actual.getDer() == null) { //si la raíz es la hoja, la borramos
        if (actual == raiz) {
          raiz = null;
        } else {
          Nodo<T> papa = actual.getPapa();

          if (papa.getIzq() == actual) { //si la hoja es uno de sus hijos, quitamos esa referencia y lo borramos
            papa.setIzq(null);
          } else {
            papa.setDer(null);
          }
        }
        cont--;

      } else if ((actual.getIzq() == null || actual.getDer() == null)) {
        // 2. Tiene 1 hijo.
        Nodo<T> hijo = actual.getIzq() != null ? actual.getIzq() : actual.getDer();
        if (actual == raiz) { //El hijo se convierte en la raíz
          raiz = hijo;
          cont--;
          return;
        }
        if (actual.getPapa().getIzq() == actual)
          actual.getPapa().setIzq(hijo);
        else
          actual.getPapa().setDer(hijo);

        hijo.setPapa(actual.getPapa());

        cont--;
      } else {
        // Caso 3: Nodo con 2 hijos.
        Nodo<T> sucesor = actual.getDer();
        while (sucesor.getIzq() != null) {
          sucesor = sucesor.getIzq();
        }
        actual.setDato(sucesor.getDato());

        if (sucesor.getPapa().getIzq() == sucesor) {
          sucesor.getPapa().setIzq(sucesor.getDer());
        } else {
          sucesor.getPapa().setDer(sucesor.getDer());
        }

        if (sucesor.getDer() != null) {
          sucesor.getDer().setPapa(sucesor.getPapa());
        }
        cont--;

      }

    }
      
      /**
       * Recorremos el árbol inOrden
       * @param actual 
       */
      public void inOrden(Nodo<T> actual) {
        if (actual != null) {
            inOrden(actual.getIzq());
            System.out.println(actual.getDato());
            inOrden(actual.getDer());
        }
    }

    public void imprimeInOrden() {
        inOrden(raiz);
    }
    
    
}
