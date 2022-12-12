import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length < 1) {
            System.out.println("Missing file name");
        }

        Parser parser = new Parser(System.in);
        ASTNode exp;

        try{
            exp = parser.Start();
            exp.eval();
        } catch (Exception e) {
            System.out.println("Syntax Error!");
            e.printStackTrace();
        }
    }
}