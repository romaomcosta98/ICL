import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class CodeBlock {

    String code[];
    int pos;

    //tem de receber o nome do ficheiro
    public CodeBlock() {
        this.code = new String[100];
        this.pos = 0;
    }

    public void emit(String bytecode) {
        if(pos >= code.length) {
            String[] aux = new String[2 * code.length];
            for(int i = 0; i < code.length; i++)
                aux[i] = code[i];
            code = aux;
        }
        code[pos] = bytecode;
        pos++;
    }

    public void dump(PrintStream f) {
        f.println(".class public Demo");
        f.println(".super java/lang/Object");
        f.println(".method public <init>()V");
        f.println("aload_0");
        f.println("invokenonvirtual java/lang/Object/<init>()V");
        f.println("return");
        f.println(".end method");
        f.println(".method public static main([Ljava/lang/String;)V");
        f.println(".limit locals  10");
        f.println(".limit stack 256");
        f.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        for (int i = 0; i < pos; i++) {
            f.println(code[i]);
        }
        f.println("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        f.println("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        f.println("return");
        f.println(".end method");
    }

    public void newFrame(int depth, int num) {
        String frameName = "frame_" + depth;
        PrintStream f;
        try {
            f = new PrintStream(new FileOutputStream(frameName + ".j", false));
            CodeBlock c = new CodeBlock();
            c.emit(String.format(".class public %s", frameName));
            c.emit(".super java/lang/Object");
            if (depth == 0)
                c.emit(".field public sl Ljava/lang/Object;");
            else
                c.emit(String.format(".field public sl Lframe_%d;", depth-1));
            for(int i= 0; i < num; i++) {
                String slot = "v"+i;
                c.emit(String.format(".field public %s I", slot));
            }
            c.emit(".method public <init>()V");
            c.emit("aload_0");
            c.emit("invokenonvirtual java/lang/Object/<init>()V");
            c.emit("return");
            c.emit(".end method");
            for (int i = 0; i < c.pos; i++) {
                f.println(c.code[i]);
            }
            System.out.println(String.format("[Compiler] Generated %s.j", frameName));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public int CountLabels(int i) {
        return i;
    }
}
