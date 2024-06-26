public class ASTEquals implements ASTNode {
    ASTNode lhs, rhs;

    public ASTEquals(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).getValue() == ((VInt) v2).getValue());
        } else {
            throw new TypeErrorException("== : requires two integers");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        int startLabels = c.CountLabels(2);
        String l1 = "L" + startLabels;
        String l2 = "L" + (startLabels + 1);
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("isub");
        c.emit("ifeq" + l1);
        c.emit("iconst_0");
        c.emit("goto" + l2);
        c.emit(l1 + ":");
        c.emit("iconst_1");
        c.emit(l2 + ":");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        IType v2 = rhs.typecheck(e);
        if(v1.equals(v2)) {
            return new TypeBool();
        } else {
            throw new TypeErrorException("== : requires two integers");
        }
    }
    
}
