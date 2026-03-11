package modelo;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ModeloTest {
    
    private Modelo modelo;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Estudiante estudiante3;
    
    @Before
    public void setUp() {
       
        modelo = new Modelo();
        
        estudiante1 = new Estudiante("Juan Perez", 101, 4.5, 3.5);
        estudiante2 = new Estudiante("Maria Gomez", 102, 3.0, 4.0);
        estudiante3 = new Estudiante("Carlos Lopez", 103, 5.0, 4.8);
    }
    
    @After
    public void tearDown() {
        
        modelo = null;
        estudiante1 = null;
        estudiante2 = null;
        estudiante3 = null;
    }
    
    
    @Test
    public void testAgregarEstudiante() {
        modelo.agregarEstudiante(estudiante1);
        assertEquals(1, modelo.getEstudiantes().size());
        assertEquals("Juan Perez", modelo.getEstudiantes().get(0).getNombre());
    }
    
    @Test
    public void testHayEstudiantes() {
        assertFalse(modelo.hayEstudiantes());
        modelo.agregarEstudiante(estudiante1);
        assertTrue(modelo.hayEstudiantes());
    }
    
    @Test
    public void testBuscarPorCodigo() {
        modelo.agregarEstudiante(estudiante1);
        modelo.agregarEstudiante(estudiante2);
        
        Estudiante encontrado = modelo.buscarPorCodigo(102);
        assertNotNull(encontrado);
        assertEquals("Maria Gomez", encontrado.getNombre());
        
        Estudiante noEncontrado = modelo.buscarPorCodigo(999);
        assertNull(noEncontrado);
    }
    
    @Test
    public void testOrdenarPorNota() {
       
        modelo.agregarEstudiante(estudiante2); // Nota: 3.5
        modelo.agregarEstudiante(estudiante1); // Nota: 4.0
        modelo.agregarEstudiante(estudiante3); // Nota: 4.9
        
        modelo.ordenarPorNota();
        
        ArrayList<Estudiante> ordenados = modelo.getEstudiantes();
        assertEquals("Maria Gomez", ordenados.get(0).getNombre());
        assertEquals("Juan Perez", ordenados.get(1).getNombre());
        assertEquals("Carlos Lopez", ordenados.get(2).getNombre());
    }
}