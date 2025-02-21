package proyecto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Jugador {
    public enum Recurso {
        BOSQUE("Bosque"),
        TRIGO("Trigo"),
        PASTIZAL("Pastizal"),
        PIEDRA("Piedra"),
        ARCILLA("Arcilla");

        private final String nombre;

        Recurso(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    private String nombre;
    private int puntosVictoria;
    private Mazo mazo;
    private ManoJugador mano;

    public Jugador(String nombre, ManoJugador mano, Mazo mazo) {
        this.nombre = nombre;
        this.mano = mano;
        this.mazo = mazo;
        this.puntosVictoria = 0;
    }

    public void recibirRecurso(Carta recurso) {
        mano.recibirCarta(recurso);
    }

    public boolean canjearRecursos(List<Carta> costo) {
        Map<String, Long> recursoJugador = mano.getCartasRecursos().stream()
                .filter(c -> c.getTipo() == Carta.Tipocarta.Recurso)
                .collect(Collectors.groupingBy(Carta::getNombre, Collectors.counting()));

        boolean tieneRecursos = costo.stream().allMatch(c ->
                recursoJugador.getOrDefault(c.getNombre(), 0L) >= Collections.frequency(costo, c));

        if (tieneRecursos) {
            for (Carta c : costo) {
                mano.getCartasRecursos().removeIf(r -> r.getNombre().equals(c.getNombre()));
            }
            return true;
        }
        return false;
    }

    public boolean canjearPorCartaDesarrollo() {
        List<Carta> costo = List.of(
                new Carta("Trigo", Carta.Tipocarta.Recurso),
                new Carta("Piedra", Carta.Tipocarta.Recurso),
                new Carta("Pastizal", Carta.Tipocarta.Recurso));

        if (canjearRecursos(costo)) {
            Carta cartaRobada = mazo.robarCarta();
            System.out.println("Has recibido la carta de desarrollo: " + cartaRobada.getNombre());
            if ("Punto de Victoria".equals(cartaRobada.getNombre())) {
                puntosVictoria++;
            }
            return true;
        }
        System.out.println("No tienes recursos suficientes para canjear una carta de desarrollo.");
        return false;
    }

    public void construirPoblado(Tablero tablero, boolean esGratis) {
        List<Carta> costo = List.of(
                new Carta("Bosque", Carta.Tipocarta.Recurso),
                new Carta("Trigo", Carta.Tipocarta.Recurso),
                new Carta("Pastizal", Carta.Tipocarta.Recurso),
                new Carta("Arcilla", Carta.Tipocarta.Recurso));

        if (canjearRecursos(costo)) {
            tablero.colocarPoblado(this);
            puntosVictoria++;
            System.out.println("Poblado construido correctamente.");
        } else {
            System.out.println("No tienes recursos suficientes para construir un poblado.");
        }
    }

    public void construirCiudad(Tablero tablero) {
        List<Carta> costo = List.of(
                new Carta("Trigo", Carta.Tipocarta.Recurso),
                new Carta("Trigo", Carta.Tipocarta.Recurso),
                new Carta("Piedra", Carta.Tipocarta.Recurso),
                new Carta("Piedra", Carta.Tipocarta.Recurso),
                new Carta("Piedra", Carta.Tipocarta.Recurso));

        if (canjearRecursos(costo)) {
            tablero.mejorarCiudad(this);
            puntosVictoria++;
            System.out.println("Ciudad mejorada correctamente.");
        } else {
            System.out.println("No tienes recursos suficientes para mejorar a ciudad.");
        }
    }

    public void construirCarretera(Tablero tablero, boolean esGratis) {
        List<Carta> costo = List.of(
                new Carta("Bosque", Carta.Tipocarta.Recurso),
                new Carta("Arcilla", Carta.Tipocarta.Recurso));

        if (canjearRecursos(costo)) {
            tablero.colocarCarretera(this);
            System.out.println("Carretera construida correctamente.");
        } else {
            System.out.println("No tienes recursos suficientes para construir una carretera.");
        }
    }

    public void mostrarRecursos() {
        Map<String, Long> recursoJugador = mano.getCartasRecursos().stream()
                .filter(c -> c.getTipo() == Carta.Tipocarta.Recurso)
                .collect(Collectors.groupingBy(Carta::getNombre, Collectors.counting()));

        recursoJugador.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public int getPuntosVictoria() {
        return puntosVictoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarMenu(Tablero tablero) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\\n--- Menú de " + nombre + " ---");
            System.out.println("1. Construir Poblado");
            System.out.println("2. Mejorar a Ciudad");
            System.out.println("3. Construir Carretera");
            System.out.println("4. Canjear por Carta de Desarrollo");
            System.out.println("5. Mostrar Recursos");
            System.out.println("6. Pasar turno");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> construirPoblado(tablero, false);
                case 2 -> construirCiudad(tablero);
                case 3 -> construirCarretera(tablero, false);
                case 4 -> canjearPorCartaDesarrollo();
                case 5 -> mostrarRecursos();
                case 6 -> {
                    System.out.println("Saliendo del menú...");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
