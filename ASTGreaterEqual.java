public class ASTGreaterEqual implements ASTNode {
    ASTNode lhs, rhs;

    public ASTGreaterEqual(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).getValue() >= ((VInt) v2).getValue());
        } else {
            throw new TypeErrorException(">= : requires two integers");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        // TODO Auto-generated method stub
        
    }
    
    
}
