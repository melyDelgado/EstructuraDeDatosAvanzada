package pr1;

import java.util.Arrays;

public class PacienteLista {
    private String nombre;
    private int edad;
    private double[] datos;
    private int id;
    private static int contador = 0;
    private static int contadoriguales = 0;
    private PacienteLista[] pacientes = new PacienteLista[1000]; // Ajusta tamaño según necesidad
    private int numPacientes = 0;

    public PacienteLista(String nombre, int edad, double[] datos) {
        this.nombre = nombre;
        this.edad = edad;
        this.datos = datos;
        this.id = contador++;
    }

    public String getNombre() { 
        return nombre; 
    }
    public int getEdad() { 
       return edad; 
    }
    public double[] getDatos() { 
        return datos; 
    }

    @Override
    public String toString() {
        return "Paciente{nombre=" + nombre + ", edad=" + edad + ", datos=" + Arrays.toString(datos) + ", id=" + id + "}";
    }

    public void ingresarPaciente(String nombre, int edad, double[] datos) {
        if (numPacientes < pacientes.length) {
            pacientes[numPacientes++] = new PacienteLista(nombre, edad, datos);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PacienteLista)) return false;
    
        PacienteLista other = (PacienteLista) obj;
    
        if (!this.nombre.equals(other.nombre)) return false;
        if (this.edad != other.edad) return false;
        if (this.datos.length != other.datos.length) return false;
    
        for (int i = 0; i < this.datos.length; i++) {
            if (this.datos[i] != other.datos[i]) return false;
        }
    
        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + edad;
        result = 31 * result + Arrays.hashCode(datos);
        return result;
    }

    // Devuelve la cantidad de pacientes iguales
    public int registrosIguales() {
        contadoriguales = 0;
        for (int i = 0; i < numPacientes; i++) {
            for (int j = i + 1; j < numPacientes; j++) {
                if (pacientes[i].equals(pacientes[j])) {
                    contadoriguales++;
                }
            }
        }
        return contadoriguales;
    }

    // Devuelve solo los pacientes cargados (sin null)
    public PacienteLista[] getPacientes() {
        return Arrays.copyOf(pacientes, numPacientes);
    }
}