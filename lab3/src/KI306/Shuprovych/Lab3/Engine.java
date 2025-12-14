package KI306.Shuprovych.Lab3;

/**
 * Двигун поїзда: модель, потужність, рік випуску.
 */
public class Engine {

    private String model;
    private int power;
    private int year;

    /** Стандартний двигун: конструктор зп замовчуванням */
    public Engine() {
        this.model = "Std-Engine";
        this.power = 1000;
        this.year = 2000;
    }

    /** Створення двигуна з параметрами */
    public Engine(String model, int power, int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }

    /** Копіювання двигуна */
    public Engine(Engine other) {
        this.model = other.model;
        this.power = other.power;
        this.year = other.year;
    }

    /** @return модель */
    public String getModel() { return model; }

    /** @return потужність */
    public int getPower() { return power; }

    /** @return рік випуску */
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("Engine{model='%s', power=%d, year=%d}", model, power, year);
    }
}
