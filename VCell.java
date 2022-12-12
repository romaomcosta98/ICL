public class VCell implements IValue {
    IValue value;

    VCell(IValue v) {
        value = v;
    }
    public String toString() {
        return value.toString();
    }
    IValue getValue() {
        return value;
    }
    void setValue(IValue v) {
        value = v;
    }
}
