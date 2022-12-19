public class ASTIf implements ASTNode {
    //if node
    ASTNode cond;
    //then node
    ASTNode then;
    //else node
    ASTNode els;

    public ASTIf(ASTNode cond, ASTNode then, ASTNode els) {
        this.cond = cond;
        this.then = then;
        this.els = els;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = cond.eval(env);
        if (v1 instanceof VBool) {
            Boolean condVal = ((VBool) v1).getValue();
            IValue v2 = condVal ? then.eval(env) : els.eval(env);
            return v2;
        }
        throw new TypeErrorException("if : requires a boolean");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        c.emit("if");
        cond.compile(c, e);
        then.compile(c, e);
        els.compile(c, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = cond.typecheck(e);
        if (v1 instanceof TypeBool) {
            IType v2 = then.typecheck(e);
            IType v3 = els.typecheck(e);
            if (v2.equals(v3)) {
                return v2;
            }
        }
        throw new TypeErrorException("if : requires a boolean");
    }
    
}
