package proyecto;

public class Vertice {
    private int id;
    private String tipoConstruccion; // "vacio", "poblado", "ciudad"
    private Jugador propietario;

    public Vertice(int id) {
        this.id = id;
        this.tipoConstruccion = "vacio";
        this.propietario = null;
    }

    public boolean construirPoblado(Jugador jugador, boolean inicio) {
        if (tipoConstruccion.equals("vacio") && inicio) {
            this.tipoConstruccion = "poblado";
            this.propietario = jugador;
            return true;
        } else if (tipoConstruccion.equals("vacio")) {
            this.tipoConstruccion = "poblado";
            this.propietario = jugador;
            return true;
        }
        return false;
    }

    public boolean mejorarCiudad() {
        if (tipoConstruccion.equals("poblado") && propietario != null) {
            this.tipoConstruccion = "ciudad";
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getTipoConstruccion() {
        return tipoConstruccion;
    }

    public Jugador getPropietario() {
        return propietario;
    }
}
