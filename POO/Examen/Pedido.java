import java.util.LinkedList;
public class Pedido {

    Mesa mesaAsociada;
    LinkedList<Plato> listaPlatos;
    boolean estaCompletado;
    double precioTotal;
    int descuento;
    

    public Pedido(Mesa mesa, LinkedList<Plato> listaPlatos) {
        this.mesaAsociada = mesa;
        this.listaPlatos = listaPlatos;
        this.estaCompletado = false;
        this.precioTotal = this.calcularPrecioTotal();
        this.descuento = 0;       
    }


    public int getNumeroMesa() { return mesaAsociada.getNumero(); }
    public void cambiarCompletado() { this.estaCompletado = !this.estaCompletado; }
    public void addPlato(Plato plato) { 
        this.listaPlatos.add(plato); 
        this.precioTotal = this.calcularPrecioTotal();
    }
    public boolean removePlato(String codigoPlato) { 
        Plato platoIterado;
        for(int i = 0; i < this.listaPlatos.size(); i++){
            platoIterado = this.listaPlatos.get(i);
            if(platoIterado.getCodigo().equals(codigoPlato)) {
                this.listaPlatos.remove(i);
                this.precioTotal = this.calcularPrecioTotal();
                return true;
            }
        }
        return false;
    }
    public void setMesa(Mesa mesa) { this.mesaAsociada = mesa; }
    public void aplicarDescuento(int descuento) { this.descuento = descuento; }


    private double calcularPrecioTotal() {
        double precioTotal = 0;

        for (Plato plato : this.listaPlatos) {
            precioTotal += plato.getPrecio();
        }

        return (precioTotal * (100-this.descuento) / 100);
    }

   
}



