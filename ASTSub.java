import org.xml.sax.EntityResolver;

public class ASTSub implements ASTNode {

    ASTNode lhs, rhs;

    public ASTSub(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VInt(((VInt) v1).getValue() - ((VInt) v2).getValue());
        } else {
            throw new TypeErrorException("- : requires two integers");
        }

    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("isub");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt) {
                return v1;
            }
        }
        throw new TypeErrorException("- : requires two integers");
    }
    
}

