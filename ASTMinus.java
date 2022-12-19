public class ASTMinus implements ASTNode {
    ASTNode lhs;
    
    public ASTMinus(ASTNode l) {
        lhs = l;
    }
    
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VInt) {
            return new VInt(-((VInt) v1).getValue());
        } else {
            throw new TypeErrorException("- : requires an integer");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        c.emit("ineg");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            return v1;
        }
        
        throw new TypeErrorException("- : requires an integer");
    }
    
}
