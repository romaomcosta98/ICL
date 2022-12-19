public class ASTPrint implements ASTNode {
    ASTNode node;
    
    public ASTPrint(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        IValue v1 = node.eval(env);
        if(v1 instanceof VInt) {
            System.out.println(((VInt) v1).getValue());
        } else if(v1 instanceof VBool) {
            System.out.println(((VBool) v1).getValue());
        } else if(v1 instanceof VCell) {
            System.out.println(((VCell) v1).getValue());
        }else {
            throw new TypeErrorException("print : requires a value");
        }
        return v1;
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        node.compile(c, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
       return null;   
    }
}
