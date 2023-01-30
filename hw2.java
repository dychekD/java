// +Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму. 
// Пример 1: а = 3, b = 2, ответ: 9 
// Пример 2: а = 2, b = -2, ответ: 0.25
// Пример 3: а = 3, b = 0, ответ: 1
// Пример 4: а = 0, b = 0, ответ: не определено
// Пример 5
// входные данные находятся в файле input.txt в виде
// b 3
// a 10
// Результат нужно сохранить в файле output.txt
// 1000
package java_lessons;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class hw2 {
    public static void main(String[] args) throws Exception {
        int [] values = getValues();
        System.out.println(Arrays.toString(values));
        Double res = exponentiation(values);
        writeToFile(res);
    }
    static int [] getValues () {
        String aAsString = "";
        String bAsString = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader ("input.txt"));
            String line;
            while ((line = br.readLine()) !=null) {
                System.out.println(line);
                if (line.startsWith("a ") == true) {
                    for (int i = 2; i < line.length(); i++) {
                        aAsString += line.charAt(i);
                    }
                }
                if (line.startsWith("b ") == true) {
                    for (int i = 2; i < line.length(); i++) {
                        bAsString += line.charAt(i);
                    }
                }
                line = "";
            }
            br.close();
            if (aAsString == "" || bAsString == "") {
                System.out.println("the file is missing a value");
                int [] empty = new int [] {0, 0};
                return empty;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
        int a = Integer.valueOf(aAsString);
        int b = Integer.valueOf(bAsString);
        int [] arr = new int [] {a, b};
        return arr;       
    }
    static double exponentiation (int [] arr) {
        double result = 0;
        if (arr [0] == 0 && arr [1] == 0) {
            System.out.println("value not defined");
            return result;
        }
        else result = Math.pow (arr [0], arr [1]);
        System.out.printf("%d ^ %d = %.4f \n", arr[0], arr [1], result);
        return result;
    }
    static void writeToFile (Double result) {
        try {
            FileWriter fw = new FileWriter("output.txt");
            if (result == 0) {
                fw.write ("not defined");
            }
            else {
                fw.write (result.toString());
            }
            fw.close();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}

