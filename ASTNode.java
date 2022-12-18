public interface ASTNode {

    IValue eval(Environment<IValue> e) throws TypeErrorException;

    void compile(CodeBlock c, Environment<Coordinates> env);

    IType typecheck(Environment<IType> e) throws TypeErrorException;

}

