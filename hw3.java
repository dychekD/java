package java_lessons;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
public class hw3 {
    public static void main(String[] args) {
        Scanner is = new Scanner(System.in);
        List<Integer> size = input(is);
        List<Integer> position = FigurePosition (size, is);
        int routes = NumberOfRoutes(size, position);
        is.close();
    }
    static List<Integer> input (Scanner is) {
        System.out.printf("enter size of the board X x Y: \n");
        System.out.printf("enter X: ");
        String input = is.nextLine();
        int x = Integer.valueOf(input);
        System.out.printf("enter Y: ");
        input = is.nextLine();
        int y = Integer.valueOf(input);
        List<Integer> result = List.of(x, y);
        return result;
    }

    static List<Integer> FigurePosition (List<Integer> size, Scanner is) {
        System.out.printf("enter the position of the figure (a, b): \n");
        System.out.printf("enter a: ");
        String input = is.nextLine();
        int a = Integer.valueOf(input);
        if (a > size.get(0)) {
            System.out.printf("enter a < %d: ", size.get(0));
            input = is.nextLine();
            a = Integer.valueOf(input);
        }
        System.out.printf("enter b: ");
        input = is.nextLine();
        int b = Integer.valueOf(input);
        if (b > size.get(1)) {
            System.out.printf("enter b < %d: ", size.get(1));
            input = is.nextLine();
            b = Integer.valueOf(input);
        }
        List<Integer> position = List.of(a, b);
        return position;
    }

    static int NumberOfRoutes (List<Integer> size, List<Integer> position){
        int a = size.get(0) - position.get(0)+1;
        int b = size.get(1) - position.get(1)+1;
        int [][] board = new int [a][b];
        for (int i = 1; i<a; i++) {
            for (int j = 1; j < b; j++) {
                if (i == 1 && j ==1) {
                    board [i][j] = 1;
                }
                else board [i][j] = board [i] [j-1] + board [i-1] [j];
            }
        }
        for (int [] temp: board) {
            System.out.println(Arrays.toString(temp));
        }
        int result = board [a-1] [b-1];
        System.out.printf("the number of routes for the figure on the position (%d, %d) on the board %d x %d is %d", position.get(0), position.get(1), size.get (0), size.get (1), result);
        return result;
    }

}
