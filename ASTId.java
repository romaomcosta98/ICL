public class ASTId implements ASTNode {
    String id;

    public ASTId(String id) {
        this.id = id;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        return env.find(id);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        c.emit("iload " + e.find(id)); 
    }
}
