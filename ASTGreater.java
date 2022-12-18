public class ASTGreater implements ASTNode {

    ASTNode lhs;
    ASTNode rhs;

    @Override
    public IValue eval(Environment<IValue> env) throws TypeErrorException {
       IValue v1 = lhs.eval(env);
       IValue v2 = rhs.eval(env);
         if (v1 instanceof VInt && v2 instanceof VInt) {
              return new VBool(((VInt) v1).getValue() > ((VInt) v2).getValue());
         } else {
              throw new TypeErrorException("> : requires two integers");
         }
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
     lhs.compile(c, e);
     rhs.compile(c, e);
     c.emit("if_icmpgt");
    }

@Override
public IType typecheck(Environment<IType> e) throws TypeErrorException {
     // TODO Auto-generated method stub
     return null;
}
    
}
