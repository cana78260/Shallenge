package fr.sncf.d2d.shallenge;

public enum Hash {
    SHA256("SHA-256");
    

    public String value = "";
    Hash(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
