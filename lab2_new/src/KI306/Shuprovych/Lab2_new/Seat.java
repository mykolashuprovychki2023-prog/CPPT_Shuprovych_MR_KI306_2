package KI306.Shuprovych.Lab2_new;

/**
 * Клас Seat — описує одне місце у вагоні.
 * Містить номер, стан бронювання та ім'я пасажира.
 * Підтримує копіювання.
 */
class Seat {

    /** Номер місця у вагоні */
    private final int number;

    /** Стан бронювання: true — місце зайняте, false — вільне */
    private boolean reserved;

    /** Ім'я пасажира, який зайняв місце (null, якщо вільне) */
    private String passenger;

    /**
     * Конструктор за замовчуванням.
     */
    public Seat() {
        this.number = 1;
        this.reserved = false;
        this.passenger = null;
    }


    /**
     * Конструктор для створення місця з номером.
     * Спочатку місце вільне.
     * @param number номер місця
     */
    public Seat(int number) {
        this.number = number;
        this.reserved = false;
        this.passenger = null;
    }

    /**
     * Копіювальний конструктор для deep copy місця.
     * @param other місце для копіювання
     */
    public Seat(Seat other) {
        this.number = other.number;
        this.reserved = other.reserved;
        this.passenger = other.passenger;
    }

    /**
     * Бронює місце для пасажира.
     * @param passengerName ім'я пасажира
     * @return true, якщо бронювання пройшло успішно, false якщо місце вже зайняте
     */
    public boolean reserve(String passengerName) {
        if (reserved) return false;
        this.reserved = true;
        this.passenger = passengerName;
        return true;
    }

    /**
     * Скасовує бронювання місця.
     * @return true, якщо скасування пройшло успішно, false якщо місце було вільне
     */
    public boolean cancel() {
        if (!reserved) return false;
        this.reserved = false;
        this.passenger = null;
        return true;
    }

    /** Перевіряє, чи місце зайняте */
    public boolean isReserved() { return reserved; }

    /** Повертає номер місця */
    public int getNumber() { return number; }

    /** Повертає рядок з інформацією про місце (номер та стан) */
    public String info() {
        return String.format("Seat %d: %s", number, reserved ? "reserved by " + passenger : "free");
    }
}
