import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

// Sé que Queue no lo hemos visto, pero es otro tipo de colección que se usa en la práctica
// Queue es una interfaz que representa una cola de elementos
// LinkedList es una implementación de Queue
// Queue es una estructura de datos FIFO (First In, First Out)
// Es decir, el primer elemento que entra es el primero en salir
// LinkedList es una lista doblemente enlazada, lo que significa que cada elemento tiene una referencia al siguiente y al anterior
// Esto permite que se puedan añadir y eliminar elementos de la lista de forma eficiente
// Queue tiene varios métodos útiles como add, remove, poll, peek, etc. (Similar a Stack)
// En este caso, se usa para guardar las reservas y la lista de espera de un vuelo
// Se podría haber usado una lista normal, pero Queue es más adecuado para este caso
// Si no se entiende, no pasa nada, es un concepto avanzado que no es necesario para el examen
// Pero si se entiende, mejor que mejor

// Más información sobre Serializable en Pasajero.java

// Vuelo: Clase que representa un vuelo
public class Vuelo implements Serializable {
    // Atributos
    private String codigo; // Código del vuelo
    private String origen; // Origen del vuelo
    private String destino; // Destino del vuelo     
    private String fecha; // Fecha del vuelo
    private int capacidad; // Capacidad del vuelo
    private Queue<Pasajero> reservas; // Cola de reservas 
    private Queue<Pasajero> listaEspera; // Cola de espera

    // Constructor
    public Vuelo(String codigo, String origen, String destino, String fecha, int capacidad) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;

        // Inicializamos las colas, se puede usar LinkedList porque implementa Queue y son compatibles
        // Prácticamente LinkedList es una Queue (cola), pero con más métodos
        this.reservas = new LinkedList<>(); 
        this.listaEspera = new LinkedList<>();
    }

    // Métodos de acceso
    public String getCodigo() { return codigo; }
    public int getCapacidad() { return capacidad; }
    public int getReservados() { return reservas.size(); }
    public Queue<Pasajero> getReservas() { return reservas; }
    public Queue<Pasajero> getListaEspera() { return listaEspera; }


    // Métodos
    // agregarReserva: boolean (Pasajero) -> boolean
    public boolean agregarReserva(Pasajero pasajero) {
        // Si hay espacio en el vuelo, añadimos la reserva (huecos libres)
        if (reservas.size() < capacidad) { 
            reservas.add(pasajero);
            return true;
        } else {
            listaEspera.add(pasajero);
            return false;
        }
    }

    // cancelarReserva: boolean (Pasajero) -> boolean
    public boolean cancelarReserva(Pasajero pasajero) {
        // Si el pasajero está en la lista de reservas, lo eliminamos
        // El método remove devuelve true si se ha eliminado el elemento
        if (reservas.remove(pasajero)) {
            // Si la lista de espera no está vacía, añadimos el siguiente pasajero a la lista de reservas
            if (!listaEspera.isEmpty()) {
                reservas.add(listaEspera.poll());
                // poll elimina el primer elemento de la cola y lo devuelve
                // Es decir, el primer pasajero de la lista de espera pasa a la lista de reservas
                // Si no se entiende, no pasa nada, es un concepto avanzado que no es necesario para el examen
                // Similar al Stack, pero con una estructura de datos diferente
            }
            return true;
            // Usamos salidas booleanas para gestionar SistemaReservas
        }
        return false;
    }

    // Sobreescribimos el método toString para mostrar la información del vuelo (con @Override)
    // el método toString viene por defecto en todas las clases y se usa para mostrar información de un objeto
    // se puede sobreescribir para mostrar la información de la forma que queramos
    // el método toString se llama automáticamente cuando se imprime un objeto con System.out.print
    // por ejemplo, si hacemos System.out.print(vuelo), se llamará automáticamente a vuelo.toString()
    // en este caso, se muestra el código, origen, destino, fecha y capacidad del vuelo
    // Lo usamos en SistemaReservas para mostrar los vuelos disponibles

    @Override
    public String toString() {
        return codigo + " - " + origen + " → " + destino + " [" + fecha + "] Capacidad: " + capacidad;
    }
}
