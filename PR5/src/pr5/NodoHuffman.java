
package pr5;

public class NodoHuffman implements Comparable<NodoHuffman> {
    public String simbolo;     // Puede ser null en nodos internos
    public int frecuencia;
    public NodoHuffman izq;
    public NodoHuffman der;

    public NodoHuffman(String simbolo, int frecuencia) {
        this.simbolo = simbolo;
        this.frecuencia = frecuencia;
    }

    @Override
    public int compareTo(NodoHuffman otro) {
        return Integer.compare(this.frecuencia, otro.frecuencia);
    }

    public boolean esHoja() {
        return izq == null && der == null;
    }
}