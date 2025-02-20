import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SistemaReservas {
    // Atributos
    // SistemaReservas se encargará de almacenar los pasajeros y los vuelos
    private static HashMap<String, Pasajero> pasajeros = new HashMap<>();
    private static ArrayList<Vuelo> vuelos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    // Método principal, punto de entrada al programa
    public static void main(String[] args) {
        // Bucle principal del programa
        // Como es un menu, usamos do-while para que se ejecute al menos una vez
        // El bucle se ejecuta mientras la opción no sea 9 (salir)
        int opcion;
        do {
            System.out.println("\nBienvenido al Sistema de Reservas de Vuelos ✈️");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Crear vuelo");
            System.out.println("3. Reservar asiento");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Mostrar vuelos");
            System.out.println("6. Mostrar reservas en un vuelo");
            System.out.println("7. Guardar datos");
            System.out.println("8. Cargar datos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            // Limpiamos el buffer del scanner (forzamos a que lea la siguiente línea vacía)
            scanner.nextLine();  

            // Switch para manejar las opciones del menú
            switch (opcion) {
                case 1 -> registrarPasajero(); // No hace falta break porque usamos -> (Java 14+)
                case 2 -> crearVuelo();
                case 3 -> reservarAsiento();
                case 4 -> cancelarReserva();
                case 5 -> mostrarVuelos();
                case 6 -> mostrarReservas();
                case 7 -> Persistencia.guardarDatos(pasajeros, vuelos); // Llamamos a la clase Persistencia 
                case 8 -> Persistencia.cargarDatos(pasajeros, vuelos);  // (no hace falta importarla porque está en el mismo paquete)
                // Podemos hacerlo así porque los métodos son estáticos
            }
        } while (opcion != 9);
    }

    // Métodos auxiliares para el menu
    private static void registrarPasajero() {
        // Pedimos los datos del pasajero
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiamos el buffer del scanner

        // Creamos un nuevo pasajero y lo añadimos al mapa de pasajeros (clave: ID, valor: Pasajero)
        // Lo hacemos directamente por no crear una nueva variable, 
        // da totalmente igual separarlo en dos lineas pero me resulta mas cómodo así.
        // Usamos put porque es un mapa
        pasajeros.put(id, new Pasajero(id, nombre, edad));

        System.out.println("Pasajero registrado con éxito.");
    }

    private static void crearVuelo() {
        // Pedimos los datos del vuelo
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        // Creamos un nuevo vuelo y lo añadimos a la lista de vuelos (usamos add porque es lista)
        vuelos.add(new Vuelo(codigo, origen, destino, fecha, capacidad));
        System.out.println("Vuelo creado con éxito.");
    }

    private static void reservarAsiento() {
        System.out.print("ID Pasajero: ");
        String id = scanner.nextLine();
        // Obtenemos el pasajero del mapa de pasajeros
        Pasajero pasajero = pasajeros.get(id);
        if (pasajero == null) {
            // Si el pasajero no existe, mostramos un mensaje y salimos del método
            System.out.println("Pasajero no encontrado.");
            return;
            // Recuerdo que cada vez que se ejecuta un return se sale del metodo, da igual si es void o no
        }

        // Si no se ha salido del método, seguimos pidiendo el código del vuelo
        System.out.print("Código de vuelo: ");
        String codigo = scanner.nextLine();

        // Buscamos el vuelo en la lista de vuelos, iterando sobre ella
        for (Vuelo vuelo : vuelos) {
            // Si encontramos el vuelo, intentamos añadir la reserva
            if (vuelo.getCodigo().equals(codigo)) {
                boolean reservado = vuelo.agregarReserva(pasajero); // agregar reserva devuelve un booleano
                // Mostramos un mensaje dependiendo de si se ha reservado o no
                if (reservado) {
                    System.out.println("Reserva confirmada.");
                } else {
                    System.out.println("Vuelo lleno, añadido a la lista de espera.");
                }
                return; // salimos del método
            }
        }
        // Si no se ha encontrado el vuelo, mostramos un mensaje, nunca se ha salido del método
        System.out.println("Vuelo no encontrado.");
    }

    private static void cancelarReserva() {
        System.out.print("ID Pasajero: ");
        String id = scanner.nextLine();
        Pasajero pasajero = pasajeros.get(id);
        if (pasajero == null) {
            System.out.println("Pasajero no encontrado.");
            return;
        }

        System.out.print("Código de vuelo: ");
        String codigo = scanner.nextLine();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigo().equals(codigo)) {
                boolean cancelado = vuelo.cancelarReserva(pasajero);
                if (cancelado) {
                    System.out.println("Reserva cancelada.");
                } else {
                    System.out.println("No se encontró reserva.");
                }
                return;
            }
        }
        System.out.println("Vuelo no encontrado.");
    }

    private static void mostrarVuelos() {
        // Rápido y sencillo, no hace falta más
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo); //Al printear un objeto, se llama al método toString automáticamente
        }
    }

    private static void mostrarReservas() {
        System.out.print("Código de vuelo: ");
        String codigo = scanner.nextLine();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigo().equals(codigo)) {
                System.out.println("Reservas: " + vuelo.getReservas());
                System.out.println("Lista de espera: " + vuelo.getListaEspera());
                return;
            }
        }
        System.out.println("Vuelo no encontrado.");
    }
}
