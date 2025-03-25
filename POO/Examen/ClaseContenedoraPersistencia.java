import java.io.Serializable;
import java.util.LinkedList;

public class ClaseContenedoraPersistencia implements Serializable   {
    //Creamos aquí como atributos lo que queremos serializar 
    private LinkedList<Pedido> pedidos;
    private LinkedList<Mesa> mesas; 
    private LinkedList<Plato> platos;

    public ClaseContenedoraPersistencia(LinkedList<Pedido> pedidos, LinkedList<Mesa> mesas, LinkedList<Plato> platos) {
        this.pedidos = pedidos;
        this.mesas = mesas;
        this.platos = platos;
    }
    
    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }
    public LinkedList<Mesa> getMesas() {
        return mesas;
    }
    public LinkedList<Plato> getPlatos() {
        return platos;
    }
}
