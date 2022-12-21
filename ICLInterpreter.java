import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser(System.in);
        ASTNode exp;

        while(true){
            System.out.println("Please insert you code to be interpreted: ");
            try{
                exp = parser.Start();
                System.out.println(exp.eval(new Environment<>(null)).toString());
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}