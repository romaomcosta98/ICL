public class ASTAnd implements ASTNode {
    ASTNode lhs, rhs;

    public ASTAnd(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).getValue() && ((VBool) v2).getValue());
        } else {
            throw new TypeErrorException("&& : requires two booleans");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        // TODO Auto-generated method stub
        
    }
    
    
}
