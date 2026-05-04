/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author melydelgado
 */
public class HamburguesasPD {

    /**
     * Método principal que calcula el máximo número de hamburguesas posibles 
     * que Homero puede comer en el tiempo t usando programación dinámica iterativa.
     */
    
    public static void resultadoHamburguesas(int t, int m, int n) {
        int[] dp=new int[t + 1];
        dp[0]=0;

        for (int i=1; i<=t; i++) {
            int mejor=-1;

            if (i>= m && dp[i-m] != -1) {
                mejor=Math.max(mejor, dp[i-m] + 1);
            }

            if (i>=n && dp[i-n] != -1) {
                mejor=Math.max(mejor, dp[i - n] + 1);
            }

            dp[i]=mejor;
        }

        // Caso 1: Se puede usar exactamente t minutos
        if (dp[t]!=-1) {
            int[] hamburguesas=obtenerHamburguesas(t, m, n, dp);
            System.out.println("Máximo de hamburguesas: " + dp[t] + " (de " + m + " minutos: " + hamburguesas[0] + ", de " + n + " minutos: " + hamburguesas[1] + ")");
        } else {
            // Caso 2: No se puede usar exactamente t, buscamos el mejor menor a t
            int tiempoUsado=t;
            while (tiempoUsado>0 && dp[tiempoUsado]==-1) {
                tiempoUsado--;
            }

            int sobrante=t-tiempoUsado;
            int[] hamburguesas=obtenerHamburguesas(tiempoUsado, m, n, dp);

            System.out.println("No se puede usar exactamente " + t + " minutos.");
            System.out.println("Máximo de hamburguesas: " + dp[tiempoUsado] + " (de " + m + " minutos: " + hamburguesas[0] + ", de " + n + " minutos: " + hamburguesas[1] + ")");
            System.out.println("Tiempo sobrante: " + sobrante + " minutos");
        }
    }

    /**
     * Obtiene cuántas hamburguesas de cada tipo se comieron en el tiempo t.
     */
    private static int[] obtenerHamburguesas(int t, int m, int n, int[] dp) {
        int hamM=0, hamN=0;

        while (t>0) {
            if (t>=m && dp[t]==dp[t-m] + 1) {
                hamM++;
                t-=m;
            } else if (t>=n && dp[t]==dp[t-n] + 1) {
                hamN++;
                t-=n;
            } else {
                break;
            }
        }

        return new int[]{hamM, hamN};
    }
}
