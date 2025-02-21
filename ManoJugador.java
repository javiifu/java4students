package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManoJugador {
private List<Carta> cartasRecursos;
    private List<Carta> cartasDesarrollo;
    //Constructor. 
    public ManoJugador() {
        cartasRecursos = new ArrayList<>();
        cartasDesarrollo = new ArrayList<>();
    }

    public void agregarCartaRecurso(Carta carta) {
            cartasRecursos.add(carta);
    }

    public void agregarCartaDesarrollo(Carta carta) {
            cartasDesarrollo.add(carta);
        
    }

    public boolean usarCartas(List<String> recursosNecesarios) {
        Map<String, Integer> recursosDisponibles = new HashMap<>();
        for (Carta carta : cartasRecursos) {
            String nombre = carta.getNombre();
            recursosDisponibles.put(nombre, recursosDisponibles.getOrDefault(nombre, 0) + 1);
        }

        for (String recurso : recursosNecesarios) {
            if (!recursosDisponibles.containsKey(recurso) || recursosDisponibles.get(recurso) <= 0) {
                System.out.println("No tienes suficientes recursos de: " + recurso);
                return false;
            }
            recursosDisponibles.put(recurso, recursosDisponibles.get(recurso) - 1);
        }

        for (String recurso : recursosNecesarios) {
            for (int i = 0; i < cartasRecursos.size(); i++) {
                if (cartasRecursos.get(i).getNombre().equals(recurso)) {
                    cartasRecursos.remove(i);
                    break;
                }
            }
        }

        return true;
    }

    public void mostrarMano() {
        System.out.println("Recursos en mano:");
        Map<String, Integer> recursosContados = new HashMap<>();
        for (Carta carta : cartasRecursos) {
            String nombre = carta.getNombre();
            recursosContados.put(nombre, recursosContados.getOrDefault(nombre, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : recursosContados.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Cartas de desarrollo:");
        Map<String, Integer> desarrolloContados = new HashMap<>(); //Cambiar nombre de variable de Hashmap. 
        for (Carta carta : cartasDesarrollo) {
            String nombre = carta.getNombre();
            desarrolloContados.put(nombre, desarrolloContados.getOrDefault(nombre, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : desarrolloContados.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void recibirCarta(Carta carta) {
        if (carta.getTipo() == Carta.Tipocarta.Recurso) {
            agregarCartaRecurso(carta);
        } else {
            agregarCartaDesarrollo(carta);
        }
    }

    public List<Carta> getCartasRecursos() {
        return cartasRecursos;
    }

    public List<Carta> getCartasDesarrollo() {
        return cartasDesarrollo;
    }
}
