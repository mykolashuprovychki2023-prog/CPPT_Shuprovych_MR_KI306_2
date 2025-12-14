package KI306.Shuprovych.Lab3;

/**
 * Місце у вагоні: номер, статус бронювання та пасажир.
 */
public class Seat {

    private final int number;
    private boolean reserved;
    private String passenger;

    /** Стандартне місце №1 */
    public Seat() {
        this.number = 1;
        this.reserved = false;
        this.passenger = null;
    }

    /** Місце з вказаним номером */
    public Seat(int number) {
        this.number = number;
        this.reserved = false;
        this.passenger = null;
    }

    /** Копіювання місця */
    public Seat(Seat other) {
        this.number = other.number;
        this.reserved = other.reserved;
        this.passenger = other.passenger;
    }

    /** Бронювання місця */
    public boolean reserve(String passengerName) {
        if (reserved) return false;
        this.reserved = true;
        this.passenger = passengerName;
        return true;
    }

    /** Скасування бронювання */
    public boolean cancel() {
        if (!reserved) return false;
        this.reserved = false;
        this.passenger = null;
        return true;
    }

    /** @return чи заброньовано */
    public boolean isReserved() { return reserved; }

    /** @return номер місця */
    public int getNumber() { return number; }

    /** @return коротка інформація про місце */
    public String info() {
        return reserved ? String.format("Seat %d: reserved by %s", number, passenger)
                : String.format("Seat %d: free", number);
    }

    @Override
    public String toString() { return info(); }
}
