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
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeBool) {
            return new TypeBool();
        } else {
            throw new TypeErrorException("! : requires a boolean");
        }
    }
}
