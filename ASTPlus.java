public class ASTPlus implements ASTNode {

ASTNode lhs, rhs;

        public ASTPlus(ASTNode l, ASTNode r){  
                lhs = l; 
                rhs = r;
        }

        public int eval(Environment env){ 
                int v1 = lhs.eval(env);
                int v2 = rhs.eval(env);
                return v1+v2; 
	}

        @Override
        public void compile(CodeBlock c, Environment env) {
                lhs.compile(c,env);
                rhs.compile(c,env);
                c.emit("iadd");
                
        }
}

