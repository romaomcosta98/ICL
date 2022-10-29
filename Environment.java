import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
public class Environment {
    private List<HashMap<String, Integer>> env;
    
    public Environment() {
       env = new LinkedList<>();
    }

    Environment beginScope(){
        return new Environment();
    } //push level
    Environment endScope(){
        return this;
    } //pop level

    void assoc(String id, int value){
        map.put(id, value);
    }
    int find(String id){
        
    }


}
