import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ASTDef implements ASTNode{
    
    Map<String, ASTNode> init;
    ASTNode body;

    public ASTDef() {
       init = new HashMap<String, ASTNode>();
    }

    public IValue eval(Environment<IValue> e) throws TypeErrorException{
        e = e.beginScope();
        e = e.beginScope();
        for(String key : init.keySet()){
            IValue value = init.get(key).eval(e);
            e.assoc(key, value);
        }

        IValue bodyEval = body.eval(e);
        e = e.endScope();
        if(bodyEval instanceof VInt || bodyEval instanceof VBool) {
            return bodyEval;
        }
        throw new TypeErrorException("Body of def must be an int or a bool");

    }

    public ASTNode setBody(ASTNode body){
        this.body = body;
        return this;
    }

    public void addBiding(String id, ASTNode value){
        init.put(id, value);
    }

    public void compile(CodeBlock c, Environment<Coordinates> env){
       env = env.beginScope();
       String frame = "frame_" + env.depth();
       c.emit(".class public " + frame);
        c.emit(".super java/lang/Object");
        if(env.depth() == 0){
            c.emit(".field public sl Ljava/lang/Object;");
        }
        else{
            c.emit(".field public sl Lframe_" + (env.depth()-1) + ";");
        }
        for(Entry<String, ASTNode> aux : init.entrySet()){
            c.emit(".field public " + aux.getKey() + " I");
        }
        c.emit(".method public <init>()V");
        c.emit("aload_0");
        c.emit("invokenonvirtual java/lang/Object/<init>()V");
        c.emit("return");
        c.emit(".end method");

        c.emit("new frame_" + env.depth());
        c.emit("dup");
        c.emit("invokespecial frame_" + env.depth() + "/<init>()V");
        c.emit("dup");
        c.emit("aload_3");
        c.emit("putfield frame_" + env.depth() + "/sl Ljava/lang/Object;");
        c.emit("astore_3");

        int counter = 1;
        for(Entry<String, ASTNode> aux : init.entrySet()){
            c.emit("aload_3");
            c.emit("dup");
            c.emit("iload " + counter);
            c.emit("putfield frame_" + env.depth() + "/" + aux.getKey() + " I");
            counter++;
        }
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        e = e.beginScope();
        for(String key : init.keySet()){
            IType value = init.get(key).typecheck(e);
            e.assoc(key, value);
        }
        IType bodyType = body.typecheck(e);
        e = e.endScope();
        if(bodyType instanceof TypeInt || bodyType instanceof TypeBool) {
            return bodyType;
        }
        throw new TypeErrorException("Body of def must be an int or a bool");
       
    }
}
