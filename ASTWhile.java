public class ASTWhile implements ASTNode {
    ASTNode lhs, rhs;
    ASTNode cond, body;
    
    public ASTWhile(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        while(((VBool)lhs.eval(env)).getValue()){
            rhs.eval(env);
        }
        return null;

        
    
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        int startLabels = c.CountLabels(2);
        String l1 = "L" + startLabels;
        String l2 = "L" + (startLabels + 1);
        c.emit(l1 + ":");
        cond.compile(c,e);
        c.emit("ifeq " + l2);
        body.compile(c, e);
        c.emit("pop");
        c.emit("goto " + l1);
        c.emit(l2 + ":");




        
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType w = lhs.typecheck(e);
        if(w instanceof TypeBool){
            rhs.typecheck(e);
           return null;
        }
        throw new TypeErrorException("while : requires a boolean");
    }
}
