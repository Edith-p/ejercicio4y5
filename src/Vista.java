import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("""
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                              RENTAMOVIL 
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                1. Registrar vehiculo
                2. Registrar cliente
                3. Consultar flota
                4. Consultar clientes
                5. Cotizar alquiler
                6. Confirmar alquiler
                7. Registrar devolucion
                8. Finalizar mantenimiento
                9. Reporte de vehiculos
                10. Reporte de ingresos
                11. Reporte de descuentos
                12. Alquileres activos
                13. Historial de cliente
                0. Salir
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                """);
    }

    public String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    public double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}