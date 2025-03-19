import java.util.LinkedList;
import java.util.Scanner;

public class Restaurante {
    // Atributos especiales
    Scanner sc = new Scanner(System.in);

    // Atributos Restaurante
    LinkedList<Mesa> mesas = new LinkedList<>();  
    LinkedList<Plato> platos = new LinkedList<>();
    LinkedList<Pedido> pedidos = new LinkedList<>();
    
    public void registrarMesa() {
        int numeroMesa;
        int capacidad;

        do { 
            System.out.println("Ingresa un número de mesa: ");
            numeroMesa = sc.nextInt();
        } while (numeroMesa <= 0);

        do { 
            System.out.println("Ingresa la capacidad de la mesa: ");
            capacidad = sc.nextInt();
        } while (capacidad <= 0);

        Mesa mesa = new Mesa(numeroMesa, capacidad);
        mesas.add(mesa);

        System.out.println("Mesa creada correctamente!");
        
    }

    public void registrarPlato() {
        String codigoPlato; // P-01
        String nombrePlato;
        double precioPlato;

        System.out.println("Ingresa un código del plato: ");
        codigoPlato = sc.nextLine();
        sc.next();

        System.out.println("Ingresa un nombre del plato: ");
        nombrePlato = sc.nextLine();
        sc.next();

        do { 
            System.out.println("Ingresa un precio del plato: ");
            precioPlato = sc.nextDouble();
        } while (precioPlato <= 0);

        Plato plato = new Plato(codigoPlato, nombrePlato, precioPlato);
        platos.add(plato);

        System.out.println("Plato creado correctamente!");
    }

    public void registrarPedido() {
        Mesa mesaEncontrada = null;
        int numeroMesa;
        do { 
            System.out.println("Introduce el número de una mesa existente: ");
            numeroMesa = sc.nextInt();
            for (Mesa mesa : this.mesas) {
                if (numeroMesa == mesa.getNumero()) {
                    mesaEncontrada = mesa;
                    break;
                }
            }
            if (mesaEncontrada == null) {
                System.out.println("No se ha encontrado esa mesa");
            }
        } while (mesaEncontrada == null);

        LinkedList<Plato> listaPlatosPedidos = new LinkedList<Plato>();
        String codigo;
        boolean terminar = false;

        while(!terminar) {
            System.out.println("Introduce el código de los platos: ");
            System.out.println("Si se introduce un 0, parará de preguntar: ");
            codigo = sc.next();

<<<<<<< HEAD
            if(codigo.equals("0")) {
=======
            //Futuro fix, no sale
            if(codigo.equals('0')) {
>>>>>>> cdfe2d3eadcec4900886374198814c914229054a
                terminar = true;
            } else {
                for (Plato plato : this.platos) {
                    if (codigo.equals(plato.getCodigo())) {
                        listaPlatosPedidos.add(plato);
                        break;
                    }
                }
            }
        }
        
        Pedido pedido = new Pedido(mesaEncontrada, listaPlatosPedidos);
        pedidos.add(pedido);
        System.out.println("Pedido añadido correctamente");
    }
}
