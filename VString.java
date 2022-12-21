public class VString implements IValue {
    String value;
    
    public VString(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public String toString() {
        return value;
    }
}
