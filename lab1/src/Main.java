import java.io.*;
import java.util.*;

public  class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Введіть розмір квадратної матриці:");
        int n = in.nextInt();
        in.nextLine();

        System.out.print("Введіть символ заповнювач:");
        String filler = in.nextLine();

        if (filler.length() != 1){
            System.out.print("Заповнювач - це 1 символ");
            fout.close();
            return;
        }

        char symbol =  filler.charAt(0);

        char[][] arr = new char[n][];
        for (int i = 0; i < n; i++) {
            int count = i + 1;
            arr[i] = new char[count];
            for (int j = 0; j < count; j++) {
                arr[i][j] = symbol;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  ");
                fout.print("  ");
            }

            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
                fout.print(arr[i][j] + " ");
            }
            System.out.println();
            fout.println();
        }
        fout.flush();
        fout.close();

    }
}
