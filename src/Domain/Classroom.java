package Domain;

public class Classroom {

    /**
     * Attributes
     */

    private String name;
    private Integer capacity;
    private String [] extras;


    /**
     * Getters / Setters
     */

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Boolean hasExtra(String e){
        for(int i=0; i<extras.length; ++i){
            if (extras[i] == e) return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacidad(Integer capacity) {
        this.capacity = capacity;
    }


    public boolean addExtra(String e){
        if(this.hasExtra(e)) return false;
        else{
            String [] aux = new String[extras.length + 1];
            System.arraycopy(extras, 0, aux, 0, extras.length);
            aux[aux.length-1] = e;
            extras = aux;
            return true;
        }
    }
}