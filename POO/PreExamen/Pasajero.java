import java.io.Serializable;


// Pasajero: Clase que representa un pasajero
// Serializable es una interfaz que indica que la clase se puede serializar
// Serializar es convertir un objeto en un flujo de bytes para almacenarlo en un archivo o enviarlo por la red
// Es decir, guardar un objeto en un archivo
// Se usa para guardar objetos en archivos, enviar objetos por la red, etc.
// Se usa en conjunto con ObjectOutputStream y ObjectInputStream

public class Pasajero implements Serializable {
    // Atributos
    private String id;
    private String nombre;
    private int edad;

    // Constructor
    public Pasajero(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Métodos de acceso
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    // Devuelve una cadena con los datos del pasajero
    // Se llama automáticamente cuando se imprime un objeto
    // @Override indica que estamos sobreescribiendo un método de la clase padre (Object, que es la clase base de todas las clases)
    // Es una buena práctica usar @Override para indicar que estamos sobreescribiendo un método
    // Si no se usa, el código funcionará igual, pero es una buena práctica
    // Hay mas información de este bloque en Vuelo.java

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ", Edad: " + edad + ")";
    }
}
