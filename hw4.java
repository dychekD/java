package java_lessons;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
public class hw4 {
    public static void main(String[] args) {
        Scanner is = new Scanner(System.in);
        List<Integer> size = Input (is);
        int [][] field = WallGeneration(size);
        int [][] fieldWithWalls = new int [size.get(0)][size.get(1)];
        for (int i = 0; i < size.get(0); i++) {
            for (int j = 0; j < size.get (1);j++) {
                fieldWithWalls [i][j] = field [i][j];
            }
        }
        List<Integer> start = StartPoint(field, is);
        List<Integer> exit = ExitPoint(field, start, is);
        field = FieldWithPoints(field, start, exit);
        field = WaveAlgorithm(field, size, start);
        fieldWithWalls = ShortestPath(fieldWithWalls, field, size, start, exit);
        is.close();
    }
    static List<Integer> Input (Scanner is) {
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
    static int [][] WallGeneration (List<Integer> size) {
        int a = size.get(0);
        int b = size.get(1);
        int [][] field = new int [a][b];
        int count = 0;
        int i = 0;
        int j = 0;
        while (count < (0.2 * a * b)){
            i = (int) (Math.random() * a);
            j = (int) (Math.random() * b);
            field [i][j] = -1;
            count++;
        }
        System.out.println();
        System.out.println("-1 marks the impassable walls");
        for (int [] item: field) {
            System.out.println(Arrays.toString(item));
        }
        return field;
    }
    static List<Integer> StartPoint (int[][] field, Scanner is) {
        System.out.printf("enter coordinates of the start point \n");
        System.out.printf("enter X: ");
        String input = is.nextLine();
        int x = Integer.valueOf(input);
        System.out.printf("enter Y: ");
        input = is.nextLine();
        int y = Integer.valueOf(input);
        List<Integer> start = List.of(x, y);
        if (field [start.get(0)] [start.get(1)] == -1) {
            System.out.printf("enter coordinates of the start point, not equal to the coordinates of the wall \n");
            System.out.printf("enter X: ");
            input = is.nextLine();
            x = Integer.valueOf(input);
            System.out.printf("enter Y: ");
            input = is.nextLine();
            y = Integer.valueOf(input);
            start = List.of(x, y);
        }
        return start;
    }
    static List<Integer> ExitPoint (int [][] field, List<Integer> start, Scanner is) {
        System.out.printf("enter coordinates of the exit \n");
        System.out.printf("enter X: ");
        String input = is.nextLine();
        int x = Integer.valueOf(input);
        System.out.printf("enter Y: ");
        input = is.nextLine();
        int y = Integer.valueOf(input);
        List<Integer> exit = List.of(x, y);
        if (field [exit.get(0)] [exit.get(1)] == -1) {
            System.out.printf("enter coordinates of the exit point, not equal to the coordinates of the wall \n");
            System.out.printf("enter X: ");
            input = is.nextLine();
            x = Integer.valueOf(input);
            System.out.printf("enter Y: ");
            input = is.nextLine();
            y = Integer.valueOf(input);
            exit = List.of(x, y);
        }
        if (exit.get(0) == start.get(0) && exit.get(1) == start.get (1)) {
            System.out.printf("enter coordinates of the exit point, not equal to the coordinates of the start point \n");
            System.out.printf("enter X: ");
            input = is.nextLine();
            x = Integer.valueOf(input);
            System.out.printf("enter Y: ");
            input = is.nextLine();
            y = Integer.valueOf(input);
            exit = List.of(x, y);
        }
        return exit;
    }
    static int [][] FieldWithPoints (int [][] field, List<Integer> start, List<Integer> exit){
        field [start.get(0)][start.get(1)] = 1;
        field [exit.get(0)][exit.get(1)] = 2;
        System.out.println();
        System.out.println("1 marks the start point, 2 marks the exit point");
        for (int [] item: field) {
            System.out.println(Arrays.toString(item));
        }
        field [exit.get(0)][exit.get(1)] = 0;
        return field;
    }
    static int [][] WaveAlgorithm (int [][] field, List<Integer> size, List<Integer> start) {
        Queue<List<Integer>> qu = new LinkedList <>();
        qu.add (start);
        int i = start.get (0);
        int j = start.get (1);
        List<Integer> coordinates = qu.peek();
        List<Integer> newCoordinates = List.of (i,j);
        while (coordinates != null) {
            i = coordinates.get (0);
            j = coordinates.get (1);
            if (i-1 >= 0 && field [i-1] [j] == 0 ) {
                field [i-1][j] = field [i][j] + 1;
                newCoordinates = List.of(i-1, j);
                qu.add(newCoordinates);
            }
            if (j+1 < size.get (1) && field [i] [j+1] == 0) {
                field [i][j+1] = field [i][j] + 1;
                newCoordinates = List.of(i, j+1);
                qu.add(newCoordinates);
            }
            if (i+1 < size.get(0) && field [i+1] [j] == 0) {
                field [i+1][j] = field [i][j] + 1;
                newCoordinates = List.of (i + 1, j);
                qu.add(newCoordinates);
            }
            if (j - 1 >= 0 && field [i] [j-1] == 0) {
                field [i][j-1] = field [i][j] + 1;
                newCoordinates = List.of (i, j-1);
                qu.add(newCoordinates);
            }
            qu.poll();
            coordinates = qu.peek();
        }
        System.out.println();
        for (int [] item: field) {
            System.out.println(Arrays.toString(item));
        }
        return field;
    }
    static int [][] ShortestPath (int [][] fieldWithWalls, int [][] field, List<Integer> size, List<Integer> start, List<Integer> exit) {
        if (field [exit.get(0)] [exit.get(1)] == 0) {
            System.out.println("there is no path from start point to exit point");
        }
        else {
            ArrayDeque<List<Integer>> dequ = new ArrayDeque<List<Integer>>();
            dequ.add(exit);
            List<Integer> coordinates = dequ.peek();
            List<Integer> newCoordinates = dequ.peek();
            int i = coordinates.get (0);
            int j = coordinates.get (1);
            fieldWithWalls [i] [j] = field [i] [j];
            while (field [i] [j] != 1){
                i = coordinates.get (0);
                j = coordinates.get (1);
                fieldWithWalls [i] [j] = field [i] [j];
                if (i-1 >= 0 && field [i-1] [j] == field [i] [j] - 1) {
                    newCoordinates = List.of(i-1, j);
                    dequ.addFirst(newCoordinates);
                }
                else if (j+1 < size.get (1) && field [i] [j+1] == field [i] [j] - 1) {
                    newCoordinates = List.of(i, j+1);
                    dequ.addFirst(newCoordinates);
                }
                else if (i+1 < size.get(0) && field [i+1] [j] == field [i] [j] - 1) {
                    newCoordinates = List.of (i + 1, j);
                    dequ.addFirst(newCoordinates);
                }
                else if (j - 1 >= 0 && field [i] [j-1] == field [i] [j] - 1) {
                    newCoordinates = List.of (i, j-1);
                    dequ.addFirst(newCoordinates);
                }
                coordinates = dequ.peekFirst();        
            }
            System.out.println ();
            System.out.println ("The shortest path from start point to exit point: ");
            for (List<Integer> item: dequ) {
                System.out.println(item);
            }
            System.out.println ();
            for (int [] item: fieldWithWalls) {
                System.out.println(Arrays.toString(item));
            }
        }
        return fieldWithWalls;
    }
} 
