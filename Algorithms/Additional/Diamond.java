import java.sql.SQLOutput;

public class Diamond {
    public static void Dmnd(int num){
        for(int i = 0; i < 2*num-1; i++){
            for(int a = 0; a <= 2*num-1; a++){
                if(i<num){
                    if(a<num-i){
                        System.out.print(" ");
                    }else if(a>num+i){
                        System.out.print(" ");
                    }else{
                        System.out.print("*");
                    }
                }else{
                    if(a<i-num+2){
                        System.out.print(" ");
                    }else if(a<i-num+3+2*(2*num-2-i)){
                        System.out.print("*");
                    }else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args){
        Dmnd(20);
    }
}
