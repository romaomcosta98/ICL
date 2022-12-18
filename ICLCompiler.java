import java.io.FileOutputStream;
import java.io.PrintStream;

public class ICLCompiler {
    public static void main(String[] args) {
        System.out.println("Please insert you code to be compiled: ");
        Parser parser = new Parser(System.in);
        ASTNode exp;

        try{
            exp = parser.Start();
            CodeBlock c = new CodeBlock();
            exp.compile(c, new Environment<>());
            c.dump(new PrintStream(new FileOutputStream("Demo.j", false)));
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.out.println("Syntax Error!");
            e.printStackTrace();
            parser.ReInit(System.in);
        }

    }
}
