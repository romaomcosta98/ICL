public class VInt implements IValue {
    int value;
    public VInt(int v) {
        value = v;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }
}
