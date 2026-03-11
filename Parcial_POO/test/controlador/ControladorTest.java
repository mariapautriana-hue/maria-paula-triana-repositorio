package controlador;

import modelo.Estudiante;
import modelo.Modelo;
import vista.Vista;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ControladorTest {
    
    private Controlador controlador;
    private Modelo modelo;
    private Vista vista;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    
    @Before
    public void setUp() {
        // Crear instancias reales
        modelo = new Modelo();
        vista = new Vista();
        controlador = new Controlador(modelo, vista);
        
        // Crear estudiantes de prueba
        estudiante1 = new Estudiante("Juan Perez", 101, 4.5, 3.5);
        estudiante2 = new Estudiante("Maria Gomez", 102, 3.0, 4.0);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull("El controlador no debe ser null", controlador);
    }
    
    @Test
    public void testAgregarEstudianteDirectamente() {
        // Agregar estudiante directamente al modelo
        modelo.agregarEstudiante(estudiante1);
        
        // Verificar
        assertEquals(1, modelo.getEstudiantes().size());
        assertEquals("Juan Perez", modelo.getEstudiantes().get(0).getNombre());
    }
    
    @Test
    public void testModeloHayEstudiantes() {
        // Al inicio no hay estudiantes
        assertFalse(modelo.hayEstudiantes());
        
        // Agregar un estudiante
        modelo.agregarEstudiante(estudiante1);
        
        // Ahora sí hay estudiantes
        assertTrue(modelo.hayEstudiantes());
    }
    
    @Test
    public void testBuscarEstudiantePorCodigo() {
        modelo.agregarEstudiante(estudiante1);
        modelo.agregarEstudiante(estudiante2);
        
        Estudiante encontrado = modelo.buscarPorCodigo(101);
        
        assertNotNull("Debe encontrar al estudiante", encontrado);
        assertEquals("Juan Perez", encontrado.getNombre());
    }
    
    @Test
    public void testBuscarEstudiantePorCodigoInexistente() {
        modelo.agregarEstudiante(estudiante1);
        
        Estudiante encontrado = modelo.buscarPorCodigo(999);
        
        assertNull("No debe encontrar al estudiante", encontrado);
    }
    
    @Test
    public void testOrdenarEstudiantesPorNota() {
        // Agregar estudiantes en orden desordenado
        modelo.agregarEstudiante(estudiante2); // Nota: 3.5
        modelo.agregarEstudiante(estudiante1); // Nota: 4.0
        
        modelo.ordenarPorNota();
        
        ArrayList<Estudiante> ordenados = modelo.getEstudiantes();
        
        // El primer estudiante debe ser el de menor nota (Maria: 3.5)
        assertEquals("Maria Gomez", ordenados.get(0).getNombre());
        assertEquals("Juan Perez", ordenados.get(1).getNombre());
    }
    
    @Test
    public void testFiltrarEstudiantesPorNotaLimite() {
        modelo.agregarEstudiante(estudiante1); // Nota: 4.0
        modelo.agregarEstudiante(estudiante2); // Nota: 3.5
        
        double limite = 3.8;
        ArrayList<Estudiante> filtrados = new ArrayList<>();
        
        for (Estudiante e : modelo.getEstudiantes()) {
            if (e.calcularDefinitiva() > limite) {
                filtrados.add(e);
            }
        }
        
        assertEquals(1, filtrados.size());
        assertEquals("Juan Perez", filtrados.get(0).getNombre());
    }
    
    @Test
    public void testIncrementarNotaDesarrollo() {
        modelo.agregarEstudiante(estudiante1); // Nota desarrollo: 4.5
        modelo.agregarEstudiante(estudiante2); // Nota desarrollo: 3.0
        
        double incremento = 0.3;
        
        for (Estudiante e : modelo.getEstudiantes()) {
            e.incrementarDesarrollo(incremento);
        }
        
        assertEquals(4.8, estudiante1.getNotaDesarrollo(), 0.001);
        assertEquals(3.3, estudiante2.getNotaDesarrollo(), 0.001);
    }
    
    @Test
    public void testIncrementarNotaDesarrolloConLimite() {
        Estudiante estudianteLimite = new Estudiante("Test", 999, 4.8, 4.0);
        modelo.agregarEstudiante(estudianteLimite);
        
        double incremento = 0.5; // 4.8 + 0.5 = 5.3 (debe quedar en 5.0)
        
        estudianteLimite.incrementarDesarrollo(incremento);
        
        assertEquals(5.0, estudianteLimite.getNotaDesarrollo(), 0.001);
    }
    
    @Test
    public void testModificarNotasEstudiante() {
        modelo.agregarEstudiante(estudiante1);
        
        // Modificar notas
        estudiante1.setNotaDesarrollo(5.0);
        estudiante1.setNotaMatematica(5.0);
        
        assertEquals(5.0, estudiante1.getNotaDesarrollo(), 0.001);
        assertEquals(5.0, estudiante1.getNotaMatematica(), 0.001);
        assertEquals(5.0, estudiante1.calcularDefinitiva(), 0.001);
    }
    
    @Test
    public void testCalcularDefinitiva() {
        // Juan: (4.5 + 3.5) / 2 = 4.0
        assertEquals(4.0, estudiante1.calcularDefinitiva(), 0.001);
        
        // Maria: (3.0 + 4.0) / 2 = 3.5
        assertEquals(3.5, estudiante2.calcularDefinitiva(), 0.001);
    }
    
    @Test
    public void testEstudianteConDatosCompletos() {
        Estudiante nuevo = new Estudiante("Ana Lopez", 201, 4.2, 3.8);
        
        assertEquals("Ana Lopez", nuevo.getNombre());
        assertEquals(201, nuevo.getCodigo());
        assertEquals(4.2, nuevo.getNotaDesarrollo(), 0.001);
        assertEquals(3.8, nuevo.getNotaMatematica(), 0.001);
    }
    
    @Test
    public void testEliminarReferencias() {
        modelo.agregarEstudiante(estudiante1);
        assertEquals(1, modelo.getEstudiantes().size());
        
        estudiante1 = null;
        // El estudiante sigue en el modelo aunque la referencia local sea null
        assertEquals(1, modelo.getEstudiantes().size());
        assertNotNull(modelo.getEstudiantes().get(0));
    }
}