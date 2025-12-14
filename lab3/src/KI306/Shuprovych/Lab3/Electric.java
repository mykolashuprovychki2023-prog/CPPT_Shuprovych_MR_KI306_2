package KI306.Shuprovych.Lab3;

/**
 * Інтерфейс для електричної тяги поїзда.
 * Містить методи підключення, відключення та отримання характеристик.
 */
public interface Electric {

    /** Підключитися до контактної мережі */
    void connectToCatenary();

    /** Відключитися від контактної мережі */
    void disconnectFromCatenary();

    /** @return напруга контактної мережі (V) */
    int getVoltage();

    /** @return споживана потужність на км */
    double getPowerConsumption();
}
