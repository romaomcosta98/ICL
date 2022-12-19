public class ASTDeref implements ASTNode {
    ASTNode node;

    public ASTDeref(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        if (v1 instanceof VCell) {
            return ((VCell) v1).getValue();
        } else {
            throw new TypeErrorException("! : requires a reference");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        node.compile(c, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = node.typecheck(e);
        if (v1 instanceof TypeRef) {
            return ((TypeRef) v1).getType();
        } else {
            throw new TypeErrorException("! : requires a reference");
        }
    }
    
}
