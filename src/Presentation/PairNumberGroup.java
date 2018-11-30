package Presentation;

public class PairNumberGroup {
    private Integer i = 0;
    private String subj = "";
    private String name = "";
    private String types = "";

    public PairNumberGroup(Integer i, String subj, String name, String types){
        this.i = i;
        this.name = name;
        this.subj = subj;
        this.types = types;
    }

    public Integer getI() {
        return i;
    }

    public String getSubj() {
        return subj;
    }

    public String getName() {
        return name;
    }

    public String getTypes(){
        return types;
    }
}
