public class TypeInt implements IType {
    public boolean equals(IType t) {
        return t instanceof TypeInt;
    }
    
    public String toString() {
        return "int";
    }
}
