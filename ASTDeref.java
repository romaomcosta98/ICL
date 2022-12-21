public class ASTDeref implements ASTNode {
    ASTNode node;
    IType type;

    public ASTDeref(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        if (v1 instanceof VCell) {
            return ((VCell) v1).getValue();
        } else {
            throw new TypeErrorException("! : requires a reference");
        }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        String refType = null;
        String typeJ = null;
        String refClass = null;

        IType typeInRef = ((TypeRef) type).getType();
        if(typeInRef instanceof TypeInt || typeInRef instanceof TypeBool){
            refType = "ref_int";
            typeJ = "I";
            refClass = "ref_int";
        }else{
            refType = "ref_class";
            typeJ = "Ljava/lang/Object;";
            refClass = "ref_class";
        }
        node.compile(c, e);
        c.emit("getfield " + refType + "/" + "v" + " " + typeJ);

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = node.typecheck(e);
        if (v1 instanceof TypeRef) {
            type = ((TypeRef) v1).getType();
            return type;
        } else {
            throw new TypeErrorException("! : requires a reference");
        }
    }
    
}
