import java.util.HashMap;
import java.util.Map;

public class Environment<T> {
    
    private Map<String, T> mappings = new HashMap<String, T>();
    private Environment<T> prevEnv;

    public Environment(Environment<T> prevEnvironment) {
        this.prevEnv = prevEnvironment;
    }

    Environment<T> beginScope(){
        return new Environment<T>(this);
    } //push level

    Environment<T> endScope(){
        return prevEnv;
    } //pop level

    int depth(){
        if(prevEnv == null){
            return 0;
        }
        else{
            return prevEnv.depth() + 1;
        }
    }
    
    void assoc(String id, T value){
       mappings.put(id, value);
    }
    T find(String id){
       T aux = mappings.get(id);
         if(aux == null){
              if(prevEnv == null){
                throw new RuntimeException("Variable " + id + " not found");
              }
              else{
                return prevEnv.find(id);
              }
         }
         else{
              return aux;
    }
    }
}
