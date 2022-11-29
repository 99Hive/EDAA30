package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{

    public Map<String, Integer> map = new TreeMap<String, Integer>();
    private Set<String> stopwords;


    public GeneralWordCounter(Set<String> stopwords){
        this.stopwords = stopwords;
    }


    public void process(String w){
        if(map.containsKey(w)){
            map.replace(w, map.get(w)+1);
        }
        else if(!stopwords.contains(w)){
            map.put(w, 1);
        }
    }

    public void report(){
        /*for(String key : map.keySet()){
            if(map.get(key) >= 200){
                System.out.println(key + ": förekommer " + map.get(key) + " gånger");
            }
        }*/
        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        wordList.sort((a,b) -> {

            if(b.getValue().compareTo(a.getValue()) != 0 ){
                return b.getValue().compareTo(a.getValue()); 
            } else {
                return a.getKey().compareTo(b.getKey()); 
            }
        });



        for(int i = 0; i < 5; i++){
            System.out.println("------------" + wordList.get(i));
        }
        
    }

    public List<Map.Entry<String, Integer>> getWordList(){

        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        return wordList;

    }
    
}