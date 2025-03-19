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

            if(codigo.equals("0")) {
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

    private Pedido getPedidoNumeroMesa() {
        if (!this.pedidos.isEmpty()) {
            do {
                System.out.println("Introduce un número de mesa asociado al pedido: ");
                int numero = sc.nextInt();
                for (Pedido pedido : this.pedidos) {
                    if (numero == pedido.getNumeroMesa()) {
                        return pedido;
                    }
                }
                System.out.println("Introduce un número válido");
            } while (true);

        }
        return null;
    }

    public void menuModificarPedido() {
        Pedido pedido = this.getPedidoNumeroMesa();
        int opcion;
        do {        
            System.out.println("Que quieres hacer?:");
            System.out.println("1. Cambiar estado (Completado/No Completado).");
            System.out.println("2. Añadir Plato.");
            System.out.println("3. Borrar Plato.");
            System.out.println("4. Cambiar Mesa");
            System.out.println("5. Aplicar Descuento.");
            System.out.println("6. Borrar Pedido.");
            System.out.println("7. Salir");
            System.out.println("Introduce una opción: ");
            opcion = sc.nextInt();
        } while (opcion != 7);

        switch (opcion) {
            case 1 -> {pedido.cambiarCompletado();}
            case 2 -> {
                
                Plato plato = new Plato();
                pedido.addPlato(plato);
            }
        }
    }

    public void modificarPedido(Pedido pedido) {
        
    }

}
