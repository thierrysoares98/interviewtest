package interview.utils.constants;

public enum ShouldTypes {

    //have
    HAVE_VALUE("have.value"),
    HAVE_INNERHTML("have.innerHTML"),
    HAVE_SRC("have.src"),

    //not have
    NOTHAVE_VALUE("nothave.value"),
    NOTHAVE_INNERHTML("nothave.innerHTML"),
    NOTHAVE_SRC("nothave.src"),

    //contains
    CONTAINS_VALUE("contains.value"),
    CONTAINS_INNERHTML("contains.innerHTML"),
    CONTAINS_SRC("contains.src"),

    //not contains
    NOTCONTAINS_VALUE("notcontains.value"),
    NOTCONTAINS_INNERHTML("notcontains.innerHTML"),
    NOTCONTAINS_SRC("notcontains.src");

    private String type;

    ShouldTypes(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
