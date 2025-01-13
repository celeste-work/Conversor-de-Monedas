import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner usuarioEscribe = new Scanner(System.in);
        Solicitudes solicitud = new Solicitudes();
        boolean continuar = true;

        String menu = """
                1- Dolar a Pesos Argentinos
                2- Pesos Argentinos a Dolar
                3- Dolar a Real Brasileño
                4- Real Brasileño a Dolar
                5- Dolar a Pesos Colombianos
                6- Pesos Colombianos a Dolar
                7- Salir
                """;
        while (continuar) {
            System.out.println(menu);
            System.out.print("Elige una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(usuarioEscribe.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> realizarConversion(solicitud, "USD", "ARS", usuarioEscribe);
                case 2 -> realizarConversion(solicitud, "ARS", "USD", usuarioEscribe);
                case 3 -> realizarConversion(solicitud, "USD", "BRL", usuarioEscribe);
                case 4 -> realizarConversion(solicitud, "BRL", "USD", usuarioEscribe);
                case 5 -> realizarConversion(solicitud, "USD", "COP", usuarioEscribe);
                case 6 -> realizarConversion(solicitud, "COP", "USD", usuarioEscribe);
                case 7 -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void realizarConversion(Solicitudes solicitud, String monedaBase, String monedaDestino, Scanner scanner) {
        System.out.print("Ingresa el monto a convertir: ");

        try {
            double monto = Double.parseDouble(scanner.nextLine());
            double resultado = solicitud.convertirMoneda(monto, monedaBase, monedaDestino);
            System.out.printf("El resultado de convertir %.2f %s a %s es: %.2f %s%n", monto, monedaBase, monedaDestino, resultado, monedaDestino);

        } catch (NumberFormatException e) {
            System.out.println("Monto inválido. Intenta de nuevo.");
        } catch (RuntimeException e) {
            System.out.println("Error durante la conversión: " + e.getMessage());
        }
    }
}
