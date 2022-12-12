public class ASTNum implements ASTNode {

IValue val;

        public ASTNum(IValue v){
                val = v; 
        } 

        public IValue eval(Environment<IValue> env) throws TypeErrorException {
                return val; 
        }

        @Override
        public void compile(CodeBlock c, Environment e) {
                c.emit("iconst " + val);
        }
}

