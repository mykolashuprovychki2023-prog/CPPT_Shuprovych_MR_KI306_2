package KI306.Shuprovych.Lab2_new;

/**
 * Клас Carriage — описує вагон поїзда.
 * Містить ідентифікатор, тип та масив місць.
 * Підтримує копіювання та методи для отримання інформації.
 */
class Carriage {

    /** Тип вагону: пасажирський, спальний або вантажний */
    public enum Type { PASSENGER, SLEEPING, CARGO }

    private final String id;      // унікальний ідентифікатор вагону
    private final Type type;      // тип вагону
    private final Seat[] seats;   // масив місць у вагоні

    /**
     * Конструктор за замовчуванням.
     */
    public Carriage() {
        this.id = "C0";
        this.type = Type.PASSENGER;
        this.seats = new Seat[10];
        for (int i = 0; i < seats.length; i++) seats[i] = new Seat(i + 1);
    }


    /**
     * Основний конструктор вагону.
     * @param id унікальний ідентифікатор
     * @param type тип вагону
     * @param seatCount кількість місць
     */
    public Carriage(String id, Type type, int seatCount) {
        this.id = id;
        this.type = type;
        this.seats = new Seat[seatCount];
        for (int i = 0; i < seatCount; i++) seats[i] = new Seat(i + 1);
    }

    /**
     * Копіювальний конструктор для копіювання вагону разом з місцями.
     * @param other вагон для копіювання
     */
    public Carriage(Carriage other) {
        this.id = other.id;
        this.type = other.type;
        this.seats = new Seat[other.seats.length];
        for (int i = 0; i < other.seats.length; i++)
            this.seats[i] = new Seat(other.seats[i]);
    }

    /** Повертає ідентифікатор вагону */
    public String getId() { return id; }

    /** Повертає тип вагону */
    public Type getType() { return type; }

    /** Повертає кількість місць у вагоні */
    public int getSeatCount() { return seats.length; }

    /**
     * Повертає об'єкт Seat за номером (починається з 1).
     * @param number номер місця
     * @return об'єкт Seat або null, якщо номер некоректний
     */
    public Seat getSeat(int number) {
        if (number < 1 || number > seats.length) return null;
        return seats[number - 1];
    }

    /** Повертає кількість вільних місць у вагоні */
    public int getAvailableSeats() {
        int c = 0;
        for (Seat s : seats) if (!s.isReserved()) c++;
        return c;
    }

    /** Коротка інформація про вагон (для логів та манифесту) */
    public String shortInfo() {
        return String.format("Carriage %s: type=%s, seats=%d, free=%d", id, type, getSeatCount(), getAvailableSeats());
    }

    /** Детальна інформація про вагон та всі місця */
    public String detailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(shortInfo()).append(" ");
        for (Seat s : seats) sb.append("  ").append(s.info()).append(" ");
        return sb.toString();
    }
}
