package fop.w2fac;

public class Factorization extends MiniJava {

    public static void main(String[] args){
	// TODO
        int number = readInt("Please insert a number:" );

        if(number < 2){
            write("Error: n>1 expected!");
            return;
        }
        for(int i = 2; i<=number; i++){
            while(number%i==0){
                number /= i;
                writeConsole(i + " ");
            }
        }
    }
}
