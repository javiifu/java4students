import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Persistencia {

    // Atributos constantes se suelen escribir en mayúsculas, es una convención de nombres y una buena práctica
    // - RUTA: String (constante) que almacena la ruta del archivo de datos
    // private sirve para que solo la clase pueda acceder a la variable
    // static sirve para que la variable sea compartida por todas las instancias de la clase (objetos)
    // final sirve para que la variable sea constante y no pueda ser modificada
    private static final String RUTA = "POO/PreExamen/reservas.dat";

    // Métodos
    //  guardarDatos: void (static) (HashMap<String, Pasajero>, ArrayList<Vuelo>) -> void
    //  Guarda los datos de pasajeros y vuelos en un archivo de datos
    //  static sirve para que el método sea de la clase y no del objeto
    //  ObjectOutputStream sirve para escribir objetos en un archivo
    //  FileOutputStream sirve para escribir bytes en un archivo
    //  HashMap<String, Pasajero> es un mapa de pasajeros con clave String y valor Pasajero
    //  ArrayList<Vuelo> es una lista de vuelos
    //  try-catch es un bloque de código que intenta ejecutar un código y captura cualquier excepción que ocurra
    //  IOException es una excepción que se lanza cuando ocurre un error de entrada/salida
    public static void guardarDatos(HashMap<String, Pasajero> pasajeros, ArrayList<Vuelo> vuelos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            out.writeObject(pasajeros);
            out.writeObject(vuelos);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar datos.");
        }
    }

    //  cargarDatos: void (static) (HashMap<String, Pasajero>, ArrayList<Vuelo>) -> void
    //  Carga los datos de pasajeros y vuelos desde un archivo de datos
    //  ObjectInputStream sirve para leer objetos de un archivo
    //  FileInputStream sirve para leer bytes de un archivo
    //  Recordemos que cuando usamos dos Input Stream, el primero que escribimos es el "filtro" de la entrada binaria
    public static void cargarDatos(HashMap<String, Pasajero> pasajeros, ArrayList<Vuelo> vuelos) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RUTA))) {
            pasajeros.clear(); // Limpiamos el mapa de pasajeros
            pasajeros.putAll((HashMap<String, Pasajero>) in.readObject()); // Añadimos todos los pasajeros del archivo
            vuelos.clear(); // Limpiamos la lista de vuelos
            vuelos.addAll((ArrayList<Vuelo>) in.readObject()); // Añadimos todos los vuelos del archivo

            // in.readObject() devuelve un Object, por lo que hay que hacer un cast a HashMap<String, Pasajero> y ArrayList<Vuelo>
            // Cast es una conversión de un tipo de dato a otro
            // Es decir, forzamos a que el objeto devuelto sea de un tipo concreto
            // Es obligatorio ir en el orden en el que se escribieron los objetos en el archivo

            // También se podrían separar en dos lineas
            // HashMap<String, Pasajero> pasajerosGuardados = (HashMap<String, Pasajero>) in.readObject();
            // pasajeros.putAll(pasajerosGuardados);
            // putAll añade todos los elementos de un mapa a otro mapa

            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se encontraron datos previos.");
        }
    }
}
