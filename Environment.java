import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Integer> env;
    private Environment prevEnv;

    public Environment(Environment prevEnvironment) {
        this.env = new HashMap<String, Integer>();
        this.prevEnv = prevEnv;
    }

    Environment beginScope(){
        return new Environment(this);
    } //push level

    Environment endScope(){
        return prevEnv;
    } //pop level

    void assoc(String id, int value){
       Integer val = env.putIfAbsent(id, value);
       if(val != null){
           throw new RuntimeException("Variable already defined: " + id);
       }
    }
    int find(String id){
        Integer val = env.get(id);
        if(val != null){
          return val.intValue();
        }
        if(prevEnv != null){
            return prevEnv.find(id);
        }
    
        throw new RuntimeException("Variable not defined: " + id);
    }
}
