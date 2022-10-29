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

    
}
