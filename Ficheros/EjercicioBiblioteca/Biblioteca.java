package Ficheros.EjercicioBiblioteca;
import java.io.*;
import java.util.*;

public class Biblioteca {
    public static void main(String[] args) {
        // Rutas
        String inputFile = "Ficheros/EjercicioBiblioteca/libros.txt";

        // Mapa para clasificar los libros por categoría
        Map<String, List<String>> librosPorCategoria = new HashMap<>();

        // Leer el archivo línea a línea
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                // 0: Título, 1: Autor, 2: Categoría, 3: Año de Publicación
                String titulo = data[0];
                String autor = data[1];
                String categoria = data[2];
                String ano = data[3];
                String infoLibro = titulo + " - " + autor + " (" + ano + ")";

                // Almacenar los libros por categoría en el mapa
                if (librosPorCategoria.containsKey(categoria)) {
                    librosPorCategoria.get(categoria).add(infoLibro);
                } else {
                    List<String> libros = new ArrayList<>();
                    libros.add(infoLibro);
                    librosPorCategoria.put(categoria, libros);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return; // Usamos return para salir del método (programa) si hay un error (es decir, no continuamos con el código)
        }

        // Determinar la categoría con más libros
        String categoriaConMasLibros = "";
        int maxLibros = 0;
        for (String key : librosPorCategoria.keySet()) {
            int numLibros = librosPorCategoria.get(key).size();
            if (numLibros > maxLibros) {
                maxLibros = numLibros;
                categoriaConMasLibros = key;
            }
        }

        // Escribir los libros por categoría en archivos distintos y mostrar la categoría con más libros
        for (String key : librosPorCategoria.keySet()) {
            String outputFile = "Ficheros/EjercicioBiblioteca/" + key + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
                for (String libro : librosPorCategoria.get(key)) {
                    writer.println(libro);
                }
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo: " + e.getMessage());
                return;
            }
        }
        // Mostrar en consola la categoría con más libros
        System.out.println("Categoría con más libros: " + categoriaConMasLibros + " (" + maxLibros + " libros)");
    }
}
