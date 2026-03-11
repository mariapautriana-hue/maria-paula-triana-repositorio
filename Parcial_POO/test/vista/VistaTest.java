package vista;

import modelo.Estudiante;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class VistaTest {
    
    private Vista vista;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;
    
    @Before
    public void setUp() {
        // Redirigir salidas estándar para capturar lo que se imprime
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        vista = new Vista();
    }
    
    @After
    public void tearDown() {
        // Restaurar salidas y entradas estándar
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }
    
    @Test
    public void testMostrarMenu() {
        vista.mostrarMenu();
        String salida = outContent.toString();
        
        assertTrue(salida.contains("SISTEMA DE NOTAS ESTUDIANTES"));
        assertTrue(salida.contains("1. Registrar un estudiante"));
        assertTrue(salida.contains("2. Registrar múltiples estudiantes"));
        assertTrue(salida.contains("3. Mostrar todos (orden ascendente)"));
        assertTrue(salida.contains("4. Filtrar por nota límite"));
        assertTrue(salida.contains("5. Incrementar notas de Desarrollo"));
        assertTrue(salida.contains("6. Modificar nota de un estudiante"));
        assertTrue(salida.contains("7. Salir"));
        assertTrue(salida.contains("Seleccione una opción:"));
    }
    
    @Test
    public void testLeerOpcionValida() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int opcion = vista.leerOpcion();
        assertEquals(5, opcion);
    }
    
    @Test
    public void testLeerOpcionInvalidaLuegoValida() {
        String input = "abc\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int opcion = vista.leerOpcion();
        
        assertEquals(3, opcion);
        String error = errContent.toString();
        assertTrue(error.contains("Error: Debe ingresar un número"));
    }
    
    @Test
    public void testPedirNumeroEstudiantesValido() {
        String input = "10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int numero = vista.pedirNumeroEstudiantes();
        assertEquals(10, numero);
    }
    
    @Test
    public void testPedirNumeroEstudiantesInvalidoLuegoValido() {
        String input = "abc\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int numero = vista.pedirNumeroEstudiantes();
        
        assertEquals(5, numero);
        String error = errContent.toString();
        assertTrue(error.contains("Error: Debe ingresar un número entero"));
    }
    
    @Test
    public void testPedirDatosEstudianteValidos() {
        // Simular entrada: código válido (>21000), nombre, notas válidas
        String input = "21001\nJuan Perez\n4.5\n3.8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Object[] datos = vista.pedirDatosEstudiante();
        
        assertEquals(21001, datos[0]);
        assertEquals("Juan Perez", datos[1]);
        assertEquals(4.5, (double) datos[2], 0.001);
        assertEquals(3.8, (double) datos[3], 0.001);
    }
    
    @Test
    public void testPedirDatosEstudianteCodigoInvalido() {
        // Primero código inválido (<=21000), luego válido
        String input = "20000\n21001\nJuan Perez\n4.5\n3.8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Object[] datos = vista.pedirDatosEstudiante();
        
        assertEquals(21001, datos[0]);
        String salida = outContent.toString();
        assertTrue(salida.contains("Error: El código debe ser mayor a 21000"));
    }
    
    @Test
    public void testPedirNotaValida() {
        String input = "4.7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double nota = vista.pedirNota("Matemática");
        
        assertEquals(4.7, nota, 0.001);
        String salida = outContent.toString();
        assertTrue(salida.contains("Nota de Matemática (0.0 - 5.0):"));
    }
    
    @Test
    public void testPedirNotaInvalidaLuegoValida() {
        String input = "6.5\n4.2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double nota = vista.pedirNota("Desarrollo");
        
        assertEquals(4.2, nota, 0.001);
        String salida = outContent.toString();
        assertTrue(salida.contains("Error: La nota debe estar entre 0.0 y 5.0"));
    }
    
    @Test
    public void testPedirNotaLimiteValida() {
        String input = "3.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double limite = vista.pedirNotaLimite();
        
        assertEquals(3.5, limite, 0.001);
    }
    
    @Test
    public void testPedirNotaLimiteInvalidaLuegoValida() {
        String input = "5.5\n4.0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double limite = vista.pedirNotaLimite();
        
        assertEquals(4.0, limite, 0.001);
        String salida = outContent.toString();
        assertTrue(salida.contains("Error: La nota límite debe estar entre 0.0 y 5.0"));
    }
    
    @Test
    public void testPedirIncrementoValido() {
        String input = "0.3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double incremento = vista.pedirIncremento();
        
        assertEquals(0.3, incremento, 0.001);
    }
    
    @Test
    public void testPedirIncrementoInvalidoLuegoValido() {
        String input = "0.7\n0.2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double incremento = vista.pedirIncremento();
        
        assertEquals(0.2, incremento, 0.001);
        String salida = outContent.toString();
        assertTrue(salida.contains("Error: El incremento debe estar entre 0.0 y 0.5"));
    }
    
    @Test
    public void testPedirCodigoModificar() {
        String input = "21001\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int codigo = vista.pedirCodigoModificar();
        
        assertEquals(21001, codigo);
    }
    
    @Test
    public void testMostrarEstudiantesConListaVacia() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        
        vista.mostrarEstudiantes(lista);
        
        String salida = outContent.toString();
        assertTrue(salida.contains("No hay estudiantes para mostrar"));
    }
    
    @Test
    public void testMostrarEstudiantesConDatos() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        Estudiante e1 = new Estudiante("Juan Perez", 21001, 4.5, 3.5);
        Estudiante e2 = new Estudiante("Maria Gomez", 21002, 3.0, 4.0);
        lista.add(e1);
        lista.add(e2);
        
        vista.mostrarEstudiantes(lista);
        
        String salida = outContent.toString();
        assertTrue(salida.contains("LISTA DE ESTUDIANTES"));
        assertTrue(salida.contains("Juan Perez"));
        assertTrue(salida.contains("Maria Gomez"));
        assertTrue(salida.contains("Total de estudiantes: 2"));
    }
    
    @Test
    public void testMostrarMensaje() {
        vista.mostrarMensaje("Mensaje de prueba");
        
        String salida = outContent.toString();
        assertTrue(salida.contains("Mensaje de prueba"));
    }
    
    @Test
    public void testMostrarError() {
        vista.mostrarError("Error de prueba");
        
        String error = errContent.toString();
        assertTrue(error.contains("ERROR: Error de prueba"));
    }
    
    @Test
    public void testMostrarExito() {
        vista.mostrarExito("Operación exitosa");
        
        String salida = outContent.toString();
        assertTrue(salida.contains("Operación exitosa"));
    }
    
    @Test
    public void testPausa() {
        // Simular Enter del usuario
        String input = "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        // No podemos verificar fácilmente la pausa, solo que no lance excepción
        vista.pausa();
        
        // Verificar que se mostró el mensaje
        String salida = outContent.toString();
        assertTrue(salida.contains("Presione Enter para continuar..."));
    }
    
    @Test
    public void testPedirNotaConTextoInvalido() {
        String input = "abc\n4.0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        double nota = vista.pedirNota("Desarrollo");
        
        assertEquals(4.0, nota, 0.001);
        String error = errContent.toString();
        assertTrue(error.contains("Error: Nota inválida"));
    }
    
    @Test
    public void testPedirDatosEstudianteConNotasInvalidas() {
        // Primera nota inválida (6.0), segunda inválida (-1.0), luego válidas
        String input = "21001\nJuan Perez\n6.0\n-1.0\n4.5\n3.8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Object[] datos = vista.pedirDatosEstudiante();
        
        assertEquals(21001, datos[0]);
        assertEquals("Juan Perez", datos[1]);
        assertEquals(4.5, (double) datos[2], 0.001);
        assertEquals(3.8, (double) datos[3], 0.001);
        
        String salida = outContent.toString();
        assertTrue(salida.contains("Error: La nota debe estar entre 0.0 y 5.0"));
    }
    
    @Test
    public void testPedirNumeroEstudiantesConTextoInvalido() {
        String input = "texto\n-5\n0\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        int numero = vista.pedirNumeroEstudiantes();
        
        assertEquals(3, numero);
        String error = errContent.toString();
        assertTrue(error.contains("Error: Debe ingresar un número entero"));
    }
}