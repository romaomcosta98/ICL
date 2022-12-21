public class ASTNeg implements ASTNode{
    ASTNode node;
    
    public ASTNeg(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        if (v1 instanceof VBool) {
            return new VBool(!((VBool) v1).getValue());
        } else {
            throw new TypeErrorException("~ : requires a boolean");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        node.compile(c, e);
        c.emit("ineg");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = node.typecheck(e);
        if (v1 instanceof TypeBool) {
            return new TypeBool();
        } else {
            throw new TypeErrorException("~ : requires a boolean");
        }
    }
}
