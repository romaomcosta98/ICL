public class ASTId implements ASTNode {
    String id;

    public ASTId(String id) {
        this.id = id;
    }

    public IValue eval(Environment<IValue> env) throws TypeErrorException {
        return env.find(id);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        c.emit("aload " + e.find(id));
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        // TODO Auto-generated method stub
        return null;
    }
}
