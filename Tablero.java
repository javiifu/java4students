package proyecto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tablero {
    
    static final int FILAS = 24; // Tamaño aumentado para evitar desbordamientos
    static final int COLUMNAS = 31; // Tamaño aumentado para evitar desbordamientos

    // Simbolos que aparecerán en la consola. 
    static final char VERTICE = '.';
    static final char ARISTA = 'x';
    static final char VACIO = ' ';

    private String[][] tablero;
    private Map<Integer, String> recursosHexagonos; // Mapa para asociar el ID del hexágono con su recurso.
    private Map<Integer, Integer> numerosHexagonos; // Mapa para asociar el ID del hexágono con su número.
    private int contadorHexagonos; // Contador para asignar identificadores únicos a los hexágonos

    //Creamos aparte estas dos list que son las que irán asociadas a los vértices y aristas.
    private List<Vertice> vertices; 
    private List<Arista> aristas;

    //Creamos una lista para de recursos y números para asignarlos a los hexágonos
    private static final List<String> DISTRIBUCION_RECURSOS = Arrays.asList(
            "Bosque", "Bosque", "Bosque", "Bosque",
            "Trigo", "Trigo", "Trigo", "Trigo",
            "Pastizal", "Pastizal", "Pastizal", "Pastizal",
            "Piedra", "Piedra", "Piedra",
            "Arcilla", "Arcilla", "Arcilla",
            "Desierto"
    );

    // Distribución oficial de números en Catán sin desierto para que no haya problemas. 
    private static final List<Integer> DISTRIBUCION_NUMEROS = Arrays.asList(
            5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11
    );

    // Constructor para inicializar el tablero
    public Tablero() {
        tablero = new String[FILAS][COLUMNAS];
        recursosHexagonos = new HashMap<>(); //
        numerosHexagonos = new HashMap<>();//
        vertices = new ArrayList<>(); // Inicializar la lista de vértices
        aristas = new ArrayList<>(); // Inicializar la lista de aristas
        contadorHexagonos = 1;
        inicializarTablero();
        generarRecursos();
    }

    // Método para inicializar el tablero
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = String.valueOf(VACIO);
            }
        }

        // Patrón escalonado para simular los hexágonos del Catán (alineados para formar un bloque)
        
        colocarHexagono(1, 8);
        colocarHexagono(1, 13);
        colocarHexagono(1, 18);

        colocarHexagono(5, 6);
        colocarHexagono(5, 11);
        colocarHexagono(5, 16);
        colocarHexagono(5, 21);

        colocarHexagono(9, 4);
        colocarHexagono(9, 9);
        colocarHexagono(9, 14);
        colocarHexagono(9, 19);
        colocarHexagono(9, 24);


        colocarHexagono(13, 6);
        colocarHexagono(13, 11);
        colocarHexagono(13, 16);
        colocarHexagono(13, 21);

        colocarHexagono(17, 8);
        colocarHexagono(17, 13);
        colocarHexagono(17, 18);
        // Crear vértices y agregarlos a la lista de vértices
        for (int i = 0; i < 54; i++) { // Suponiendo que hay 54 vértices en el tablero
            vertices.add(new Vertice(i));
        }
        // Crear aristas y agregarlas a la lista de aristas
        for (int i = 0; i < 72; i++) { // Suponiendo que hay 72 aristas en el tablero
            aristas.add(new Arista(i, vertices.get(i % 54), vertices.get((i + 1) % 54))); // Ejemplo de conexión de vértices
        }
    }

    
    // Método para colocar un hexágono en el tablero
    private void colocarHexagono(int filaBase, int colBase) {
        // Validaciones para evitar desbordamiento
        if (filaBase + 4 >= FILAS || colBase + 3 >= COLUMNAS || colBase - 1 < 0) {
            return; // Se omite si no cabe en el array
        }
        int idHexagono = contadorHexagonos++;
        recursosHexagonos.put(idHexagono, "Pendiente");
        numerosHexagonos.put(idHexagono, 0);
        // Como van a ir dispuestos los vértices dentro del tablero
        tablero[filaBase][colBase] = String.valueOf(VERTICE);
        tablero[filaBase][colBase + 2] = String.valueOf(VERTICE);
        tablero[filaBase + 2][colBase - 1] = String.valueOf(VERTICE);
        tablero[filaBase + 2][colBase + 3] = String.valueOf(VERTICE);
        tablero[filaBase + 4][colBase] = String.valueOf(VERTICE);
        tablero[filaBase + 4][colBase + 2] = String.valueOf(VERTICE);

        // Como van a ir dispuestas las aristas dentro del tablero. 
        tablero[filaBase][colBase + 1] = String.valueOf(ARISTA);
        tablero[filaBase + 1][colBase - 1] = String.valueOf(ARISTA);
        tablero[filaBase + 1][colBase + 3] = String.valueOf(ARISTA);
        tablero[filaBase + 3][colBase - 1] = String.valueOf(ARISTA);
        tablero[filaBase + 3][colBase + 3] = String.valueOf(ARISTA);
        tablero[filaBase + 4][colBase + 1] = String.valueOf(ARISTA);
    }

    // Método público para mostrar el tablero en consola
    public void mostrarTablero() {
        for (String[] fila : tablero) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
    // Método para asignar recursos y números siguiendo las reglas oficiales del Catán
    private void asignarRecursosYNumeros() {
        //Creamos unas listas para poder 
        List<String> recursosAleatorios = new ArrayList<>(DISTRIBUCION_RECURSOS);
        Collections.shuffle(recursosAleatorios); //Utilizamos la función shuffle de la libería Collections para barajar los recursos

        List<Integer> numerosAleatorios = new ArrayList<>(DISTRIBUCION_NUMEROS);
        Collections.shuffle(numerosAleatorios); //Hacemos los mismo con los números para que se repartán entre los hexágonos. 
        //Añadimos al hasmap los recursos para cada hexágono y los números asignados a cada uno. salvo para el desierto. 
        int indiceNumero = 0;
        for (Integer id : recursosHexagonos.keySet()) {
            String recurso = recursosAleatorios.remove(0);
            recursosHexagonos.put(id, recurso);
            if (!recurso.equals("Desierto")) {
                numerosHexagonos.put(id, numerosAleatorios.get(indiceNumero++));
            } else {
                numerosHexagonos.put(id, 0);
            }
        }
    }

public boolean colocarPobladoValidado(Jugador jugador, boolean esGratis) {
    Scanner scanner = new Scanner(System.in);
    mostrarVerticesDisponibles();
    System.out.println("Ingrese el ID del vértice donde desea colocar el poblado: ");
    int idVertice = scanner.nextInt();

    if (idVertice < 0 || idVertice >= vertices.size()) {
                return false;
            }
        return false;
    }


    private boolean validarDistanciaMinima(int idVertice) {
        for (Arista arista : aristas) {
            if (arista.getVerticeA().getId() == idVertice || arista.getVerticeB().getId() == idVertice) {
                if (!arista.getVerticeA().getTipoConstruccion().equals("vacio") ||
                        !arista.getVerticeB().getTipoConstruccion().equals("vacio")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void repartirRecursosIniciales(Jugador jugador) {
        System.out.println("Repartiendo recursos iniciales para " + jugador.getNombre());
        for (Vertice v : vertices) {
            if (v.getPropietario() == jugador && v.getTipoConstruccion().equals("poblado")) {
                int idHexagono = v.getId() % 19;
                String recurso = recursosHexagonos.getOrDefault(idHexagono, "Desierto");
                if (!recurso.equals("Desierto")) {
                    jugador.recibirRecurso(new Carta(recurso, Carta.Tipocarta.Recurso));
                }
            }
        }
        jugador.mostrarRecursos();
    }

    public boolean colocarCarreteraValidada(Jugador jugador, boolean esGratis) {
        Scanner scanner = new Scanner(System.in);
        mostrarAristasDisponibles();
        System.out.println("Ingrese el ID de la arista donde desea colocar la carretera: ");
        int idArista = scanner.nextInt();

        if (idArista < 0 || idArista >= aristas.size()) {
            return false;
        }
        return false;

    }

// Método para mostrar los recursos y números asignados a cada hexágono
    public void mostrarRecursosYNumeros() {
        System.out.println("\nRecursos y números asignados a los hexágonos:");
        for (Integer id : recursosHexagonos.keySet()) {
           System.out.println("Hexágono " + id + ": " + recursosHexagonos.get(id) + " (Número: " + numerosHexagonos.get(id) + ")");
        }
    }
     
    //Métodos para utilizar dentro de la clase Jugador. Para poder colocar poblados, ciudades y carreteras en el tablero.
    public void colocarPoblado(Jugador jugador) {
        System.out.println("Poblado colocado en el tablero por " + jugador.getNombre());
    }

    public void mejorarCiudad(Jugador jugador) {
        System.out.println("Ciudad mejorada en el tablero por " + jugador.getNombre());
    }

    public void colocarCarretera(Jugador jugador) {
        System.out.println("Carretera colocada en el tablero por " + jugador.getNombre());
    }
    // Nuevo método para generar recursos llamando a la asignación de recursos y números
    private void generarRecursos() {
        asignarRecursosYNumeros();
    }
    public void mostrarVerticesDisponibles() {
        System.out.println("Vértices disponibles para construir un poblado:");
        for (Vertice vertice : vertices) {
            if (vertice.getTipoConstruccion().equals("vacio")) {
                System.out.print(vertice.getId()+", ");
            }
        }
    }

    // Método para mostrar las aristas disponibles
    public void mostrarAristasDisponibles() {
        System.out.println("Aristas disponibles para construir una carretera:");
        for (Arista arista : aristas) {
            if (!arista.tieneCarretera() && (arista.getVerticeA().getPropietario() == null || arista.getVerticeB().getPropietario() == null)) {
                System.out.print(arista.getId()+", ");
            }
        }
    }

    // Método para obtener los vértices de un jugador
    public List<Vertice> getVerticesJugador(Jugador jugador) {
        List<Vertice> verticesJugador = new ArrayList<>();
        for (Vertice vertice : vertices) {
            if (vertice.getPropietario() == jugador) {
                verticesJugador.add(vertice);
            }
        }
        return verticesJugador;
    }

    //Hecho con IA para solventar problemas. 
    public int getNumeroHexagono(Vertice vertice) {
        // Implementación para obtener el número del hexágono asociado a un vértice
        // Suponiendo que cada vértice tiene un ID que se puede usar para obtener el número del hexágono
        return numerosHexagonos.getOrDefault(vertice.getId(), 0);
    }

    // Método para obtener el recurso del hexágono asociado a un vértice
    public Carta getRecursoHexagono(Vertice vertice) {
        // Implementación para obtener el recurso del hexágono asociado a un vértice
        // Suponiendo que cada vértice tiene un ID que se puede usar para obtener el recurso del hexágono
        String recursoNombre = recursosHexagonos.getOrDefault(vertice.getId(), "RecursoDesconocido");
        return new Carta(recursoNombre, Carta.Tipocarta.Recurso);
    }
}




