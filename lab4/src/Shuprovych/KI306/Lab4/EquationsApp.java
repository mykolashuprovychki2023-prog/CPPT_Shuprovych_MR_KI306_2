package Shuprovych.KI306.Lab4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Драйвер для класу Equations.
 * Читає ім'я файлу і значення X з консолі, обчислює y і записує результат у файл.
 */
public class EquationsApp {

    public static void main(String[] args) {
        // Scanner для читання з консолі — закривається через try-with-resources
        try (Scanner in = new Scanner(System.in)) {

            out.print("Enter file name: ");
            String fName = in.nextLine().trim();

            // Відкриваємо файл для запису у try-with-resources (автоматичне закриття)
            try (PrintWriter fout = new PrintWriter(new FileWriter(fName, true))) {

                Equations eq = new Equations();

                out.print("Enter X (integer degrees): ");
                int x;
                try {
                    x = in.nextInt();
                } catch (InputMismatchException ime) {
                    out.println("Input error: Please enter an integer value for X.");
                    return;
                }

                try {
                    double result = eq.calculate(x);
                    fout.println("--------------------");
                    String outLine = String.format("For x = %d, y = %.10f", x, result);
                    // Вивід у файл та на консоль
                    fout.println(outLine);
                    out.println("Result written to file: " + fName);
                    out.println(outLine);
                } catch (CalcException ce) {
                    // Конкретна помилка обчислення
                    fout.println("--------------------");
                    String err = String.format("ERROR for x = %d: %s", x, ce.getMessage());
                    fout.println(err);
                    out.println(err);
                }

            } catch (IOException ioe) {
                // Проблема з відкриттям файлу для запису
                out.println("Exception reason: Cannot open file for writing or IO error. Perhaps wrong path or permissions.");
            }

        } // Scanner закривається автоматично тут
    }
}
