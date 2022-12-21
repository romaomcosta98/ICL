public class ASTAssign implements ASTNode {
    ASTNode lhs;
    ASTNode rhs;
    IType type;

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
        
        if(lhs instanceof VInt && rhs instanceof VInt){
            lhs.compile(c, e);
            rhs.compile(c, e);
            c.emit("putfield " + refType + "/" + "v" + " " + typeJ);
        }
        else if(lhs instanceof VBool && rhs instanceof VBool){
            lhs.compile(c, e);
            rhs.compile(c, e);
            c.emit("putfield " + refType + "/" + "v" + " " + typeJ);
        }
        
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if(v1 instanceof TypeRef){
            IType v2 = rhs.typecheck(e);
            if(v2 instanceof TypeRef){
                return v1;
            }
        }
        throw new TypeErrorException(":= : requires a reference");
    }
    
    
}
