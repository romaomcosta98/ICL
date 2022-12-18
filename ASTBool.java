public class ASTBool implements ASTNode {
    boolean value;
    
    public ASTBool(boolean value) {
        this.value = value;
    }
    
    public IValue eval(Environment<IValue> env) {
        return new VBool(value);
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

