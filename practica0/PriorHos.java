package practica0;
/*

*/
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author ICA
 */
public class PriorHos {
    private Lista lista;
    
    public PriorHos() {
        this.lista = new Lista<Paciente>();
    }
    
    public void InsertL(String nombre, String padecimiento){
            Paciente p=new Paciente(nombre, padecimiento);
            Iterator it=lista.iterator();
            int cont=0;
            boolean sigue=true;
            while(it.hasNext() && sigue){
                if(p.compareTo((((Paciente)it.next())))==1){
                    sigue=false;                    
                }
                else
                    cont++;
            }
            lista.add(cont, p);
    }
    
    public Paciente Search(String nombre, String padecimiento) {
        return (Paciente)lista.busca(new Paciente(nombre, padecimiento));
    }
    
    public boolean Delete(String nombre, String padecimiento) {
        boolean borrado = false;
        if(lista.eliminaDato(new Paciente(nombre, padecimiento)) != null){
            borrado = true;
        }
        return borrado;
    }
    
    public int cuentaPaciente() {
        return lista.cuentaElementos();
    }
    
    public Paciente siguienteAtendido() {
        return (Paciente)lista.eliminaPrimero();
    }
    
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Hospital:\n");
        Iterator it=lista.iterator();
        while(it.hasNext()) {
            sb.append(it.next()).append("\n");
        }
        return sb.toString();
    }
}
