package practica0;

import java.util.Random;

public class PruebasHospital {

    public static void main(String[] args) {
        Random rand = new Random();
        String[] nombres = {"Santiago", "Mateo", "Sebastián", "Leonardo", "Matías", "Emiliano", "Diego", "Pablo", "Pedro", "Mauricio", "Marco", "Daniel", "Sofía", "María José", "Valentina", "Ximena", "Regina", "Paula", "Frida", "Alexa", "Ana"};
        String[] padecimientos = {"Cardiorespiratorio", "Cardiovascular", "Neurologico", "Traumatico", "Abdominal", "Metabolico", "Ortopedico", "Infeccioso", "Gastrointestinal", "Respiratorio"};

        int[] tamaños = {0, 50, 100, 200, 400, 500, 600, 800, 1000}; // distintos tamaños de prueba

        System.out.println("Tiempos de inserta, busca y borra");
        System.out.printf("%-8s %-15s %-15s %-15s%n", "n", "Inserción (s)", "Búsqueda (s)", "Borrado (s)");

        double totalInserta=0;
        double totalBusca=0;
        double totalBorra=0;
        for (int n : tamaños) {
            BST<Paciente> hospital = new BST<>();

            //Inserta
            long inicioInserta = System.nanoTime();
            for (int i = 0; i < n; i++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.inserta(new Paciente(nombre, padecimiento));
            }
            long finInserta = System.nanoTime();
            double tiempoInserta = (finInserta - inicioInserta) / 1000000000.0;
            totalInserta+= tiempoInserta;

            //Busca
            long inicioBusca = System.nanoTime();
            for (int i = 0; i < n / 2; i++) { // buscamos n/2 pacientes
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.busca(new Paciente(nombre, padecimiento));
            }
            long finBusca = System.nanoTime();
            double tiempoBusca = (finBusca - inicioBusca) / 1000000000.0;
            totalBusca=tiempoBusca;

            //Borra
            long inicioBorra = System.nanoTime();
            for (int i = 0; i < n / 2; i++) { // borramos la mitad aprox
                String nombre = nombres[rand.nextInt(nombres.length)];
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospital.borra(new Paciente(nombre, padecimiento));
            }
            long finBorra = System.nanoTime();
            double tiempoBorra = (finBorra - inicioBorra) / 1000000000.0;
            totalBorra+= tiempoBorra;

            //Imrpimimos resultado
            System.out.printf("%-8d %-15.6f %-15.6f %-15.6f%n",n, totalInserta, totalBusca, totalBorra);
        }

        //Loop
        System.out.println("\nSimulación de llegadas, atenciones");
        BST<Paciente> hospitalSim = new BST<>();
        int tiempoSimulado = 1200; // simular 1200 segundos
        int atendidos = 0;
        int idPaciente = 1;

        for (int t=0; t<tiempoSimulado; t++) {
            //llegada (1 paciente por minuto aprox)
            if (rand.nextInt(60) == 0) {
                String nombre = nombres[rand.nextInt(nombres.length)] + idPaciente;
                String padecimiento = padecimientos[rand.nextInt(padecimientos.length)];
                hospitalSim.inserta(new Paciente(nombre, padecimiento));
                System.out.println(t + "s: Llega paciente " + nombre);
                idPaciente++;
            }

            //atención (1 paciente cada 2 minutos aprox)
            if (rand.nextInt(120) == 0 && hospitalSim.getCont() > 0) {
                Paciente aAtender = hospitalSim.getRaiz().getDato();
                hospitalSim.borra(aAtender);
                atendidos++;
                System.out.println(t + "s: Atendido paciente " + aAtender.getNombre());
            }
        }

        System.out.println("\nPacientes restantes en el hospital: " + hospitalSim.getCont());
        System.out.println("Pacientes atendidos: " + atendidos);
    }
}
