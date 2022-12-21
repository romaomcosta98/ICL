public class ASTBool implements ASTNode {
    boolean value;
    
    public ASTBool(boolean value) {
        this.value = value;
    }
    
    public IValue eval(Environment<IValue> env) {
        return new VBool(value);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> env) {
         if(value){
             c.emit("iconst_1");
         }
         else{
             c.emit("iconst_0");
         }
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        return new TypeBool(); 
    }    
}

