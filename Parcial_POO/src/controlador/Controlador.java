package controlador;

import modelo.Estudiante;
import modelo.Modelo;
import vista.Vista;
import java.util.ArrayList;

public class Controlador {
    private Modelo modelo;
    private Vista vista;
    private boolean ejecutando;
    
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.ejecutando = true;
    }
    
    public void iniciar() {
        while (ejecutando) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            procesarOpcion(opcion);
        }
        vista.mostrarMensaje("¡Hasta luego!");
    }
    
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarUnEstudiante();
                break;
            case 2:
                registrarVariosEstudiantes();
                break;
            case 3:
                mostrarEstudiantesOrdenados();
                break;
            case 4:
                filtrarPorNotaLimite();
                break;
            case 5:
                incrementarNotasDesarrollo();
                break;
            case 6:
                modificarNotaEstudiante();
                break;
            case 7:
                salir();
                break;
            default:
                vista.mostrarError("Opción no válida. Intente de nuevo.");
        }
    }
    
    private void registrarUnEstudiante() {
        vista.mostrarMensaje("\n--- REGISTRAR UN ESTUDIANTE ---");
        Object[] datos = vista.pedirDatosEstudiante();
        Estudiante e;
        e = new Estudiante(
                (String) datos[0],
                (int) datos[1],
                (double) datos[2],
                (double) datos[3]
        );
        modelo.agregarEstudiante(e);
        vista.mostrarExito("Estudiante registrado correctamente.");
    }
    
    private void registrarVariosEstudiantes() {
        vista.mostrarMensaje("\n--- REGISTRAR MÚLTIPLES ESTUDIANTES ---");
        int n = vista.pedirNumeroEstudiantes();
        
        for (int i = 0; i < n; i++) {
            vista.mostrarMensaje("\n--- Estudiante " + (i + 1) + " de " + n + " ---");
            Object[] datos = vista.pedirDatosEstudiante();
            Estudiante e;
            e = new Estudiante(
                    (String) datos[0],
                    (int) datos[1],
                    (double) datos[2],
                    (double) datos[3]
            );
            modelo.agregarEstudiante(e);
        }
        vista.mostrarExito(n + " estudiante(s) registrado(s) correctamente.");
    }
    
    private void mostrarEstudiantesOrdenados() {
        if (!modelo.hayEstudiantes()) {
            vista.mostrarError("No hay estudiantes registrados.");
            return;
        }
        
        modelo.ordenarPorNota(); // Método que navega a las clases contenidas
        vista.mostrarEstudiantes(modelo.getEstudiantes());
    }
    
    private void filtrarPorNotaLimite() {
        if (!modelo.hayEstudiantes()) {
            vista.mostrarError("No hay estudiantes registrados.");
            return;
        }
        
        double limite = vista.pedirNotaLimite();
        vista.mostrarMensaje("\n=== ESTUDIANTES CON NOTA DEFINITIVA > " + limite + " ===");
        
        ArrayList<Estudiante> filtrados = new ArrayList<>();
        for (Estudiante e : modelo.getEstudiantes()) {
            if (e.calcularDefinitiva() > limite) {
                filtrados.add(e);
            }
        }
        
        if (filtrados.isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes con nota definitiva superior a " + limite);
        } else {
            vista.mostrarEstudiantes(filtrados);
        }
    }
    
    private void incrementarNotasDesarrollo() {
        if (!modelo.hayEstudiantes()) {
            vista.mostrarError("No hay estudiantes registrados.");
            return;
        }
        
        double incremento = vista.pedirIncremento();
        
        for (Estudiante e : modelo.getEstudiantes()) {
            e.incrementarDesarrollo(incremento);
        }
        
        vista.mostrarExito("Notas de Desarrollo incrementadas en " + incremento);
        vista.mostrarMensaje("(Se ha respetado el límite máximo de 5.0)");
    }
    
    private void modificarNotaEstudiante() {
        if (!modelo.hayEstudiantes()) {
            vista.mostrarError("No hay estudiantes registrados.");
            return;
        }
        
        int codigo = vista.pedirCodigoModificar();
        Estudiante e = modelo.buscarPorCodigo(codigo);
        
        if (e == null) {
            vista.mostrarError("No se encontró estudiante con código " + codigo);
            return;
        }
        
        vista.mostrarMensaje("\nEstudiante encontrado: " + e.getNombre());
        vista.mostrarMensaje("Notas actuales: Desarrollo = " + e.getNotaDesarrollo() + 
                           ", Matemática = " + e.getNotaMatematica());
        
        double nuevaNotaDesarrollo = vista.pedirNota("Desarrollo (nueva)");
        double nuevaNotaMatematica = vista.pedirNota("Matemática (nueva)");
        
        e.setNotaDesarrollo(nuevaNotaDesarrollo);
        e.setNotaMatematica(nuevaNotaMatematica);
        
        vista.mostrarExito("Notas actualizadas correctamente");
        vista.mostrarMensaje("Nueva definitiva: " + String.format("%.2f", e.calcularDefinitiva()));
    }
    
    private void salir() {
        ejecutando = false;
    }
}