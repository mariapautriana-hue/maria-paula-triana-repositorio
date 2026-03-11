package modelo;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

public class EstudianteTest {
    
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Estudiante estudiante3;
    
    @Before
    public void setUp() {
        // Crear estudiantes de prueba usando el constructor que nos mostraste
        estudiante1 = new Estudiante("Juan Perez", 101, 4.5, 3.5);
        estudiante2 = new Estudiante("Maria Gomez", 102, 3.0, 4.0);
        estudiante3 = new Estudiante("Carlos Lopez", 103, 5.0, 4.8);
    }
    
    @After
    public void tearDown() {
        estudiante1 = null;
        estudiante2 = null;
        estudiante3 = null;
    }
    
    @Test
    public void testConstructorEstudiante() {
        // Verificar que el constructor asigna correctamente los valores
        Estudiante nuevo = new Estudiante("Ana Martinez", 201, 4.2, 3.8);
        
        assertEquals("Ana Martinez", nuevo.getNombre());
        assertEquals(201, nuevo.getCodigo());
        assertEquals(4.2, nuevo.getNotaDesarrollo(), 0.001);
        assertEquals(3.8, nuevo.getNotaMatematica(), 0.001);
    }
    
    @Test
    public void testGetNombre() {
        assertEquals("Juan Perez", estudiante1.getNombre());
        assertEquals("Maria Gomez", estudiante2.getNombre());
        assertEquals("Carlos Lopez", estudiante3.getNombre());
    }
    
    @Test
    public void testGetCodigo() {
        assertEquals(101, estudiante1.getCodigo());
        assertEquals(102, estudiante2.getCodigo());
        assertEquals(103, estudiante3.getCodigo());
    }
    
    @Test
    public void testGetNotaDesarrollo() {
        assertEquals(4.5, estudiante1.getNotaDesarrollo(), 0.001);
        assertEquals(3.0, estudiante2.getNotaDesarrollo(), 0.001);
        assertEquals(5.0, estudiante3.getNotaDesarrollo(), 0.001);
    }
    
    @Test
    public void testSetNotaDesarrollo() {
        estudiante1.setNotaDesarrollo(4.8);
        assertEquals(4.8, estudiante1.getNotaDesarrollo(), 0.001);
        
        estudiante2.setNotaDesarrollo(3.5);
        assertEquals(3.5, estudiante2.getNotaDesarrollo(), 0.001);
    }
    
    @Test
    public void testGetNotaMatematica() {
        assertEquals(3.5, estudiante1.getNotaMatematica(), 0.001);
        assertEquals(4.0, estudiante2.getNotaMatematica(), 0.001);
        assertEquals(4.8, estudiante3.getNotaMatematica(), 0.001);
    }
    
    @Test
    public void testSetNotaMatematica() {
        estudiante1.setNotaMatematica(3.9);
        assertEquals(3.9, estudiante1.getNotaMatematica(), 0.001);
        
        estudiante3.setNotaMatematica(5.0);
        assertEquals(5.0, estudiante3.getNotaMatematica(), 0.001);
    }
    
    @Test
    public void testCalcularDefinitiva() {
        // Juan: (4.5 + 3.5) / 2 = 4.0
        assertEquals(4.0, estudiante1.calcularDefinitiva(), 0.001);
        
        // Maria: (3.0 + 4.0) / 2 = 3.5
        assertEquals(3.5, estudiante2.calcularDefinitiva(), 0.001);
        
        // Carlos: (5.0 + 4.8) / 2 = 4.9
        assertEquals(4.9, estudiante3.calcularDefinitiva(), 0.001);
    }
    
    @Test
    public void testCalcularDefinitivaConCambios() {
        // Modificar notas y verificar que la definitiva se actualiza
        estudiante1.setNotaDesarrollo(5.0);
        estudiante1.setNotaMatematica(5.0);
        assertEquals(5.0, estudiante1.calcularDefinitiva(), 0.001);
        
        estudiante2.setNotaDesarrollo(1.5);
        estudiante2.setNotaMatematica(2.5);
        assertEquals(2.0, estudiante2.calcularDefinitiva(), 0.001);
    }
    
    @Test
    public void testEstudianteWithDifferentValues() {
        // Probar diferentes combinaciones de notas
        Estudiante est = new Estudiante("Test", 999, 0.0, 5.0);
        assertEquals(0.0, est.getNotaDesarrollo(), 0.001);
        assertEquals(5.0, est.getNotaMatematica(), 0.001);
        assertEquals(2.5, est.calcularDefinitiva(), 0.001);
        
        est = new Estudiante("Test2", 888, 2.5, 2.5);
        assertEquals(2.5, est.calcularDefinitiva(), 0.001);
    }
    
    @Test
    public void testEstudiantesSonIndependientes() {
        // Verificar que modificar un estudiante no afecta a los otros
        double notaOriginalJuan = estudiante1.getNotaDesarrollo();
        double notaOriginalMaria = estudiante2.getNotaDesarrollo();
        
        estudiante1.setNotaDesarrollo(1.0);
        
        assertEquals(1.0, estudiante1.getNotaDesarrollo(), 0.001);
        assertEquals(notaOriginalMaria, estudiante2.getNotaDesarrollo(), 0.001);
        assertEquals(4.5, notaOriginalJuan, 0.001); 
    }
    
    @Test
    public void testCodigosUnicos() {
        // Verificar que los códigos son diferentes
        assertNotEquals(estudiante1.getCodigo(), estudiante2.getCodigo());
        assertNotEquals(estudiante1.getCodigo(), estudiante3.getCodigo());
        assertNotEquals(estudiante2.getCodigo(), estudiante3.getCodigo());
    }
    
    @Test
    public void testNombresCorrectos() {
        assertEquals("Juan Perez", estudiante1.getNombre());
        assertEquals("Maria Gomez", estudiante2.getNombre());
        assertEquals("Carlos Lopez", estudiante3.getNombre());
    }
    
    @Test
    public void testNotasEnRango() {
        // Verificar que las notas están entre 0 y 5
        assertTrue(estudiante1.getNotaDesarrollo() >= 0 && estudiante1.getNotaDesarrollo() <= 5);
        assertTrue(estudiante1.getNotaMatematica() >= 0 && estudiante1.getNotaMatematica() <= 5);
        assertTrue(estudiante2.getNotaDesarrollo() >= 0 && estudiante2.getNotaDesarrollo() <= 5);
        assertTrue(estudiante2.getNotaMatematica() >= 0 && estudiante2.getNotaMatematica() <= 5);
    }
}