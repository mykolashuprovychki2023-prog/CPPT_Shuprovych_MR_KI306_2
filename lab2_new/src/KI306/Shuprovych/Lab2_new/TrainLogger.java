package KI306.Shuprovych.Lab2_new;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Клас TrainLogger — логер для запису активності поїзда у текстовий файл.
 * Реалізує інтерфейс Closeable для коректного закриття файлу.
 * Підтримує синхронізоване записування рядків з таймштампом.
 */
class TrainLogger implements Closeable {

    /** Потік для запису у файл */
    private final BufferedWriter writer;

    /** Формат дати та часу для таймштампа */
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

    /** Позначка закриття логера */
    private boolean closed = false;

    /**
     * Конструктор логера.
     * Відкриває файл для дозапису та пише початковий рядок.
     * @param filePath шлях до файлу логів
     * @throws IOException у разі проблем з файлом
     */
    public TrainLogger(String filePath) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filePath, true));
        logRaw("--- Logger opened: " + LocalDateTime.now().format(fmt));
    }

    /**
     * Записує повідомлення у файл з таймштампом.
     * Синхронізовано для потокобезпечності.
     * @param message повідомлення для запису
     */
    public synchronized void log(String message) {
        try {
            logRaw(LocalDateTime.now().format(fmt) + " - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Внутрішній метод для безпосереднього запису рядка у файл.
     * Перевіряє, чи логер не закритий.
     * @param line рядок для запису
     * @throws IOException якщо логер закритий або виникла помилка запису
     */
    private synchronized void logRaw(String line) throws IOException {
        if (closed) throw new IOException("Logger is closed");
        writer.write(line);
        writer.newLine();
        writer.flush();
    }

    /**
     * Закриває логер та файл.
     * Пише фінальний рядок у файл перед закриттям.
     * @throws IOException у разі помилки закриття файлу
     */
    @Override
    public synchronized void close() throws IOException {
        if (!closed) {
            logRaw("--- Logger closed: " + LocalDateTime.now().format(fmt));
            writer.close();
            closed = true;
        }
    }
}
