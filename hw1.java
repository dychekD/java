package java_lessons;
import java.util.Scanner;
public class hw1 {
    public static void main(String[] args) {
        int userNumber = input ();
        System.out.printf("triangular number of %d - %d", userNumber, triangularNumber(userNumber));
        System.out.println();
        showTriangular(userNumber);
    }
    static int input () {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("enter integer n: ");
        String input = iScanner.nextLine();
        int n = Integer.valueOf(input);
        iScanner.close();
        return n;
    }
    static int triangularNumber (int number) {
        int tn = (number*(number+1))/2;
        return tn;
    }
    static void showTriangular (int n) {
        String line = "";
        for (int i = 1; i < n+1; i++) {
            int temp = triangularNumber(i);
            for (int j = 0; j < temp; j++) {
                line +="1"; 
            }
            System.out.println(line);
            line = "";   
        }
    }
}
