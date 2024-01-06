public class StringHash {
    public static void main(String[] args) {
        String s = "example"; // Replace with your string
        int p = 31; // A common choice for p, a prime number
        long hashValue = hash(s, p);
        System.out.println("Hash value: " + hashValue);
    }

    private static long hash(String s, int p) {
        long hash = 0;
        long pPow = 1; // p to the power of i

        for (int i = 0; i < s.length(); i++) {
            hash = (hash + (s.charAt(i) - 'a' + 1) * pPow) % Long.MAX_VALUE;
            pPow = (pPow * p) % Long.MAX_VALUE;
        }

        return hash;
    }
}
