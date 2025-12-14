package KI306.Shuprovych.Lab3;

/**
 * Вагон поїзда.
 * Містить інформацію про тип та місця.
 */
public class Carriage {

    /** Типи вагонів */
    public enum Type { PASSENGER, SLEEPING, CARGO }

    private final String id;
    private final Type type;
    private final Seat[] seats;

    /** Конструктор за замовчуванням — пасажирський вагон з 10 місцями */
    public Carriage() {
        this.id = "C0";
        this.type = Type.PASSENGER;
        this.seats = new Seat[10];
        for (int i = 0; i < seats.length; i++) seats[i] = new Seat(i + 1);
    }

    /** Конструктор з параметрами: id, тип та кількість місць */
    public Carriage(String id, Type type, int seatCount) {
        this.id = id;
        this.type = type;
        this.seats = new Seat[Math.max(0, seatCount)];
        for (int i = 0; i < seats.length; i++) seats[i] = new Seat(i + 1);
    }

    /** Конструктор копіювання */
    public Carriage(Carriage other) {
        this.id = other.id;
        this.type = other.type;
        this.seats = new Seat[other.seats.length];
        for (int i = 0; i < other.seats.length; i++) this.seats[i] = new Seat(other.seats[i]);
    }

    /** Отримати id вагону */
    public String getId() { return id; }

    /** Отримати тип вагону */
    public Type getType() { return type; }

    /** Отримати кількість місць */
    public int getSeatCount() { return seats.length; }

    /** Отримати місце за номером */
    public Seat getSeat(int number) {
        if (number < 1 || number > seats.length) return null;
        return seats[number - 1];
    }

    /** Отримати кількість вільних місць */
    public int getAvailableSeats() {
        int c = 0;
        for (Seat s : seats) if (!s.isReserved()) c++;
        return c;
    }

    /** Коротка інформація про вагон */
    public String shortInfo() {
        return String.format("Carriage %s: type=%s, seats=%d, free=%d", id, type, getSeatCount(), getAvailableSeats());
    }

    /** Детальна інформація про вагон з місцями */
    public String detailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(shortInfo()).append("\n");
        for (Seat s : seats) sb.append("  ").append(s.info()).append("\n");
        return sb.toString();
    }

    /** Повертає коротку інформацію для toString */
    @Override
    public String toString() { return shortInfo(); }
}
