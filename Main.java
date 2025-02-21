package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Mazo mazoJuego = new Mazo();
        Random random = new Random();
        

        System.out.println("Bienvenido al juego de los Colones de Catán\n");
        tablero.mostrarRecursosYNumeros();
        tablero.mostrarTablero();

        // Crear jugadores
        System.out.print("Ingrese el número de jugadores (2-4): ");
        int numJugadores = sc.nextInt();
        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 1; i <= numJugadores; i++) {
            System.out.print("Ingrese el nombre del jugador " + i + ": ");
            String nombre = sc.next();
            ManoJugador ManoJugador = new ManoJugador(); // Creamos la mano del jugador
            Jugador jugador = new Jugador(nombre, ManoJugador, mazoJuego);
            jugadores.add(jugador);
        }

        Map<Jugador, Integer> tiradas = new HashMap<>();
        for (Jugador jugador : jugadores) {
            int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int total = dado1 + dado2;
            tiradas.put(jugador, total);
            System.out.println(jugador.getNombre() + " ha sacado: " + dado1 + " + " + dado2 + " = " + total);
        }

        jugadores.sort((j1, j2) -> tiradas.get(j2) - tiradas.get(j1));
        System.out.println("\nEl orden de inicio es:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getNombre());
        }

        // Colocación inicial de poblados y carreteras (orden inverso)
        System.out.println("\n--- Colocación inicial de poblados y carreteras ---");
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);
            boolean colocado = false;
            while (!colocado) {
                System.out.println("Turno de " + jugador.getNombre() + " para colocar su segundo poblado y carretera.");
                colocado = tablero.colocarPobladoValidado(jugador, true);
                if (colocado) {
                    tablero.colocarCarreteraValidada(jugador, true);
                } else {
                    System.out.println("Ubicación inválida. Intente de nuevo.");
                }
            }
        }

        // Colocación inicial de poblados y carreteras (orden inverso)
        System.out.println("\n--- Colocación inicial de poblados y carreteras (orden inverso) ---");
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);
            System.out.println("Turno de " + jugador.getNombre() + " para colocar su segundo poblado y carretera.");
            tablero.mostrarVerticesDisponibles(); // Mostrar vértices disponibles
            jugador.construirPoblado(tablero, true);
            jugador.construirCarretera(tablero, true);
        }

        boolean juegoActivo = true;
        int turno = 0;

        while (juegoActivo) {
            Jugador jugadorActual = jugadores.get(turno % numJugadores);
            System.out.println("\nTurno de: " + jugadorActual.getNombre());
            // Actualizar y mostrar el tablero
            actualizarYMostrarTablero(tablero);
            // Tirada de dados
            int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int total = dado1 + dado2;
            System.out.println(jugadorActual.getNombre() + " ha sacado: " + dado1 + " + " + dado2 + " = " + total);

            // Menú de acciones
            jugadorActual.mostrarMenu(tablero);

            // Comprobar si alguien ha ganado
            if (jugadorActual.getPuntosVictoria() >= 10) {
                System.out.println("¡" + jugadorActual.getNombre() + " ha ganado el juego con " + jugadorActual.getPuntosVictoria() + " puntos de victoria!");
                juegoActivo = false;
            }

            turno++;
        }

        System.out.println("\nFin de la partida. ¡Gracias por jugar!");
        sc.close();
    }

    // Método para actualizar y mostrar el tablero
    private static void actualizarYMostrarTablero(Tablero tablero) {
        // Aquí puedes agregar cualquier lógica adicional para actualizar el estado del tablero si es necesario
        tablero.mostrarTablero();
    }

    private static void distribuirRecursos(Tablero tablero, List<Jugador> jugadores, int numero) {
        for (Jugador jugador : jugadores) {
            List<Vertice> verticesJugador = tablero.getVerticesJugador(jugador);
            for (Vertice vertice : verticesJugador) {
                if (tablero.getNumeroHexagono(vertice) == numero) {
                    Carta recurso = tablero.getRecursoHexagono(vertice);
                    jugador.recibirRecurso(recurso);
                    System.out.println(jugador.getNombre() + " ha recibido " + recurso.getNombre());
                }
            }
        }
    }
}