import java.io.FileOutputStream;
import java.io.PrintStream;

public class ICLCompiler {
    public static void main(String[] args) {
        Parser parser = new Parser(System.in);
        ASTNode exp;
        CodeBlock c;

        try{
            exp = parser.Start();
            c = new CodeBlock(new File(args[1]));

            exp.compile(c, new Environment<>(null));
            c.dump(new PrintStream(new FileOutputStream("Demo.j", false)));
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.out.println("Syntax Error!");
            e.printStackTrace();
            parser.ReInit(System.in);
        }

    }
}
