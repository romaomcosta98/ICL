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
    public void compile(CodeBlock c, Environment e) {
        // TODO Auto-generated method stub
        
    }
    
}
