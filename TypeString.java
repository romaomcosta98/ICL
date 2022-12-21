public class TypeString implements IType {
    
    public boolean equals(Object other) {
        return other instanceof TypeString;
    }
    
    public String toString() {
        return "string";
    }

    @Override
    public boolean equals(IType t) {
        return t instanceof TypeString;
    }
    
}