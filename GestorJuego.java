package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GestorJuego {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Mazo mazoJuego;
    private Random random;

    public GestorJuego() {
        tablero = new Tablero();
        mazoJuego = new Mazo();
        jugadores = new ArrayList<>();
        random = new Random();
    }

    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la simulación de Catán\n");
        tablero.mostrarRecursosYNumeros();
        tablero.mostrarTablero();

        crearJugadores(scanner);
        determinarOrdenInicio();
        realizarColocacionInicial();
        iniciarPartida();

        scanner.close();
    }

    private void crearJugadores(Scanner scanner) {
        System.out.print("Ingrese el número de jugadores (2-4): ");
        int numJugadores = scanner.nextInt();

        for (int i = 1; i <= numJugadores; i++) {
            System.out.print("Ingrese el nombre del jugador " + i + ": ");
            String nombre = scanner.next();
            ManoJugador manoJugador = new ManoJugador();
            Jugador jugador = new Jugador(nombre, manoJugador, mazoJuego);
            jugadores.add(jugador);
        }
    }

    private void determinarOrdenInicio() {
        Map<Jugador, Integer> tiradas = new HashMap<>();
        for (Jugador jugador : jugadores) {
            int dado1 = random.nextInt(6) + 1; //De esta manera se excluye el 0 pero no el 6
            int dado2 = random.nextInt(6) + 1;
            int total = dado1 + dado2;
            tiradas.put(jugador, total);
            System.out.println(jugador.getNombre() + " ha sacado: " + dado1 + " + " + dado2 + " = " + total);
        }
        jugadores.sort((j1, j2) -> tiradas.get(j2) - tiradas.get(j1));
    }

    private void realizarColocacionInicial() {
        System.out.println("\n--- Colocación inicial de poblados y carreteras (orden normal) ---");
        for (Jugador jugador : jugadores) {
            colocarEstructurasIniciales(jugador);
        }

        System.out.println("\n--- Colocación inicial de poblados y carreteras (orden inverso) ---");
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            colocarEstructurasIniciales(jugadores.get(i));
        }
    }

    private void colocarEstructurasIniciales(Jugador jugador) {
        boolean colocado = false;
        while (!colocado) {
            System.out.println("Turno de " + jugador.getNombre() + " para colocar su poblado y carretera.");
            colocado = tablero.colocarPobladoValidado(jugador, true); // or false depending on the required logic
            if (colocado) {
                tablero.repartirRecursosIniciales(jugador);
                tablero.colocarCarreteraValidada(jugador, true); // or false depending on the required logic
            } else {
                System.out.println("Ubicación inválida. Intente de nuevo.");
            }
        }
    }

    private void iniciarPartida() {
boolean juegoActivo = true;
int turno = 0;
Jugador jugadorActual = null;
while (juegoActivo) {
    jugadorActual = jugadores.get(turno % jugadores.size());
    System.out.println("\\nTurno de: " + jugadorActual.getNombre());

    jugadorActual.mostrarMenu(tablero);

    if (jugadorActual.getPuntosVictoria() >= 10) {
        System.out.println("¡" + jugadorActual.getNombre() + " ha ganado el juego con " + jugadorActual.getPuntosVictoria() + " puntos de victoria!");
        juegoActivo = false;
    }
    turno++;
}
        System.out.println("\\nFin de la partida. ¡Gracias por jugar!");
    }
//Esto deberá ir en el main

    public static void main(String[] args) {
        GestorJuego gestor = new GestorJuego();
        gestor.iniciarJuego();
    }
}


