package Ficheros.EjercicioLog;

import java.io.*;
import java.util.*;

public class AnalizadorLogs {
    public static void main(String[] args) {
        // Fichero de entrada con los logs
        String inputFile = "Ficheros/EjercicioLog/access.log";
        
        // Mapas para contar accesos por IP y códigos de respuesta.
        // Usamos LinkedHashMap para conservar el orden de aparición (en caso de empate en el número de accesos).
        Map<String, Integer> ipCount = new LinkedHashMap<>();
        Map<String, Integer> codeCount = new HashMap<>();
        
        // Lectura del fichero línea a línea
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Se ignoran líneas vacías
                if (line.trim().isEmpty()) {
                    continue;
                    // Continue se usa para saltar a la siguiente iteración del bucle.
                    // En este caso, si la línea está vacía, no se procesa y se pasa a la siguiente.
                }
                
                /*
                 * Formato de cada línea:
                 * IP - - [Fecha] "Método URL Protocolo" CódigoRespuesta Bytes
                 * 
                 * Por ejemplo:
                 * 192.168.1.1 - - [2025-01-27 10:15:32] "GET /index.html HTTP/1.1" 200 512
                 * 
                 * Se aprovecha que el método y la URL están entre comillas ("")
                 * para separar la línea en partes.
                 */

                String[] parts = line.split("\"");
                if (parts.length < 3) {
                    // Si la línea no cumple el formato esperado, se salta.
                    continue;
                    // Recordamos que continue se usa para saltar a la siguiente iteración del bucle.
                }
                
                // La primera parte (antes de las comillas) contiene la IP y la fecha.
                // Separamos por espacios y la primera cadena es la IP.
                
                String[] inicio = parts[0].trim().split(" ");
                // Recordar que se pueden encadenar todos los métodos que queramos.
                // .trim elimina los espacios en blanco al principio y al final de la cadena.
                // .split divide la cadena en partes, en este caso por espacios.

                String ip = inicio[0];
                
                // La parte que está después de las comillas contiene el código de respuesta y los bytes.
                String[] fin = parts[2].trim().split(" ");
                // El primer elemento es el código de respuesta.
                String codigoRespuesta = fin[0];
                
                // Actualizamos el conteo para la IP
                if(ipCount.containsKey(ip)){
                    ipCount.put(ip, ipCount.get(ip) + 1);
                } else {
                    ipCount.put(ip, 1);
                }

                // Actualizamos el conteo para el código de respuesta
                if(codeCount.containsKey(codigoRespuesta)){
                    codeCount.put(codigoRespuesta, codeCount.get(codigoRespuesta) + 1);
                } else {
                    codeCount.put(codigoRespuesta, 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Hacemos una copia del mapa para no modificar el original
        Map<String, Integer> copiaIpCount = new HashMap<>(ipCount);
        // asi duplicamos el mapa ipCount en copiaIpCount
       
        System.out.println("IPs ordenadas por accesos:");
        int posicion = 1;
        String topIP = "";
        int topIPCount = 0;
        
        // Mientras la copia tenga elementos, se busca la IP con mayor número de accesos
        while (!copiaIpCount.isEmpty()) {
            String maxIp = null;
            int maxCount = -1;
            // Recorremos las claves del mapa
            for (String ip : copiaIpCount.keySet()) {
                int count = copiaIpCount.get(ip);
                if (count > maxCount) {
                    maxCount = count;
                    maxIp = ip;
                }
            }
            // Guardamos la primera IP encontrada (la de mayor accesos) para el resumen
            if (posicion == 1) {
                topIP = maxIp;
                topIPCount = maxCount;
            }

            // Mostramos la IP y el número de accesos
            System.out.println(posicion + ". " + maxIp + " - " + maxCount + " accesos");

            // Eliminamos la IP procesada para no volver a considerarla
            copiaIpCount.remove(maxIp);

            // Siguiente posición
            posicion++;
        }
        // Salto de línea
        System.out.println();
        
        // Identificar el código de respuesta más frecuente
        String codigoMasFrecuente = "";
        int maxCodigoCount = 0;
        for (String code : codeCount.keySet()) {
            int count = codeCount.get(code);
            if (count > maxCodigoCount) {
                maxCodigoCount = count;
                codigoMasFrecuente = code;
            }
        }

        System.out.println("Código de respuesta más frecuente: " + codigoMasFrecuente + " (" + maxCodigoCount + " veces)");
        
        // Guardar en el fichero resumen_logs.txt los datos solicitados.
        // Se escribe:
        // 1. La IP con más accesos y el número total de accesos.
        // 2. El código de respuesta más frecuente y cuántas veces se repite.
        String outputFile = "resumen_logs.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            writer.println(topIP + " - " + topIPCount + " accesos");
            writer.println("Código de respuesta más frecuente: " + codigoMasFrecuente + " (" + maxCodigoCount + " veces)");
            
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}