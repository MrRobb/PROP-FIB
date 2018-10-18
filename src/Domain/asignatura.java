<<<<<<< HEAD
package Domain;

import javafx.util.Pair;

import java.util.Map;

public class asignatura {
    private String nombre;
    private Integer Semestre;
    private Integer creditos;
    private Boolean obligatoria;
    private String especialidad;
    private Map<String,Integer> horasSemanales;

    public String getNombre() {
        return nombre;
    }

    public Integer getSemestre() {
        return Semestre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Integer getHorasSemanales(String tipo) {
        return horasSemanales.get(tipo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestre(Integer semestre) {
        Semestre = semestre;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void addHorasSemanales(String tipo, Integer horas) {
        horasSemanales.put(tipo,horas);
    }
}
=======
package Domain;

public class asignatura {
}
>>>>>>> master
