package Presentation;

public class PairNumberClassroom {
    private Integer i = 0;
    private String name = "";
    private String extras = "";

    public PairNumberClassroom(Integer i, String name, String ex){
        this.i = i;
        this.name = name;
        this.extras = ex;
    }

    public Integer getI() {
        return i;
    }

    public String getExtras() {
        return extras;
    }

    public String getName() {
        return name;
    }
}
