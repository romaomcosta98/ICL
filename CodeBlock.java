import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

class CodeBlock {
    
    List<String> code;
    
    public CodeBlock(){
        code = new LinkedList<String>();
    }

    void emit(String opcode){
        code.add(opcode);
    }

    void dump(PrintStream f){
        f.println();
        for(String line: code){
            f.println(line);
            System.out.println(line);
        }
        f.println();
    }
}
