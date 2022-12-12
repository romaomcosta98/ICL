public class ASTNot implements ASTNode {
    ASTNode lhs;

    public ASTNot(ASTNode l) {
        lhs = l;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VBool) {
            return new VBool(!((VBool) v1).getValue());
        } else {
            throw new TypeErrorException("! : requires a boolean");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        c.emit("ineg");
    }
}
