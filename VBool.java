public class VBool implements IValue {
    boolean value;
    public VBool(boolean v) {
        value = v;
    }

    public String toString() {
        return Boolean.toString(value);
    }

    public boolean getValue() {
        return value;
    }
}
