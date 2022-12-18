public class ASTAssign implements ASTNode {
    ASTNode lhs;
    ASTNode rhs;

    public ASTAssign(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        if(v1 instanceof VCell){
            IValue v2 = rhs.eval(env);
            ((VCell) v1).setValue(v2);
            return v2;
        }
        else{
            throw new TypeErrorException(":= : requires a reference");
        }

        
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        
        
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
