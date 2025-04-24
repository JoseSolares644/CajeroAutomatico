            /* NOMBRE: José Roberto Solares Lara.
            * CARNET: 0900-24-2718.
            * CURSO: Programación 1.
            * SECCIÓN: "B".
            * AULA: CS-3.
            * FECHA DE ENTRGA: 03/03/2025. 
            * CATEDRÁTICO: Ing. César Juárez
            */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class cajeroAutomatico {
    private static final String archivo = "saldo.dat";
    private static Double saldo = 1000.00;
    public static void main(String[] args) {
        limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("------CAJERO AUTOMÁTICO-----");
            System.out.println("1.- Consultar Saldo.");
            System.out.println("2.- Retirar Dinero.");
            System.out.println("3.- Salir Del programa.");
            System.out.println("Ingresa el número de la opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    limpiarPantalla();
                    consultarSaldo();
                break;
                case 2:
                    limpiarPantalla();
                    retirarDinero(archivo);
                break;
                case 3:
                    System.out.println("Saliste del programa :(");
                    scanner.close();
                break;
                default:
                    limpiarPantalla();
                    System.out.println("ERROR: Ingresa una opción entre 1 y 3.");
                break;
            }
        } while (opcion != 3);
    
    }

// MÉTODO PARA REALIZAR LA CONSULTA DEL SALDO
    public static void consultarSaldo(){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            escritor.write("Tú saldo es: " + saldo);
            escritor.newLine();
            System.out.println("El saldo de tu cuenta es: " + saldo);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

// MÉTODO PARA RETIRAR DINERO 
    public static void retirarDinero(String archivo){
        try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
            Scanner lectura = new Scanner(System.in);
            Double monto;

            System.out.println("Ingresa el monto a retirar: ");
            monto = lectura.nextDouble();

            if (monto > saldo) {
                System.out.println("Tú saldo es insuficiente :( ");
            } else {
                Double resultado = saldo - monto;
                saldo = resultado;
                System.out.println("Tú saldo final es: " + saldo);
                actualizarArchivo();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

// MÉTODO PARA ACTUALIZAR EL ARCHIVO CON EL ÚLTIMO SALDO
    public static void actualizarArchivo(){
        try (FileWriter writer = new FileWriter(archivo, false)) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo reescribir el saldo en el archivo. " + e.getMessage());
        }
    }

// MÉTODO PARA LIMPIAR PANTALLA
    public static void limpiarPantalla()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
