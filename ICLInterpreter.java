public class ICLInterpreter {
    public static void main(String args[]){
        Parser parser = new Parser(System.in);
        ASTNode exp;
        while(true){
            try{
                exp = parser.start();
                System.out.println(exp.eval());
            }catch(Exception e){
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
    }
}
