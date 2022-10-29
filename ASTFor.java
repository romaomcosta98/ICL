public class ASTFor implements ASTNode {
    ASTNode init;
    String id;
    ASTNode incr;
    ASTNode end;
    
    public ASTFor(String id, ASTNode init, ASTNode incr, ASTNode end){
        this.init = init;
        this.id = id;
        this.incr = incr;
        this.end = end;
    }
    
    public int eval(Environment env){
       int v1 = init.eval(env);
       int v2 = end.eval(env);

       int incr = 0;
       for(int i = v1; i < v2; i++){
           env.beginScope();
           env.assoc(id, i);
           int v3 = this.incr.eval(env);
           env.endScope();
           incr += v3;
       }
       return incr;
    }
}

