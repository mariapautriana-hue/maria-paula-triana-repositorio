package modelo;

public class Estudiante {
    private int codigo;
    private String nombre;
    private double notaDesarrollo;
    private double notaMatematica;
    
    public Estudiante(String nombre, int codigo, double notaDesarrollo, double notaMatematica) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notaDesarrollo = notaDesarrollo;
        this.notaMatematica = notaMatematica;
    }
    
    // Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getNotaDesarrollo() { return notaDesarrollo; }
    public void setNotaDesarrollo(double notaDesarrollo) { this.notaDesarrollo = notaDesarrollo; }
    
    public double getNotaMatematica() { return notaMatematica; }
    public void setNotaMatematica(double notaMatematica) { this.notaMatematica = notaMatematica; }
    
    // Métodos de negocio
    public double calcularDefinitiva() {
        return (notaDesarrollo * 0.55) + (notaMatematica * 0.45);
    }
    
    public String aprueba() {
        return calcularDefinitiva() >= 3.5 ? "SI APRUEBA" : "NO APRUEBA";
    }
    
    public void incrementarDesarrollo(double incremento) {
        double nuevaNota = this.notaDesarrollo + incremento;
        this.notaDesarrollo = Math.min(nuevaNota, 5.0);
    }
    
    @Override
    public String toString() {
        return String.format("Código: %d | Nombre: %s | Desarrollo: %.2f | Matemática: %.2f | Definitiva: %.2f | %s",
                codigo, nombre, notaDesarrollo, notaMatematica, calcularDefinitiva(), aprueba());
    }
}