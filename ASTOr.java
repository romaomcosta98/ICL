public class ASTOr implements ASTNode{
    ASTNode lhs, rhs;

    public ASTOr(ASTNode lhs, ASTNode rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
      IValue v1 = lhs.eval(env);
      IValue v2 = rhs.eval(env);
        if (v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).getValue() || ((VBool) v2).getValue());
        } else {
            throw new TypeErrorException("|| : requires two booleans");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("ior");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeBool) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeBool) {
                return v1;
            }
        }
        throw new TypeErrorException("|| : requires two booleans");
    }
    
    
}
