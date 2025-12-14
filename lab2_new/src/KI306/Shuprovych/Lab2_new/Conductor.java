package KI306.Shuprovych.Lab2_new;

/**
 * Клас Conductor — описує машиніста або кондуктора поїзда.
 * Містить ім'я та ліцензію, підтримує копіювання.
 */
class Conductor {

    /** Ім'я кондуктора */
    private final String name;

    /** Ліцензія або номер посвідчення */
    private final String license;

    /**
     * Конструктор за замовчуванням.
     */
    public Conductor() {
        this.name = "NoName";
        this.license = "None";
    }


    /**
     * Основний конструктор кондуктора.
     * @param name ім'я
     * @param license ліцензія
     */
    public Conductor(String name, String license) {
        this.name = name;
        this.license = license;
    }

    /**
     * Копіювальний конструктор для deep copy кондуктора.
     * @param other об'єкт Conductor для копіювання
     */
    public Conductor(Conductor other) {
        this.name = other.name;
        this.license = other.license;
    }

    /** Повертає ім'я кондуктора */
    public String getName() { return name; }

    /** Повертає ліцензію кондуктора */
    public String getLicense() { return license; }
}
