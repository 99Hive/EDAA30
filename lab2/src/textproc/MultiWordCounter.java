package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{


    private Map<String, Integer> map = new TreeMap<String, Integer>();


    public MultiWordCounter(String[] a){
        for(String b : a){
            this.map.put(b, 0);
        }
    }

    public void process(String w){
        if(map.containsKey(w)){
            int nbr = map.get(w);
            map.replace(w, nbr += 1);

        }
    }

    public void report(){

        for(String key : map.keySet()){
            System.out.println(key + " Förekommer: " +  map.get(key)+ " Gånger");
        }

    }
    
}
