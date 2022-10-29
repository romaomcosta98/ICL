public class ASTNeg implements ASTNode{
    
    ASTNode val;

    public int eval(){
        int v1 = val.eval();
        return -v1;
    }

    public ASTNeg(ASTNode n){
        val = n;
    }
    
}
