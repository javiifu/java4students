package proyecto;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    //Atributos
    
    private ArrayList<Carta> cartasRecurso;
    private ArrayList<Carta> cartasDesarrollo;
    
    //Creamos la funcion que meta las cartas del catan en el mazo.
    private void agregarCartasRecurso(String nombre, Carta.Tipocarta tipo, int cantidad){
        for (int i = 0; i < cantidad; i++){
            cartasRecurso.add(new Carta(nombre, tipo));
        }
    }
    private void agregarCartasDesarrollo(String nombre, Carta.Tipocarta tipo, int cantidad){
        for (int i = 0; i < cantidad; i++){
            cartasDesarrollo.add(new Carta(nombre, tipo));
        }
    }

    public void inicializarcartas(){

        //Agregamos las cartas de recursos con la función que hemos creado antes. . 
        agregarCartasRecurso("Ladrillo", Carta.Tipocarta.Recurso, 19);
        agregarCartasRecurso("Madera", Carta.Tipocarta.Recurso, 19);
        agregarCartasRecurso("Oveja", Carta.Tipocarta.Recurso, 19);
        agregarCartasRecurso("Trigo", Carta.Tipocarta.Recurso, 19);
        agregarCartasRecurso("Piedra", Carta.Tipocarta.Recurso, 19);
        //Agregamos las cartas de desarrollo también;
        agregarCartasDesarrollo("Caballero", Carta.Tipocarta.Desarrollo, 14);
        agregarCartasDesarrollo("Carreteras", Carta.Tipocarta.Desarrollo, 2);
        agregarCartasDesarrollo("Monopolio", Carta.Tipocarta.Desarrollo, 2);
        agregarCartasDesarrollo("Año de Abundancia", Carta.Tipocarta.Desarrollo, 2);
        agregarCartasDesarrollo("Punto de Victoria", Carta.Tipocarta.Desarrollo, 5);
    }
    //Creamos el mazo. 
    //Con la lista de cartas y la funcion que inicializa las cartas.
    public Mazo(){
        cartasRecurso = new ArrayList<Carta>();
        cartasDesarrollo = new ArrayList<Carta>();
        inicializarcartas();
        barajearcartasdesarrollo();
    }

    //Con la funcion .shuffle bajeamos las cartas esta función se encuentra de la liberería collections. 

    public void barajearcartasdesarrollo(){
        Collections.shuffle(cartasDesarrollo);
    }
    public Carta robarCarta(){
        return cartasDesarrollo.remove(0);
    }
}
