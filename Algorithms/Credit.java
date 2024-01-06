import java.util.Scanner;

public class Credit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Card Number: ");
        long number = scanner.nextLong();

        if (checkValidity(number)) {
            detectCardType(number);
        } else {
            System.out.println("INVALID");
        }

        scanner.close();
    }

    private static boolean checkValidity(long num) {
        int sum = 0;

        for (int i = 0; num != 0; i++, num /= 10) {
            if (i % 2 == 0) {
                sum += (int) (num % 10);
            } else {
                int doubled = (int) (num % 10) * 2;
                sum += doubled / 10 + doubled % 10;
            }
        }

        return sum % 10 == 0;
    }

    private static void detectCardType(long num) {
        int length = getNumberLength(num);

        if (length == 13 && (num / 1000000000000L == 4)) {
            System.out.println("VISA");
        } else if (length == 16 && (num / 1000000000000000L == 4)) {
            System.out.println("VISA");
        } else if (length == 16 && ((num / 100000000000000L >= 51 && num / 100000000000000L <= 55)) || num / 100000000000000L == 22) {
            System.out.println("MASTERCARD");
        } else if (length == 15 && (num / 10000000000000L == 34 || num / 10000000000000L == 37)) {
            System.out.println("AMEX");
        } else {
            System.out.println("INVALID");
        }
    }

    private static int getNumberLength(long num) {
        int n = 0;
        while (num > 0) {
            num /= 10;
            n++;
        }
        return n;
    }
}
