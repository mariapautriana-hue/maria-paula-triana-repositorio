package vista;

import modelo.Estudiante;
import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    private Scanner scanner;
    
    public Vista() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("\n-------------------------------------");
        System.out.println("║     SISTEMA DE NOTAS ESTUDIANTES     ║");
        System.out.println("---------------------------------------");
        System.out.println("║ 1. Registrar un estudiante          ║");
        System.out.println("║ 2. Registrar múltiples estudiantes  ║");
        System.out.println("║ 3. Mostrar todos (orden ascendente) ║");
        System.out.println("║ 4. Filtrar por nota límite          ║");
        System.out.println("║ 5. Incrementar notas de Desarrollo  ║");
        System.out.println("║ 6. Modificar nota de un estudiante  ║");
        System.out.println("║ 7. Salir                            ║");
        System.out.println("---------------------------------------");
        System.out.print("Seleccione una opción: ");
    }
    
    public int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Debe ingresar un número.");
            scanner.next();
            System.out.print("Seleccione una opción: ");
        }
        return scanner.nextInt();
    }
    
    public int pedirNumeroEstudiantes() {
        System.out.print("Ingrese el número de estudiantes a registrar: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Debe ingresar un número entero.");
            scanner.next();
            System.out.print("Ingrese el número de estudiantes: ");
        }
        return scanner.nextInt();
    }
    
    public Object[] pedirDatosEstudiante() {
        int codigo;
        do {
            System.out.print("Código (mayor a 21000): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Código inválido.");
                scanner.next();
            }
            codigo = scanner.nextInt();
            if (codigo <= 21000) {
                System.out.println("Error: El código debe ser mayor a 21000.");
            }
        } while (codigo <= 21000);
        
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        double notaDesarrollo = pedirNota("Desarrollo");
        double notaMatematica = pedirNota("Matemática");
        
        return new Object[]{codigo, nombre, notaDesarrollo, notaMatematica};
    }
    
    public double pedirNota(String materia) {
        double nota;
        do {
            System.out.print("Nota de " + materia + " (0.0 - 5.0): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Nota inválida.");
                scanner.next();
            }
            nota = scanner.nextDouble();
            if (nota < 0 || nota > 5) {
                System.out.println("Error: La nota debe estar entre 0.0 y 5.0.");
            }
        } while (nota < 0 || nota > 5);
        return nota;
    }
    
    public void mostrarEstudiantes(ArrayList<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para mostrar.");
            return;
        }
        System.out.println("\n═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                         LISTA DE ESTUDIANTES");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("Total de estudiantes: " + estudiantes.size());
    }
    
    public double pedirNotaLimite() {
        double limite;
        do {
            System.out.print("Ingrese nota límite (0.0 - 5.0): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Nota inválida.");
                scanner.next();
            }
            limite = scanner.nextDouble();
            if (limite < 0 || limite > 5) {
                System.out.println("Error: La nota límite debe estar entre 0.0 y 5.0.");
            }
        } while (limite < 0 || limite > 5);
        return limite;
    }
    
    public double pedirIncremento() {
        double incremento;
        do {
            System.out.print("Ingrese incremento para Desarrollo (0.0 - 0.5): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Incremento inválido.");
                scanner.next();
            }
            incremento = scanner.nextDouble();
            if (incremento < 0 || incremento > 0.5) {
                System.out.println("Error: El incremento debe estar entre 0.0 y 0.5.");
            }
        } while (incremento < 0 || incremento > 0.5);
        return incremento;
    }
    
    public int pedirCodigoModificar() {
        System.out.print("Ingrese el código del estudiante a modificar: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Código inválido.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarError(String error) {
        System.err.println("❌ ERROR: " + error);
    }
    
    public void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }
    
    public void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}