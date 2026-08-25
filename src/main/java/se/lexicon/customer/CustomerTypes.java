package se.lexicon.customer;

public enum CustomerTypes {
    COMPANY("COMPANY"),
    INDIVIDUAL("INDIVIDUAL");

    //private final String dbValue="";
    private String dbValue="";
    CustomerTypes(String dbValue){
        this.dbValue  = dbValue;
    }
}


