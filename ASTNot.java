public class ASTNot implements ASTNode {
    ASTNode lhs;

    public ASTNot(ASTNode l) {
        lhs = l;
    }

    public int eval(Environment env) {
        int v1 = lhs.eval(env);
        return -v1;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        c.emit("ineg");
    }
}
