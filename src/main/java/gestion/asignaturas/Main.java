package gestion.asignaturas;

import gestion.asignaturas.servicios.GestionAsignaturasService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionAsignaturasService gestionService = new GestionAsignaturasService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:\n1. Listar asignaturas\n2. Eliminar registro\n3. Revivir registro\n4. Compactar archivo\n5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionService.listarAsignaturas();
                    break;
                case 2:
                    System.out.print("Ingrese el código de la asignatura a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    gestionService.eliminarRegistro(codigoEliminar);
                    break;
                case 3:
                    System.out.print("Ingrese el código de la asignatura a revivir: ");
                    String codigoRevivir = scanner.nextLine();
                    gestionService.revivirRegistro(codigoRevivir);
                    break;
                case 4:
                    gestionService.compactarArchivo();
                    System.out.println("Archivo Compactado. Listando asignaturas resultantes...");
                    gestionService.listarAsignaturas();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

    }
}