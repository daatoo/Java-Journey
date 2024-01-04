import java.util.ArrayList;
import java.util.HashMap;

public class Cart<T extends MarketItem>{
    private HashMap<Integer, ArrayList<T>> cart = new HashMap<>();

    public void addItem(T item, int n) throws IllegalArgumentException{
        if(item == null || n <= 0){
            throw new IllegalArgumentException("Illegal arguments");
        }

        ArrayList<T> current = cart.get(item.hashCode());
        if(current == null){
            current = new ArrayList<>();
        }
        for(int i = 0; i < n; i++){
            current.add(item);
        }
        cart.put(item.hashCode(), current);
    }
    public boolean removeItem(T item, int n){
        if(item == null || !cart.containsKey(item.hashCode())){
            throw new IllegalArgumentException("item is null or not in cart");
        }
        if(n<=0)
            throw new IllegalArgumentException("n must me positive");

        ArrayList<T> current = cart.get(item.hashCode());
        if(current.size() < n)
            return false;

        for(int i = 0; i < n; i++){
            current.remove(item);
        }

        if(current.isEmpty()){
            cart.remove(item.hashCode());
        }else{
            cart.put(item.hashCode(), current);
        }
        return true;
    }
    public int calculatePrice(){
        return cart.entrySet().stream()
                .mapToInt(b -> b.getValue().size() * b.getValue().get(0).getPrice())
                .sum();
    }
    public int checkout(int money) throws IllegalArgumentException{
        if(money < 0)
            throw new IllegalArgumentException("illegal argument");
        money -= calculatePrice();
        cart = new HashMap<>();
        return money;

    }

}