public class ASTNew implements ASTNode {

    TypeRef type;
    ASTNode node;

    public ASTNew(ASTNode node) {
        this.node = node;
    }
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        return new VCell(v1);
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
        
        c.emit("new " + refType);
        c.emit("dup");
        c.emit("invokespecial " + refType + "/<init>()V");
        c.emit("dup");
        node.compile(c, e);
        c.emit("putfield " + refType + "/" + "v" + " " + typeJ);

        



    }
    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = node.typecheck(e);
        type = new TypeRef(v1);
        return type;
    }
    
    
}
