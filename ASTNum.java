public class ASTNum implements ASTNode {

int val;

        public ASTNum(int v){
                val = v; 
        } 

        public int eval(Environment env) { 
                return val; 
        }
}

