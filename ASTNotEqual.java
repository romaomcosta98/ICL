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
        } else {
            throw new TypeErrorException("!= : requires two values of the same type");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        int startLabels = c.CountLabels(2);
        String l1 = "L" + startLabels;
        String l2 = "L" + (startLabels + 1);
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("isub");
        c.emit("ifne" + l1);
        c.emit("iconst_0");
        c.emit("goto" + l2);
        c.emit(l1 + ":");
        c.emit("iconst_1");
        c.emit(l2 + ":");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        IType v2 = rhs.typecheck(e);
        if (v1 instanceof TypeInt && v2 instanceof TypeInt) {
            return new TypeBool();
        } else if(v1 instanceof TypeBool && v2 instanceof TypeBool) {
            return new TypeBool();
        } else if(v1 instanceof TypeRef && v2 instanceof TypeRef) {
            return new TypeBool();
        } else {
            throw new TypeErrorException("!= : requires two values of the same type");
        }
    }
    
}
