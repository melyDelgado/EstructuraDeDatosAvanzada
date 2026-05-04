package practica0;

import java.util.Iterator;

/**
 *
 * @author ICA
 */
public class Lista<T> implements Iterable<T>{
    private NodoSLista<T> primero;

    public Lista() {
        this.primero=null;
    }
    
    public boolean estaVacia() {
        return primero==null;
    }

    public void agregaInicio(T dato) {
        NodoSLista<T> nuevo=new NodoSLista(dato, primero);
        primero=nuevo;
    }
    
    public void agregaFin(T dato) {
        NodoSLista<T> nuevo=new NodoSLista(dato);
        if(estaVacia())
            primero=nuevo;
        else {
            NodoSLista<T> actual=primero;
            while(actual.getSiguiente() != null)
                actual=actual.getSiguiente();
            actual.setSiguiente(nuevo);
        }
    }
    
    public void add(int index, T dato) {
        NodoSLista<T> nuevo=new NodoSLista(dato);
        if(index ==0){
            nuevo.setSiguiente (primero);
            primero=nuevo;
        }
        else {
            int cont = 0;
            NodoSLista ant = primero;
            while(ant != null && cont < index-1) {
                ant = ant.getSiguiente();
                cont++;
            }
            if(ant==null){
                throw new IndexOutOfBoundsException("indice invalido:" + index);
            }
            nuevo.setSiguiente(ant.getSiguiente());
            ant.setSiguiente (nuevo);
        }
    }
    
    public T eliminaUltimo() {
        T eliminado=null;
        if(!estaVacia()) {
            if(primero.getSiguiente() == null) {
                eliminado=primero.getDato();
                primero=null;
            }
            else {
                NodoSLista<T> anterior=primero;
                NodoSLista<T> actual=anterior.getSiguiente();
                while(actual.getSiguiente()!=null) {
                    anterior=actual;
                    actual=anterior.getSiguiente();
                }
                eliminado=actual.getDato();
                anterior.setSiguiente(null);
            }
        }
        return eliminado;
    }
    
    public T eliminaPrimero() {
        T eliminado=null;
        if(!estaVacia()) {
            NodoSLista<T> aux=primero.getSiguiente();
            eliminado=primero.getDato();
            primero.setSiguiente(null);
            primero=aux;
        }
        return eliminado;
    }
    
    public int cuentaElementos() {
        int total=0;
        NodoSLista<T> aux=primero;
        while(aux!=null) {
            aux=aux.getSiguiente();
            total++;
        }
        return total;
    }
    
    public T eliminaDato(T dato) {
        T eliminado=null;
        
        if(!estaVacia()) {
            if(dato.equals(primero.getDato())) {
                eliminado=primero.getDato();
                eliminaPrimero();
            }
            else {
                NodoSLista<T> actual=primero.getSiguiente();
                NodoSLista<T> anterior=primero;
                while(actual!=null && !dato.equals(actual.getDato())) {
                    anterior=actual;
                    actual=actual.getSiguiente();
                }
                if(actual!=null) {
                    eliminado=actual.getDato();
                    anterior.setSiguiente(actual.getSiguiente());
                    actual.setSiguiente(null);
                }
            }
        }
        return eliminado;
    }
    
    public T busca(T dato) {
        T buscado=null;
        NodoSLista<T> actual=primero;
        while(actual!=null && !actual.getDato().equals(dato))
            actual=actual.getSiguiente();
        if(actual!=null)
            buscado=actual.getDato();
        return buscado;
    }
    
    public boolean eliminaSiguienteDe(T info) {
        boolean resp=false;
        NodoSLista<T> actual = primero;
        NodoSLista<T> siguiente;
        while(actual!=null && !actual.getDato().equals(info)) 
            actual=actual.getSiguiente();
        if(actual!=null) {
            siguiente=actual.getSiguiente();
            if(siguiente!=null) {   //No es el ultimo (el actual)
                actual.setSiguiente(siguiente.getSiguiente());
                siguiente.setSiguiente(null);
                resp=true;
            }
        }
        return resp;
    }
    
    public boolean eliminaAnteriorA(T info) {
        boolean resp=false;
        NodoSLista<T> siguiente = primero;
        NodoSLista<T> anterior=null, actual=null;
        while(siguiente!=null && !siguiente.getDato().equals(info)) {
            if(actual!=null)
                anterior=actual;
            actual=siguiente;
            siguiente=siguiente.getSiguiente();            
        }
        if(siguiente!=null && actual!=null) {
            if(anterior!=null) 
                anterior.setSiguiente(siguiente);            
            else 
                primero=siguiente;
            actual.setSiguiente(null);
            resp=true;
        }
        return resp;
    }
    
