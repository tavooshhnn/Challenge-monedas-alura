import org.tavoosAlura.ConversorVF;

import java.util.Scanner;

public class principal {

    boolean exit;

    public void printHeader(){
        System.out.println("***************************************************************************");
        System.out.println("*                       Reto convierte tu moneda                          *");
        System.out.println("* ---------------------------By TVS---------------------------------------*");
        System.out.println("***************************************************************************");
    }

    public void printMenu(){
        System.out.println("\n" + "Selecciona una opción: ");
        System.out.println("1) Lista monedas (USD).");
        System.out.println("2) Cambia la moneda.");
        System.out.println("3) Cambia de una moneda a otra.");
        System.out.println("4) Convierte una cantidad de una moneda a otra.");
        System.out.println("0) Salir.");
    }

    public void runMenu(){
        printHeader();
        while(!exit){
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    private int getInput(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while(choice < 0 || choice > 4){
            try{
                System.out.println("\nSelecciona una opcion 0 y 4: ");
                choice = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Opción no disponible.");
            }
        }
        return choice;
    }

    private void performAction(int choice) {
        ConversorVF converter = new ConversorVF();
        Scanner scanner = new Scanner(System.in);
        switch(choice){
            case 0:
                exit = true;
                System.out.println("\n"+"Gracias vuelve pronto.");
                break;
            case 1:
                converter.getConversionRates();
                break;
            case 2:
                System.out.println("\n"+"Ingrese el código de la moneda a convertir (EJ: MXN USD): ");
                String moneda = scanner.nextLine();
                converter.getConversionRates(moneda);
                break;
            case 3:
                System.out.println("\n"+"Ingresa el código de moneda origen y el código de moneda destino. Separado por un espacio: Ejemplo: EUR MXN ");
                String[] monedas = scanner.nextLine().split(" ");
                if(monedas.length != 2){
                    System.out.println("\n"+"Ingresa 2 valores en formato moneda");
                    break;
                }
                converter.getConversionRates(monedas[0], monedas[1]);
                break;
            case 4:
                System.out.println("\n"+"Ingresa el codigo origen + codigo destino + cantidad Ejemplo: EUR MXN 100.0: ");
                String[] amount = scanner.nextLine().split(" ");
                if(amount.length != 3){
                    System.out.println("\n"+"Coloca 3 valores en formato moneda y cantidad");
                    break;
                }

                try {
                    // Convertir la cantidad a un valor numérico
                    double cantidad = Double.parseDouble(amount[2]);

                    // Obtener la tasa de conversión entre las dos monedas
                    converter.getConversionRates(amount[0], amount[1]);

                    // Aquí deberías obtener la tasa de conversión desde el resultado de la API
                    // Simulemos la tasa para seguir adelante con el ejemplo
                    double tasaConversion = 1.23; // <- Asume que obtienes esto de la respuesta de la API

                    // Calcular el resultado
                    double resultado = cantidad * tasaConversion;
                    System.out.println("\nEsta es la cantidad convertida: " + resultado);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ser un # valido.");
                }
                break;
            default:
                System.out.println("\n"+"Error");
        }
    }

    public static void main(String[] args) {
        principal menu = new principal();
        menu.runMenu();
    }
}