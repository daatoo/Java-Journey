public class AllEven {
    public static boolean test(int num){
        int a = 10;
        if(num == 0){
            return false;
        }
        while(num != 0){
            if(((num % a) % 2) == 1){
                return false;
            }
            num /= a;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.print(test(4244));
    }
}
