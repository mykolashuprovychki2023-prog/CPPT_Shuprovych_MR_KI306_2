package KI306.Shuprovych.Lab3;

/**
 * Кондуктор або машиніст поїзда: ім'я та ліцензія.
 */
public class Conductor {

    private final String name;
    private final String license;

    /** Кондуктор за замовчуванням */
    public Conductor() {
        this.name = "NoName";
        this.license = "None";
    }

    /** Створення кондуктора з параметрами */
    public Conductor(String name, String license) {
        this.name = name;
        this.license = license;
    }

    /** Копіювання кондуктора */
    public Conductor(Conductor other) {
        this.name = other.name;
        this.license = other.license;
    }

    /** @return ім'я */
    public String getName() { return name; }

    /** @return ліцензія */
    public String getLicense() { return license; }

    @Override
    public String toString() {
        return String.format("Conductor{name='%s', license='%s'}", name, license);
    }
}
