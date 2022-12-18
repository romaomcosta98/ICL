public class ASTNum implements ASTNode {

int val;

        public ASTNum(int val){
                this.val = val; 
        } 

        public IValue eval(Environment<IValue> env) throws TypeErrorException {
                return new VInt(val);
        }

        @Override
        public void compile(CodeBlock c, Environment<Coordinates> e) {
               c.emit(String.format("sipush %s", val));
        }

        @Override
        public IType typecheck(Environment<IType> e) throws TypeErrorException {
                // TODO Auto-generated method stub
                return null;
        }

}

