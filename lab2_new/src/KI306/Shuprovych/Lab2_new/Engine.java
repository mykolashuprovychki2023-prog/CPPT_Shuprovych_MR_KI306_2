package KI306.Shuprovych.Lab2_new;

/**
 * Клас Engine — описує двигун поїзда.
 * Містить модель, потужність та рік випуску.
 * Підтримує копіювання через копіювальний конструктор.
 */
class Engine {

    /** Модель двигуна, наприклад "Turbine-3000" */
    private String model;

    /** Потужність двигуна (кВт або к.с.) */
    private int power;

    /** Рік виготовлення двигуна */
    private int year;

    /**
     * Конструктор за замовчуванням.
     * Створює стандартний двигун з базовими параметрами.
     */
    public Engine() {
        this.model = "Std-Engine";
        this.power = 1000;
        this.year = 2000;
    }

    /**
     * Основний конструктор двигуна.
     * @param model модель двигуна
     * @param power потужність
     * @param year рік виготовлення
     */
    public Engine(String model, int power, int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }

    /**
     * Копіювальний конструктор для deep copy.
     * @param other двигун, який потрібно скопіювати
     */
    public Engine(Engine other) {
        this.model = other.model;
        this.power = other.power;
        this.year = other.year;
    }

    /** Повертає модель двигуна */
    public String getModel() { return model; }

    /** Повертає потужність двигуна */
    public int getPower() { return power; }

    /** Повертає рік виготовлення двигуна */
    public int getYear() { return year; }
}
