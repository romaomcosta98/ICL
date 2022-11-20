public class ASTAdd implements ASTNode {

        ASTNode lhs, rhs;
        
        public ASTAdd(ASTNode l, ASTNode r){
            lhs = l;
            rhs = r;
        }

        public int eval(Environment env) {
            int v1 = lhs.eval(env);
            int v2 = rhs.eval(env);
            return v1+v2;
        }

        public void compile(CodeBlock c){
            lhs.compile(c);
            rhs.compile(c);
            c.emit("iadd");
        }
        
}

