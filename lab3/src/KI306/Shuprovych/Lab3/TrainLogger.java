package KI306.Shuprovych.Lab3;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Файл-логер для запису подій поїзда.
 * Підтримує автоматичне закриття через try-with-resources.
 */
public class TrainLogger implements Closeable {

    private final BufferedWriter writer;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private boolean closed = false;

    /** Створює логер та відкриває файл для додавання логів */
    public TrainLogger(String filePath) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filePath, true));
        logRaw("--- Logger opened: " + LocalDateTime.now().format(fmt));
    }

    /** Логує повідомлення з таймштампом */
    public synchronized void log(String message) {
        try {
            logRaw(LocalDateTime.now().format(fmt) + " - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Внутрішній метод запису рядка у файл */
    private synchronized void logRaw(String line) throws IOException {
        if (closed) throw new IOException("Logger is closed");
        writer.write(line);
        writer.newLine();
        writer.flush();
    }

    /** Закриває логер і файл */
    @Override
    public synchronized void close() throws IOException {
        if (!closed) {
            logRaw("--- Logger closed: " + LocalDateTime.now().format(fmt));
            writer.close();
            closed = true;
        }
    }
}
