package Shuprovych.KI306.lab5;

import java.io.*;
import java.util.Scanner;

/**
 * ResultIO — клас для запису/читання результатів у текстовому та бінарному форматах.
 * Підтримує запис довільного тексту у файл (append) для логування помилок.
 */
public class ResultIO {

    /**
     * Записує/додає текстовий рядок у текстовий файл.
     * @param fname шлях до файлу
     * @param text рядок для запису
     * @param append true — дописати в кінець, false — перезаписати
     * @throws IOException
     */
    public void writeText(String fname, String text, boolean append) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fname, append))) {
            pw.println(text);
        }
    }

    /**
     * Записує значення double у текстовий файл (форматоване).
     * @param fname шлях до файлу
     * @param result значення для запису
     * @param append true — дописати, false — перезаписати
     * @throws IOException
     */
    public void writeResultTxt(String fname, double result, boolean append) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fname, append))) {
            pw.printf("%.10f%n", result);
        }
    }

    /**
     * Читає останнє double з текстового файлу.
     * @param fname шлях до файлу
     * @return прочитане значення
     * @throws IOException якщо файл не знайдено або неможливо прочитати
     */
    /**
     * Повертає останнє double у текстовому файлі (шукає всі токени double і повертає останній).
     */
    public double readLastDoubleFromTxt(String fname) throws IOException {
        File f = new File(fname);
        if (!f.exists()) throw new FileNotFoundException("File not found: " + fname);
        double last = Double.NaN;
        boolean found = false;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
                    last = sc.nextDouble();
                    found = true;
                } else {
                    sc.next();
                }
            }
        }
        if (!found) throw new IOException("No double value found in file: " + fname);
        return last;
    }

    /**
     * Записує double у бінарний файл (перезаписує або дописує).
     * @param fname шлях до файлу
     * @param result значення
     * @param append true — дозапис (append), false — перезапис
     * @throws IOException
     */
    public void writeResultBin(String fname, double result, boolean append) throws IOException {
        // Для append у бінарному режимі треба відкривати FileOutputStream з append=true;
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fname, append)))) {
            dos.writeDouble(result);
        }
    }

    /**
     * Читає останній double з бінарного файлу.
     * @param fname шлях до файлу
     * @return перше double, прочитане з файлу
     * @throws IOException
     */
    public double readLastDoubleFromBin(String fname) throws IOException {
        File f = new File(fname);
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long len = raf.length();
            raf.seek(len - 8);      // 8 байт = один double
            return raf.readDouble(); // ← читаємо останнє double
        }
    }
}
