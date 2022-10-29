public class ASTNum implements ASTNode {

int val;

        public int eval(Environment env) { return val; }

        public ASTNum(int n)
        {
	   val = n;
        }

}

