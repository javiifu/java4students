import java.io.*;
import java.util.LinkedList;

public class Persistencia {
    //Creamos la para empezar el atributo de la Ruta donde vamos a guardar el archivo
    private static final String RUTA = "POO/Examen/restaurante.dat";

    //Creamos el método para guardar datos.Creo que voy guardar solo las mesas, los pedidos y los platos (menú) 
    public static void guardarDatos (LinkedList<Pedido> pedidos, LinkedList<Mesa> mesas, LinkedList<Plato> platos) {
        //De momento vamos a realizarlo de la misma manera 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RUTA))) {

            //Ahora vamos a guardar los datos de las mesas, pedidos y platos a partir de la clase de ClaseContenedoraPersistencia atrayendo un nuevo objeto. 

            ClaseContenedoraPersistencia datos = new ClaseContenedoraPersistencia(pedidos, mesas, platos);
            out.writeObject(datos);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar datos."+ e.getMessage());
        }
    }
    //Metodos para cargar los datos. 
    public static void cargarDatos(LinkedList<Pedido> pedidos, LinkedList<Mesa> mesas, LinkedList<Plato> platos) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RUTA))) {
            ClaseContenedoraPersistencia datos = (ClaseContenedoraPersistencia) in.readObject();
            //Creamos un if para que si no hay datos previos, no se cargue nada y evitamos excepciones (NullPointerException)
            if (datos != null){
            pedidos.clear();
            mesas.clear();
            platos.clear();
            pedidos.addAll(datos.getPedidos());
            mesas.addAll(datos.getMesas());
            platos.addAll(datos.getPlatos());
            System.out.println("Datos cargados correctamente.");
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se encontraron datos previos."+ e.getMessage());
        }
    }
}