    public boolean insertaAntesQue(T refer, T nuevo) {
        boolean resp=false;
        NodoSLista<T> buscado = primero;
        NodoSLista<T> anterior=null, nuevoNodo=new NodoSLista(nuevo);
        while(buscado!=null && !buscado.getDato().equals(refer)) {
            anterior=buscado;
            buscado=buscado.getSiguiente();
        }
        if(buscado!=null) {
            nuevoNodo.setSiguiente(buscado);
            if(anterior!=null)  //en medio
                anterior.setSiguiente(nuevoNodo);            
            else  //se inserta en el inicio
                primero=nuevoNodo;            
            resp=true;
        }
        return resp;
    }
    
    public int eliminaTodosRepetidosOrdenado() {
        int eliminados=0;
        NodoSLista<T> nodoBandera = primero;
        NodoSLista<T> siguiente;
        T datoBandera;
        while(nodoBandera!=null) {
            datoBandera=nodoBandera.getDato();
            siguiente=nodoBandera.getSiguiente();
            if(siguiente!=null && datoBandera.equals(siguiente.getDato())) {               
                nodoBandera.setSiguiente(siguiente.getSiguiente());
                siguiente.setSiguiente(null);
                eliminados++;
            }
            else 
                nodoBandera=siguiente;
        }
        return eliminados;
    }
    
    public int eliminaTodosRepetidosDesordenado1() {
        int eliminados=0;
        NodoSLista<T> nodoBandera = primero;
        NodoSLista<T> siguiente, aux;
        T datoBandera;
        while(nodoBandera!=null) {
            datoBandera=nodoBandera.getDato();
            siguiente=nodoBandera.getSiguiente();
            while(siguiente!=null) {
                if(datoBandera.equals(siguiente.getDato())) {
                    aux=siguiente.getSiguiente();
                    siguiente.setSiguiente(null);
                    eliminados++;
                    siguiente=aux;
                    nodoBandera.setSiguiente(aux);
                }
                else {                       
                    siguiente=siguiente.getSiguiente();
                    aux=siguiente;
                }
            }
            nodoBandera=nodoBandera.getSiguiente();
        }
        return eliminados;
    }
    
    public int eliminaTodosRepetidosDesordenado() {
        int eliminados=0;
        NodoSLista<T> bandera = primero;
        NodoSLista<T> aux1, aux2=bandera;
        T datoBandera=null;
        while(bandera!=null) {
            datoBandera=bandera.getDato();
            aux1=bandera.getSiguiente();
            while(aux1!=null) {
                if(datoBandera.equals(aux1.getDato())) {
                    aux2.setSiguiente(aux1.getSiguiente());
                    aux1.setSiguiente(null);
                    eliminados++;
                    aux1=aux2.getSiguiente();
                }
                else {
                    aux1=aux1.getSiguiente();
                
                    }
            }
            bandera=bandera.getSiguiente();            
        }
        return eliminados;
    }
    
    public void mezclaListas(Lista<T> otra) {
        NodoSLista<T> aux1 = primero;
        NodoSLista<T> aux2=otra.primero, aux=null, aux3=null;
        while(aux1!=null && aux2!=null) {
            if(aux!=null)
                aux3=aux;
            aux=aux2.getSiguiente();
            aux2.setSiguiente(aux1.getSiguiente());
            aux1.setSiguiente(aux2);
            aux1=aux2.getSiguiente();   //redefinir variables
            aux2=aux;    //redefinir variables
        }    
        if(aux2!=null) {    //caso: lista 2 tiene elementos todavia
            if(aux3!=null)
                aux3.setSiguiente(aux2);
            else
                if(primero!=null)
                    primero.getSiguiente().setSiguiente(aux2);
                else
                    primero=aux2;
        }        
    }
    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();       
        NodoSLista<T> actual=primero;
        while(actual!=null) {
            sb.append(actual.getDato()).append(" ");
            actual=actual.getSiguiente();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorLista(primero);
    }
    
    public void toString(StringBuilder sb, Iterator<T> iterador) {
        if(iterador.hasNext()) {
            sb.append(iterador.next()).append(" ");
            toString(sb, iterador);
        }
        else
            System.out.println(sb.toString());
    }
    
    public void invierteLista() {
        if(!estaVacia()) 
            invierteLista(primero);
    }
    
    private void invierteLista(NodoSLista<T> aux) {
        if(aux.getSiguiente()!=null) {
            invierteLista(aux.getSiguiente());
            aux.getSiguiente().setSiguiente(aux);
            aux.setSiguiente(null);
        }
        else
            primero=aux;
    }
}