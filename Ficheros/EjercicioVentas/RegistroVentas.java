package Ficheros.EjercicioVentas;
import java.io.*;
import java.util.*;

public class RegistroVentas {
    public static void main(String[] args) {

        // Rutas
        String inputFile = "Ficheros/ejerciciosfinales/ventas.txt";
        String outputFile = "Ficheros/ejerciciosfinales/resumen_ventas.txt";

        // Mapa para almacenar los ingresos por producto
        Map<String, Double> ingresosPorProducto = new HashMap<>();

        // creamos un try con recursos para que se cierren los archivos automáticamente
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            // Leer el archivo línea a línea
            String line;
            while ((line = reader.readLine()) != null) { //Ponemos null porque line es string, -1 sería para otros tipos de datos

                // Separar los datos de la línea
                // 0: fecha, 1: producto, 2: cantidad, 3: precio
                String[] data = line.split(";");
                
                String producto = data[1];
                int cantidad = Integer.parseInt(data[2]);
                double precio = Double.parseDouble(data[3]);

                // Calcular los ingresos por producto
                double ingresos = cantidad * precio;

                // Almacenar los ingresos por producto en el mapa
                if (!ingresosPorProducto.containsKey(producto)) {
                    ingresosPorProducto.put(producto, ingresos);
                } else {
                    ingresosPorProducto.put(producto, (ingresosPorProducto.get(producto) + ingresos));
                }
            }

            // Calcular el mayor ingreso
            double mayorIngreso = 0;
            String productoMayorIngreso = "";
            for (String key : ingresosPorProducto.keySet()) {
                // Comparar todas las keys para encontrar el mayor ingreso
                if (ingresosPorProducto.get(key) > mayorIngreso) {
                    mayorIngreso = ingresosPorProducto.get(key);
                    productoMayorIngreso = key;
                }
            }

            // Escribir el producto con mayor ingreso en el archivo de salida
            writer.println("Producto con mayor ingreso: " + productoMayorIngreso + " (" + mayorIngreso + "€)");


            // Escribir los ingresos por producto en el archivo de salida
            for (String key : ingresosPorProducto.keySet()) {
                writer.println(key + ": " + ingresosPorProducto.get(key));
            }

        } catch (IOException e) {
            System.out.println("Error al procesar los archivos: " + e.getMessage());
        }
    }
}