import java.io.File;
import java.io.PrintStream;


public class ICLCompiler {

    public static void main(String[] args) {
        Parser parser = new Parser(System.in);
        CodeBlock code = new CodeBlock();
        while(true){
            try{
                ASTNode ast = parser.start();
                ast.compile(code);
                code.dump(new PrintStream(new File("./test.txt"));
            }catch(Exception e){
                System.out.println("Syntax Error");
                parser.ReInit(System.in);
            }
        }
    }
}
