public class ASTNotEqual implements ASTNode {
    ASTNode lhs;
    ASTNode rhs;
    
    public ASTNotEqual(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).getValue() != ((VInt) v2).getValue());
        } else if(v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).getValue() != ((VBool) v2).getValue());
        } else if(v1 instanceof VCell && v2 instanceof VCell) {
            return new VBool(((VCell) v1).getValue() != ((VCell) v2).getValue());
        } else {
            throw new TypeErrorException("!= : requires two values of the same type");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("if_icmpne");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
