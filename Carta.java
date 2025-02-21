package proyecto;

public class Carta{
    
    //Creamos un enumarado para diferenciar los tipos de carta que hay dentro del juego. 
    public enum Tipocarta {Recurso, Desarrollo};

    //Atributos
    private String nombre; //En privado???
    private Tipocarta tipo;
    //Creamos el constructor. 
    public Carta (String nombre, Tipocarta tipo){
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre(){
        return nombre;


    }
    public Tipocarta getTipo() {
        return tipo;
    }
    
    
}