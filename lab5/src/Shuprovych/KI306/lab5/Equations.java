package Shuprovych.KI306.lab5;

/**
 * Клас Equations — містить метод для обчислення виразу
 *   y = cos(x) / ( x + 2 * ctg(x) )
 * де x задано в градусах.
 */
public class Equations {

    /** Допуск для перевірок на нуль (через похибки числення) */
    private static final double EPS = 1e-12;

    /**
     * Обчислює y = cos(x) / ( x + 2 * ctg(x) ), де x у градусах.
     *
     * Перевіряє:
     *  - чи визначений ctg(x) (sin(rad) ≠ 0),
     *  - чи не дорівнює нулю знаменник D = x + 2*ctg(x),
     *  - чи отримане значення є скінченним (не NaN/Infinity).
     *
     * @param x кут у градусах
     * @return значення y
     * @throws CalcException якщо x призводить до невизначеності або числової помилки
     */
    public double calculate(double x) throws CalcException {
        double rad = Math.toRadians(x); // переклад в радіани
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);

        // Перевірка: ctg(x) визначено лише якщо sin != 0
        if (Math.abs(sin) < EPS) {
            throw new CalcException("Exception reason: ctg(x) is undefined (sin(x) == 0). x = " + x);
        }

        double ctg = cos / sin;                 // ctg(x)
        double denom = rad + 2.0 * ctg;           // знаменник виразу

        // Перевірка знаменника на нуль (з урахуванням EPS)
        if (Math.abs(denom) < EPS) {
            throw new CalcException("Exception reason: denominator is zero (x + 2*cot(x) == 0). x = " + x);
        }

        double y = cos / denom;

        // Перевірка на NaN / Infinity
        if (!Double.isFinite(y)) {
            throw new CalcException("Exception reason: result is not finite (NaN or Infinity) for x = " + x);
        }

        return y;
    }
}