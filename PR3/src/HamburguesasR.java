import java.util.*;

public class HamburguesasR {

    // Estructura auxiliar para guardar los resultados
    static class Resultado {
        int total; // total hamburguesas
        int mCount; // hamburguesas tipo m
        int nCount; // hamburguesas tipo n
        int sobra; // tiempo restante

        Resultado(int total, int mCount, int nCount, int sobra) {
            this.total = total;
            this.mCount = mCount;
            this.nCount = nCount;
            this.sobra = sobra;
        }
    }

    static Map<Integer, Resultado> memo = new HashMap<>();

    public static Resultado resolver(int t, int n, int m) {
        // Si ya tenemos el resultado, lo devolvemos
        if (memo.containsKey(t)) return memo.get(t);

        // Caso base: no hay tiempo
        if (t == 0) return new Resultado(0, 0, 0, 0);

        // Si el tiempo es negativo, no se puede
        if (t < 0) return new Resultado(-1, 0, 0, t); // inválido

        // Opción 1: comer una hamburguesa de tipo n
        Resultado usarN = resolver(t - n, n, m);
        if (usarN.total != -1) usarN = new Resultado(usarN.total + 1, usarN.mCount, usarN.nCount + 1, usarN.sobra);

        // Opción 2: comer una hamburguesa de tipo m
        Resultado usarM = resolver(t - m, n, m);
        if (usarM.total != -1) usarM = new Resultado(usarM.total + 1, usarM.mCount + 1, usarM.nCount, usarM.sobra);

        Resultado mejor = new Resultado(-1, 0, 0, t); // valor por defecto

        // Tomamos la mejor opción
        if (usarN.total == -1 && usarM.total == -1) {
            // No se puede usar todo el tiempo: buscar menor sobra posible
            mejor.sobra = t; // todo el tiempo libre
        } else {
            // Si ambas son válidas
            if (usarN.total > usarM.total) mejor = usarN;
            else if (usarM.total > usarN.total) mejor = usarM;
            else {
                // Si empatan, elegimos la que deja menos tiempo libre
                mejor = (usarN.sobra <= usarM.sobra) ? usarN : usarM;
            }
        }

        memo.put(t, mejor);
        return mejor;
    }

    public static void main(String[] args) {
        int n = 9; // tiempo hamburguesa tipo n
        int m = 4; // tiempo hamburguesa tipo m
        int t = 10; // tiempo total

        Resultado res = null;
        // Probamos desde t hasta 0 para manejar el caso con sobra
        for (int i = t; i >= 0; i--) {
            res = resolver(i, n, m);
            if (res.total != -1) {
                res.sobra = t - i;
                break;
            }
        }

        if (res.sobra == 0)
            System.out.println(res.total + " " + res.mCount + " " + res.nCount);
        else
            System.out.println(res.total + " " + res.mCount + " " + res.nCount + " " + res.sobra);
    }
}
