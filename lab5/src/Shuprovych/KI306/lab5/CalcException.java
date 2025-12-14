package Shuprovych.KI306.lab5;

/**
 * Контрольоване виключення для помилок обчислень.
 * Використовується, коли аргумент або результат обчислення недопустимі.
 */
public class CalcException extends Exception {
    public CalcException() { super(); }
    public CalcException(String message) { super(message); }
    public CalcException(String message, Throwable cause) { super(message, cause); }
}
