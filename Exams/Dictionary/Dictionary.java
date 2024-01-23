import java.util.HashMap;
import java.util.Map;

public class Dictionary<T> {
    public T value;
    private Map<Character, Dictionary<T>> h;

    public Dictionary(T value){
        this.value = value;
        this.h = new HashMap<>();
    }

    public void record(String s, T value){
        if(s.length() > 1){
            if(!h.containsKey(s.charAt(0))){
                h.put(s.charAt(0), new Dictionary<>(null));
            }
            h.get(s.charAt(0)).record(s.substring(1), value);
        }else if(s.length() == 1){
            h.put(s.charAt(0), new Dictionary<>(value));
        }
    }
    public T lookup(String s){
        if(s.length() > 1){
            return h.get(s.charAt(0)).lookup(s.substring(1));
        }else if(s.length() == 1){
            return h.get(s.charAt(0)).value;
        }
        return null;
    }
    public T remove(String s){
        if(s.length() > 1){
            return h.get(s.charAt(0)).lookup(s.substring(1));
        }else if(s.length() == 1){
            return h.remove(s.charAt(0)).value;
        }else{
            return null;
        }
    }
    public boolean isEmpty(){
        if(h.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Dictionary<Meaning<Integer>> test = new Dictionary<>(null);

        test.record(args[0], () -> 1);
        test.record(args[1], () -> 2);
        test.record(args[2], () -> 3);

        for(int i = 0; i < args.length; i++){
            int a = i;
            test.record(args[i], () -> a);
        }

        for(int i = 0; i < args.length; i++){
            System.out.println(args[i] + ": " + test.remove(args[i]).meaning());
        }

        //StringBuilder a = new StringBuilder();
        //System.out.println(test.isEmpty());
        //System.out.println(test.h.get('f').h.get('o').h.get('o').value.meaning());
       // System.out.println(test.lookup("foo"));
    }
}
