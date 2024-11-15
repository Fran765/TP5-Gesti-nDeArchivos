package gestion.asignaturas.servicios;

import gestion.asignaturas.modelo.Asignatura;
import java.util.List;

public class GestionAsignaturasService {

    private final ArchivoAsignaturas archivoAsignaturas;

    public GestionAsignaturasService() {
        this.archivoAsignaturas = new ArchivoAsignaturas();
    }

    public void eliminarRegistro(String codigo) {
        List<Asignatura> asignaturas = archivoAsignaturas.cargarAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo) && !asignatura.isBorrado()) {
                asignatura.setBorrado(true);
                System.out.println("Asignatura eliminada: " + asignatura.getNombre());
                archivoAsignaturas.guardarAsignaturas(asignaturas);
                return;
            }
        }
        System.out.println("Asignatura no encontrada o ya eliminada.");
    }

    public void revivirRegistro(String codigo) {
        List<Asignatura> asignaturas = archivoAsignaturas.cargarAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo) && asignatura.isBorrado()) {
                asignatura.setBorrado(false);
                System.out.println("Asignatura revivida: " + asignatura.getNombre());
                archivoAsignaturas.guardarAsignaturas(asignaturas);
                return;
            }
        }
        System.out.println("Asignatura no encontrada o no está eliminada.");
    }

    public void listarAsignaturas() {
        List<Asignatura> asignaturas = archivoAsignaturas.cargarAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura);
        }
    }

    public void compactarArchivo() {
        archivoAsignaturas.compactarArchivo();
    }
}
