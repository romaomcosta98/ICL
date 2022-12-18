public class ASTDiv implements ASTNode {
    ASTNode lhs, rhs;

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VInt(((VInt) v1).getValue() / ((VInt) v2).getValue());
        } else {
            throw new TypeErrorException("/ : requires two integers");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("idiv");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
}