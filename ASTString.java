public class ASTString implements ASTNode {
    String value;
    
    public ASTString(String value) {
        this.value = value;
    }
    
    public IValue eval(Environment<IValue> env) {
        return new VString(value);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> env) {
        String quote = "\"" + value + "\"";
        String stringEmit = "ldc " + quote;

        c.emit(stringEmit);
        
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        return new TypeString(); 
    }    
}
