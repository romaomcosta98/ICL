public class ASTEquals implements ASTNode {
    ASTNode lhs, rhs;

    public ASTEquals(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public int eval(Environment e) {
        return 0;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        
    }
    
}
