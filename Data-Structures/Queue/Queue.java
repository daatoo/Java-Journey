public class Queue {
    private int first, last;
    private int[] a;
    public Queue(){
        first = last = -1;
        a = new int[4];
    }
    public boolean isEmpty() { return first == -1; }
    public void enqueue(int x){
        if(first == -1){
            first = last = 0;
        }else{
            int n = a.length;
            last = (last+1)%n;
            if(last == first){
                int[] b = new int[2*n];
                for(int i = 0; i<n; ++i){
                    b[i] = a[(first+1)%n];
                }
                first = 0;
                last = n;
                a = b;
            }
            a[last] = x;
        }
    }
    public int dequeue(){
        int result = a[first];
        if(last == first){
            first = last = -1;
            return result;
        }
        int n = a.length;
        first = (first+1)%n;
        int diff = (last+n-first)%n;

        if(diff > 0 && diff < n/4){
            int[] b = new int[n/2];
            for(int i = 0; i <= diff; i++){
                b[i] = a[(first+i)%n];
            }
            first = 0;
            last = diff;
            a = b;
        }
        return result;
    }

}
