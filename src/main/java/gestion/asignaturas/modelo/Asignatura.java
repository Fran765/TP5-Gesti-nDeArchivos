package gestion.asignaturas.modelo;

public class Asignatura {

    private String codigo;
    private String nombre;
    private boolean borrado;

    public Asignatura(String codigo, String nombre, boolean borrado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.borrado = borrado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return codigo + "\t" + nombre + "\t" + (borrado ? "Eliminado" : "Activo");
    }
}
