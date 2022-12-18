public class ASTWhile implements ASTNode {
    ASTNode lhs, rhs;
    ASTNode cond, body;
    
    public ASTWhile(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        while(((VBool)lhs.eval(env)).getValue()){
            rhs.eval(env);
        }
        return null;

        
    
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> env) {
        
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
}
