package Shuprovych.KI306.lab5;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Клас EquationsApp — головний клас-драйвер програми лабораторної №5.
 *
 * Призначення:
 *  - Зчитує з клавіатури ім'я файлу та значення X (у градусах).
 *  - Обчислює функцію y = cos(x) / (x + 2 * ctg(x)) за допомогою класу Equations.
 *  - Записує результат у текстовий і бінарний файли.
 *  - Читає результат назад із файлів для перевірки коректності.
 *  - У разі помилки обчислення — записує повідомлення про помилку у текстовий лог.
 */
public class EquationsApp {

    public static void main(String[] args) {
        // Створюємо об'єкт для роботи з файлами (читання/запис)
        ResultIO io = new ResultIO();

        // Використовуємо try-with-resources, щоб Scanner автоматично закрився після завершення
        try (Scanner in = new Scanner(System.in).useLocale(Locale.US)) {

            //Зчитування імені файлу
            out.print("Enter file name: ");
            String base = in.nextLine().trim();
            if (base.isEmpty()) {
                out.println("Empty file name. Exiting.");
                return; // якщо користувач не ввів ім'я — завершуємо програму
            }

            // Формуємо повні імена файлів (текстовий і бінарний)
            String txtFile = base + ".txt";
            String binFile = base + ".bin";

            //Зчитування аргументу X
            out.print("Enter X (degrees): ");
            double x;
            try {
                x = in.nextDouble(); // вводимо значення X
            } catch (InputMismatchException ime) {
                out.println("Input error: Please enter a numeric value for X.");
                return; // некоректне введення — завершення
            }

            // Обчислення функції та запис результатів
            try {
                Equations eq = new Equations();  // створюємо об'єкт для обчислень
                double result = eq.calculate(x); // обчислюємо y для заданого X

                // Запис числового результату у текстовий файл (додаємо в кінець)
                io.writeResultTxt(txtFile, result, true);

                // Запис результату у бінарний файл (також додаємо)
                io.writeResultBin(binFile, result, true);

                // Запис текстового повідомлення у лог
                io.writeText(txtFile, String.format(Locale.US, "For x = %.6f, y = %.10f", x, result), true);
                io.writeText(txtFile, "--------------------", true);

                // Виводимо коротку інформацію користувачу
                out.println("Result written to files:");
                out.println("  text: " + txtFile);
                out.println("  binary: " + binFile);
                out.println(String.format(Locale.US, "For x = %.6f, y = %.10f", x, result));

                // Перевірка: читаємо результат назад із файлів
                try {
                    // читаємо останнє число з кожного файлу
                    double yTxt = io.readLastDoubleFromTxt(txtFile);
                    double yBin = io.readLastDoubleFromBin(binFile);

                    // показуємо зчитані значення для перевірки
                    out.println("Read back from txt (last double): " + yTxt);
                    out.println("Read back from bin (last double): " + yBin);
                } catch (IOException readEx) {
                    // якщо файл не знайдено або формат не підходить
                    out.println("Warning: could not read back values: " + readEx.getMessage());
                }

                // Обробка можливих помилок
            } catch (CalcException ce) {
                // Якщо сталася помилка в обчисленні (наприклад, sin(x) = 0)
                String err = String.format("ERROR for x = %.6f: %s", x, ce.getMessage());
                try {
                    // Записуємо повідомлення про помилку у текстовий лог
                    io.writeText(txtFile, "--------------------", true);
                    io.writeText(txtFile, err, true);
                } catch (IOException ioe) {
                    // Якщо навіть лог не вдалося записати — просто показуємо в консолі
                    out.println("Failed to write error to file: " + ioe.getMessage());
                }
                out.println(err); // також виводимо повідомлення на екран

            } catch (IOException ioe) {
                // Якщо сталася помилка введення/виведення (I/O)
                out.println("I/O error while writing files: " + ioe.getMessage());
            }

        } // Scanner автоматично закривається тут після виходу з блоку try
    }
}
