public class ASTNew implements ASTNode {
    ASTNode node;
    public ASTNew(ASTNode node) {
        this.node = node;
    }
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        return new VCell(v1);
    }
    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        node.compile(c, e);
        c.emit("new");
    }
    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
