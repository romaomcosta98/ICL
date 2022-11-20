import java.util.Map;
import java.util.Map.Entry;

public class ASTDef {
    
    Map<String, ASTNode> init;
    ASTNode body;
    
    public ASTDef(Map<String, ASTNode> init, ASTNode body){
        this.init = init;
        this.body = body;
    }

    int eval(Environment e){
        e = e.beginScope();
        int v;
        for(Entry<String, ASTNode> aux : init.entrySet()){
            v = aux.getValue().eval(e);
            e.assoc(aux.getKey(), v);
        }
        v = body.eval(e);
        e = e.endScope();
        return v;

    }

    void compile(CodeBlock c){
        c.emit("new Environment");
        c.emit("dup");
        c.emit("invokespecial Environment/<init>()V");
        c.emit("astore_1");
        for(Entry<String, ASTNode> aux : init.entrySet()){
            c.emit("aload_1");
            aux.getValue().compile(c);
            c.emit("invokevirtual Environment/assoc(Ljava/lang/String;I)V");
        }
        c.emit("aload_1");
        body.compile(c);
        c.emit("areturn");
    };

    
}
