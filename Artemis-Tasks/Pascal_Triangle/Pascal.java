package fop.w3pascal;

class Pascal extends MiniJava {
    // Function for computing the first n rows of Pascal's triangle.
    public static int[][] pascal(int n) {
	// TODO
        int[][] pascal = new int[n+1][];
        for(int i = 0; i <= n; i++){
            pascal[i] = new int[i+1];
            pascal[i][0] = 1;
            pascal[i][i] = 1;

            for(int j = 1; j < i; j++){
                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
            }

        }
        return pascal;
    }

    public static void main(String[] args) {
        // We assume that n >= 0 holds.
        // TODO
        int n = readInt("length");
        int[][] triangle = pascal(n);

        for(int i = 0; i < n; i++){
            writeConsole("n=" + i + "    ");
            for(int a = 0; a < triangle[i].length; a++){
                writeConsole(triangle[i][a] + "   ");
            }
            writeConsole("\n");
        }
    }
}
