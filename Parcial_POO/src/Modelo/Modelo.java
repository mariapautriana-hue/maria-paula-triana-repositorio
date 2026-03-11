package modelo;

import java.util.ArrayList;

public class Modelo {
  
    
    private ArrayList<Estudiante> estudiantes;
    
    public Modelo() {
        this.estudiantes = new ArrayList<>();
    }
    
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
    
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    
    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
    public boolean hayEstudiantes() {
        return !estudiantes.isEmpty();
    }
    
    
    public void ordenarPorNota() {
        int n = estudiantes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                if (estudiantes.get(j).calcularDefinitiva() > estudiantes.get(j + 1).calcularDefinitiva()) {
                   
                    Estudiante temp = estudiantes.get(j);
                    estudiantes.set(j, estudiantes.get(j + 1));
                    estudiantes.set(j + 1, temp);
                }
            }
        }
    }
    
   
    public Estudiante buscarPorCodigo(int codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        return null;
    }
}