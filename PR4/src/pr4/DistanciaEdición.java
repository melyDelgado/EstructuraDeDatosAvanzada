package pr4;

/**
 * Calcula la distancia de edición entre dos cadenas de texto. 
 * En esta clase tenemos los métodos de programación dinámica y recursión con memoización.
 * @author melydelgado
 */
public class DistanciaEdición {
    static final int delta=1; //Costo de insertar
    static final int alfa=2; //Costo de reemplazar
    /**
     * Calcula la distancia de edición entre A y B usando una tablaa de programación dinámica.
     * @param A
     * @param B
     * @return 
     */
    public static int ProgDin(String A, String B) {
        int m= A.length();
        int n= B.length();
        
        int [][] dp= new int[m+1][n+1];
        
        //Inicializamos las primeras filas y columnas
        for(int i=0; i<=m; i++)
            dp[i][0]= i*delta;
        for(int j=1; j<=n; j++)
            dp[0][j]= j*delta;
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++) {
                if(A.charAt(i-1)==B.charAt(j-1)) {
                    dp[i][j]= dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + alfa, Math.min(dp[i - 1][j], dp[i][j - 1]) + delta);
                }
            }
        }
        
        return dp[m][n];
        
    }
    
    /**
     * 
     * @param A
     * @param B
     * @return 
     */
    public static int memoizacion(String A, String B) {
        int m= A.length();
        int n= B.length();
        
        int [][] memo= new int[m+1][n+1];
        
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++)
                memo[i][j]=-1;
        }
        
        return memoRec(A, B, m, n, memo);
    }
    
    private static int memoRec(String A, String B,int i,int j, int[][] memo){
        if(i==0)
            return j*delta;
        if(j==0)
            return i*delta;
        
        if(memo[i][j] != -1)
            return memo[i][j];
        
        if(A.charAt(i-1)==B.charAt(j-1)) {
            memo[i][j]= memoRec(A, B, i-1, j-1, memo);
        }else {
            int insercion, eliminacion, reemplazo;
            
            insercion= memoRec(A, B, i, j-1, memo)+delta;
            eliminacion= memoRec(A,B, i-1, j, memo) + delta;
            reemplazo= memoRec(A, B, i-1, j-1, memo)+alfa;
            memo[i][j]= Math.min(insercion, Math.min(eliminacion, eliminacion));       
        }
        return memo[i][j];
    }
    
    
    
}
