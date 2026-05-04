package pr1;


import java.util.Arrays;

public class PacienteHash {
    private final int[] estudios; // solo nos importa este campo

    public PacienteHash(String nombre, int edad, int[] estudios) {
        // Hacemos copia defensiva para evitar cambios externos
        this.estudios = Arrays.copyOf(estudios, estudios.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PacienteHash)) return false;
        PacienteHash that = (PacienteHash) o;
        // Comparamos SOLO por estudios
        return Arrays.equals(this.estudios, that.estudios);
    }

    @Override
    public int hashCode() {
        // El hash también depende SOLO de estudios
        return Arrays.hashCode(estudios);
    }

    @Override
    public String toString() {
        return Arrays.toString(estudios);
    }
}


