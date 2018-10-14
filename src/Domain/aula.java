package Domain;

public class aula {
    private String nombre;
    private Integer capacidad;
    private String [] extras;

    public String getNombre() {
        return nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Boolean hasExtra(String e){
        for(int i=0; i<extras.length; ++i){
            if (extras[i] == e) return true;
        }
        return false;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void addExtra(String e){
        String [] aux = new String[extras.length + 1];
        System.arraycopy(extras, 0, aux, 0, extras.length);
        aux[aux.length-1] = e;
        extras = aux;
    }
}

