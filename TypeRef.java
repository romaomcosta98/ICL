public class TypeRef implements IType {
    private IType type;
    
    public TypeRef(IType type) {
        this.type = type;
    }
    
    public IType getType() {
        return type;
    }
    
    public boolean equals(IType t) {
        return t instanceof TypeRef && type.equals(((TypeRef)t).getType());
    }
    
    public String toString() {
        return "ref " + type.toString();
    }
}
