// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ArraySum {
    public static int Arraysum(int[] arr, int i) {

        if(i < 0){
            return 0;
        }else{
            return arr[i] + Arraysum(arr, i-1);
        }
    }
    public static void main(String[] args) {
        int[] newArr = new int[] {1, 6, 9, -4, 15, 67, 100};
        int sum = Arraysum(newArr, (newArr.length-1));
        System.out.println(sum);


    }
}