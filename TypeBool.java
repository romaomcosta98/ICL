public class TypeBool implements IType {
    public boolean equals(IType t) {
        return t instanceof TypeBool;
    }
}
