public class ASTId implements ASTNode {
    String id;

    public ASTId(String id) {
        this.id = id;
    }

    public int eval(Environment env) {
        return env.find(id);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        c.emit("iload " + e.find(id)); 
    }
}
