public class ASTMul implements ASTNode{

    ASTNode lhs, rhs;

    @Override
    public int eval(Environment env) {
        int v1 = lhs.eval(env);
        int v2 = rhs.eval(env);
        return v1*v2;
    }

    public ASTMul(ASTNode l, ASTNode r){
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock c) {
    }
    

}