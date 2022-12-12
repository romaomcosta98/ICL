public class ASTNum implements ASTNode {

int val;

        public int eval(Environment env) { return val; }

        public ASTNum(int n)
        {
	   val = n;
        }

        @Override
        public void compile(CodeBlock c) {
                c.emit("ldc " + val);
        }

}

