package gestion.asignaturas.servicios;

import gestion.asignaturas.modelo.Asignatura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoAsignaturas {
    private static final String ARCHIVO = "src/main/resources/asignaturas.txt";
    private static final String ARCHIVO_TEMPORAL = "src/main/resources/asignaturas_temp.txt";

    public List<Asignatura> cargarAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    boolean borrado = Boolean.parseBoolean(partes[2]);
                    asignaturas.add(new Asignatura(codigo, nombre, borrado));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar asignaturas: " + e.getMessage());
        }
        return asignaturas;
    }

    public void guardarAsignaturas(List<Asignatura> asignaturas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Asignatura asignatura : asignaturas) {
                writer.write(asignatura.getCodigo() + "," + asignatura.getNombre() + "," + asignatura.isBorrado());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar asignaturas: " + e.getMessage());
        }
    }

    public void compactarArchivo() {
        List<Asignatura> asignaturas = cargarAsignaturas();

        // Creacion del archivo temporal y escritura de asignaturas no borradas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_TEMPORAL))) {
            for (Asignatura asignatura : asignaturas) {
                if (!asignatura.isBorrado()) {
                    writer.write(asignatura.getCodigo() + "," + asignatura.getNombre() + ",false");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo temporal: " + e.getMessage());
            return;
        }

        // Elimino el archivo original
        try {
            Path path = Paths.get(ARCHIVO);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo original: " + e.getMessage());
            return;
        }

        // Renombro el archivo temporal para que tenga el nombre del archivo original
        try {
            Path source = Paths.get(ARCHIVO_TEMPORAL);
            Path target = Paths.get(ARCHIVO);
            Files.move(source, target);
        } catch (IOException e) {
            System.out.println("Error al renombrar el archivo temporal: " + e.getMessage());
        }
    }
}
