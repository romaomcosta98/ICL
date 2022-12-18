public class ASTSeq implements ASTNode{
    ASTNode lhs;
    ASTNode rhs;
    
    public ASTSeq(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    public IValue eval(Environment<IValue> env) throws TypeErrorException{
        lhs.eval(env);
        return rhs.eval(env);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        c.emit("pop");
        rhs.compile(c, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }

    
}
