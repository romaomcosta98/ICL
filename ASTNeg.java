public class ASTNeg implements ASTNode{
    
    ASTNode val;

    public int eval(Environment env) {
        int v1 = val.eval(env);
        return -v1;
    }

    public ASTNeg(ASTNode n){
        val = n;
    }

    @Override
    public void compile(CodeBlock c) {
        val.compile(c);
        c.emit("ineg");
    }
    
}
