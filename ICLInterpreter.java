import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length < 1) {
            System.out.println("Missing file name");
        }

        Parser parser = new Parser(new FileInputStream(new File(args[0])));
        ASTNode exp;

        try{
            exp = parser.Start();
            exp.eval(new Environment<>());
            System.out.println(exp.eval(new Environment<>()));
        } catch (Exception e) {
            System.out.println("Syntax Error!");
            e.printStackTrace();
        }
    }
}