package practica0;

import java.util.Random;

public class PruebasHospitalLista {

    public static void main(String[] args) {
        Random rand = new Random();
        String[] nombres = {"Santiago", "Mateo", "Sebastián", "Leonardo", "Matías", "Emiliano", "Diego", "Pablo", "Pedro", "Mauricio", "Marco", "Daniel", "Sofía", "María José", "Valentina", "Ximena", "Regina", "Paula", "Frida", "Alexa", "Ana"};
        String[] padecimientos = {"Cardiorrespiratorio", "Cardiovascular", "Neurologico", "Traumatico", "Abdominal", "Metabolico", "Ortopedico", "Infeccioso", "Gastrointestinal", "Respiratorio"};

        int[] tamaños = {10, 25, 50, 100, 250, 500, 1000}; // distintos tamaños de prueba

        System.out.println("Tiempos de inserta, busca y borra");
        System.out.printf("%-8s %-15s %-15s %-15s%n", "n", "Inserción (s)", "Búsqueda (s)", "Borrado (s)");

        double sumaTiempoI=0, sumaTiempoB=0, sumaTiempoE=0;
        for (int n : tamaños) {
            PriorHos hospital = new PriorHos();

            //Inserta
            long inicioInserta = System.nanoTime();
            
            for (int i = 0; i < n; i++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.InsertL(nombre, padecimiento);
            }
            long finInserta = System.nanoTime();
            double tiempoInserta = (finInserta - inicioInserta) / 1_000_000_000.0;
            sumaTiempoI=sumaTiempoI+tiempoInserta;

            //Busca
            long inicioBusca = System.nanoTime();
            for (int i = 0; i < n / 2; i++) { // buscamos n/2 pacientes
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.Search(nombre, padecimiento);
            }
            long finBusca = System.nanoTime();
            double tiempoBusca = (finBusca - inicioBusca) / 1_000_000_000.0;
            sumaTiempoB=sumaTiempoB+tiempoBusca;

            //Borra
            long inicioBorra = System.nanoTime();
            for (int i = 0; i < n / 2; i++) { // borramos la mitad aprox
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.Delete(nombre, padecimiento);
            }
            long finBorra = System.nanoTime();
            double tiempoBorra = (finBorra - inicioBorra) / 1_000_000_000.0;
            sumaTiempoE=sumaTiempoE+tiempoBorra;

            //Imrpimimos resultado
            System.out.printf("%-8d %-15.6f %-15.6f %-15.6f%n",
                              n, sumaTiempoI, sumaTiempoB, sumaTiempoE);
        }

        //Loop
        System.out.println("\nSimulación de llegadas, atenciones");
        PriorHos hospitalSim = new PriorHos();
        int tiempoSimulado = 1200; // simular 600 segundos (10 min aprox)
        int atendidos = 0;
        int idPaciente = 1;

        for (int t = 0; t < tiempoSimulado; t++) {
            //llegada (1 paciente por minuto aprox)
            if (rand.nextInt(60) == 0) {
                String nombre = nombres[rand.nextInt(nombres.length)] + idPaciente;
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospitalSim.InsertL(nombre, padecimiento);
                System.out.println(t + "s: Llega paciente " + nombre);
                idPaciente++;
            }

            //atención (1 paciente cada 2 minutos aprox)
            if (rand.nextInt(120) == 0 && hospitalSim.cuentaPaciente()> 0) {
                Paciente aAtender = hospitalSim.siguienteAtendido();
                atendidos++;
                System.out.println(t + "s: Atendido paciente " + aAtender.getNombre());
            }
        }

        System.out.println("\nPacientes restantes en el hospital: " + hospitalSim.cuentaPaciente());
        System.out.println("Pacientes atendidos: " + atendidos);
    }
}