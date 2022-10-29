public class ASTId {
    
    String id;

    public ASTId(String id) {
        this.id = id;
    }
    public int eval(Environment e){
        return e.find(id);
    }
}
