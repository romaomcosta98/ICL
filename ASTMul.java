public class ASTMul implements ASTNode {
    ASTNode lhs, rhs;

    public ASTMul(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }
    public IValue eval(Environment env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VInt(((VInt) v1).getValue() * ((VInt) v2).getValue());
        } else {
            throw new TypeErrorException("* : requires two integers");
        }
       
    }

    @Override
    public void compile(CodeBlock c, Environment env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("imul");
    }
}
