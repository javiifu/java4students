import java.util.LinkedList;

public class Pedido {

    Mesa mesaAsociada;
    LinkedList<Plato> listaPlatos;
    boolean estaCompletado;
    double precioTotal;
    

    public Pedido(Mesa mesa, LinkedList<Plato> listaPlatos) {
        this.mesaAsociada = mesa;
        this.listaPlatos = listaPlatos;
        this.estaCompletado = false;
        this.precioTotal = this.calcularPrecioTotal();
        
    }


    public int getNumeroMesa() { return mesaAsociada.getNumero(); }
    public void cambiarCompletado() { this.estaCompletado = !this.estaCompletado; }

    private double calcularPrecioTotal() {
        double precioTotal = 0;

        for (Plato plato : this.listaPlatos) {
            precioTotal += plato.getPrecio();
        }

        return precioTotal;
    }

}



