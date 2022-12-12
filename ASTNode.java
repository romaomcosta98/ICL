public interface ASTNode {

    IValue eval(Environment<IValue> e);

    void compile(CodeBlock c, Environment e);

}

