import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        Restaurante restaurante = new Restaurante();

        do {
            System.out.println("\nMenú de Gestión de Restaurante");
            System.out.println("1. Registrar Mesa");
            System.out.println("2. Registrar Plato");
            System.out.println("3. Registrar Pedido");
            System.out.println("4. Modificar Pedido");
            System.out.println("5. Borrar Pedido");
            System.out.println("6. Modificar o Borrar Plato");
            System.out.println("7. Guardar Datos");
            System.out.println("8. Cargar Datos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    restaurante.registrarMesa();
                }
                case 2 -> {
                    //registrarPlato();
                }
                case 3 -> {
                    //registrarPedido();
                }
                case 4 -> {
                    //modificarPedido();
                }
                case 5 -> {
                    //borrarPedido();
                }
                case 6 -> {
                    //modificarBorrarPlato();
                }
                case 7 -> {
                    //guardarDatos();
                }
                case 8 -> {
                    //cargarDatos();
                }
                case 9 -> {
                    //guardarDatos();
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
            
        } while (opcion != 9);
    }
}
