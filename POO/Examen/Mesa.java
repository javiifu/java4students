import java.io.Serializable;
public class Mesa implements Serializable{
    
    private int numero;
    private int capacidad;

    public Mesa(int numero, int capacidad) {

        this.numero = numero;
        this.capacidad = capacidad;

    }

    public int getNumero() { return this.numero; }

}
