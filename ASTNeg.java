public class ASTNeg implements ASTNode{
    
    ASTNode val;

    public int eval(Environment env) {
        int v1 = val.eval(env);
        return -v1;
    }

    public ASTNeg(ASTNode n){
        val = n;
    }
    
}
