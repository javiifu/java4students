package proyecto;

public class Arista {
    private int id;
    private Vertice verticeA;
    private Vertice verticeB;
    private Jugador propietario;
    private boolean tieneCarretera;

    public Arista(int id, Vertice verticeA, Vertice verticeB) {
        this.id = id;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.tieneCarretera = false;
        this.propietario = null;
    }

    public boolean construirCarretera(Jugador jugador, boolean inicio) {
        if (tieneCarretera) {
            return false;
        }
        if (inicio) {
            if (verticeA.getPropietario() == jugador || verticeB.getPropietario() == jugador) {
                this.tieneCarretera = true;
                this.propietario = jugador;
                return true;
            }
        }
        if (!tieneCarretera && (verticeA.getPropietario() == jugador || verticeB.getPropietario() == jugador || 
        (verticeA.getPropietario() == null && verticeB.getPropietario() == null)) ) {
            this.tieneCarretera = true;
            this.propietario = jugador;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public Vertice getVerticeA() {
        return verticeA;
    }

    public Vertice getVerticeB() {
        return verticeB;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public boolean tieneCarretera() {
        return tieneCarretera;
    }
}
