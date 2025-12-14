package KI306.Shuprovych.Lab2_new;

import java.util.*;

/**
 * Основний клас Train — описує поїзд.
 * Використовує Builder для створення об'єктів, копіювальний конструктор для deep copy.
 * Містить список вагонів, двигун, кондуктора, поточну станцію та логер.
 * Використовує StringBuilder для формування детальної інформації про поїзда.
 */
public class Train {

    /** Назва поїзда */
    private final String name;

    /** Двигун поїзда */
    private Engine engine;

    /** Список вагонів */
    private List<Carriage> carriages;

    /** Кондуктор поїзда */
    private Conductor conductor;

    /** Логер для запису подій поїзда */
    private final TrainLogger logger;

    /** Поточна станція поїзда */
    private String currentStation;

    /** Статус: чи поїзд в русі */
    private boolean inService;

    /** Початкова станція маршруту */
    private String origin;

    /** Кінцева станція маршруту */
    private String destination;

    /**
     * Конструктор Builder.
     * Копіює поля і створює копію об'єктів двигуна, вагонів та кондуктора.
     */
    private Train(Builder b) {
        this.name = b.name;
        this.engine = b.engine != null ? new Engine(b.engine) : new Engine("Std-Engine", 1000, 2000);
        this.carriages = new ArrayList<>();
        if (b.carriages != null)
            for (Carriage c : b.carriages) this.carriages.add(new Carriage(c));
        this.conductor = b.conductor != null ? new Conductor(b.conductor) : new Conductor("NoName", "");
        this.logger = b.logger;
        this.currentStation = b.origin != null ? b.origin : "Depot";
        this.inService = false;
        this.origin = b.origin;
        this.destination = b.destination;
        log("Train created via Builder: " + this.name);
    }

    /**
     * Копіювальний конструктор поїзда.
     * Створює новий поїзд з копіями двигуна, вагонів і кондуктора.
     * @param other поїзд для копіювання
     */
    public Train(Train other) {
        this.name = other.name + "-copy";
        this.engine = new Engine(other.engine);
        this.carriages = new ArrayList<>();
        for (Carriage c : other.carriages) this.carriages.add(new Carriage(c));
        this.conductor = new Conductor(other.conductor);
        this.logger = other.logger;
        this.currentStation = other.currentStation;
        this.inService = other.inService;
        this.origin = other.origin;
        this.destination = other.destination;
        log("Train deep-copied from: " + other.name);
    }

    /** Додає вагон до поїзда (створює копію вагону) */
    public void addCarriage(Carriage c) {
        carriages.add(new Carriage(c));
        log("Added carriage: " + c.getId());
    }

    /**
     * Видаляє вагон за ідентифікатором
     * @param id ідентифікатор вагону
     * @return true, якщо вагон видалено, false якщо вагону не існує
     */
    public boolean removeCarriage(String id) {
        Iterator<Carriage> it = carriages.iterator();
        while (it.hasNext()) {
            Carriage c = it.next();
            if (c.getId().equals(id)) {
                it.remove();
                log("Removed carriage: " + id);
                return true;
            }
        }
        log("Attempted to remove non-existing carriage: " + id);
        return false;
    }

    /** Повертає загальну кількість місць у поїзді */
    public int getTotalSeats() {
        int total = carriages.stream().mapToInt(Carriage::getSeatCount).sum();
        log("Total seats calculated: " + total);
        return total;
    }

    /** Повертає кількість вільних місць у поїзді */
    public int countAvailableSeats() {
        int free = carriages.stream().mapToInt(Carriage::getAvailableSeats).sum();
        log("Available seats: " + free);
        return free;
    }

    /**
     * Шукає місце за номером і вагоном
     * @param carriageId ідентифікатор вагону
     * @param seatNumber номер місця
     * @return Optional об'єкт Seat, якщо знайдено
     */
    public Optional<Seat> findSeat(String carriageId, int seatNumber) {
        for (Carriage c : carriages) {
            if (c.getId().equals(carriageId)) {
                Seat s = c.getSeat(seatNumber);
                log("Searched for seat " + seatNumber + " in " + carriageId + ": " + (s == null ? "not found" : "found"));
                return Optional.ofNullable(s);
            }
        }
        log("Searched for seat in unknown carriage: " + carriageId);
        return Optional.empty();
    }

    /**
     * Бронює місце для пасажира
     * @param carriageId вагон
     * @param seatNumber номер місця
     * @param passengerName ім'я пасажира
     * @return true, якщо бронювання успішне
     */
    public boolean reserveSeat(String carriageId, int seatNumber, String passengerName) {
        Optional<Seat> sOpt = findSeat(carriageId, seatNumber);
        if (sOpt.isPresent()) {
            Seat s = sOpt.get();
            boolean ok = s.reserve(passengerName);
            log("Reserve seat " + seatNumber + " in " + carriageId + " for " + passengerName + ": " + ok);
            return ok;
        }
        return false;
    }

    /** Скасовує бронювання місця */
    public boolean cancelReservation(String carriageId, int seatNumber) {
        Optional<Seat> sOpt = findSeat(carriageId, seatNumber);
        if (sOpt.isPresent()) {
            boolean ok = sOpt.get().cancel();
            log("Cancel reservation for seat " + seatNumber + " in " + carriageId + ": " + ok);
            return ok;
        }
        return false;
    }

    /** Починає рух поїзда */
    public void startJourney() {
        if (origin == null || destination == null) {
            log("Cannot start journey: origin or destination not set");
            return;
        }
        this.currentStation = origin;
        this.inService = true;
        log("Journey started: " + origin + " -> " + destination);
    }

    /** Рухається до наступної станції */
    public void moveToNextStation(String station) {
        if (!inService) {
            log("Move attempted while not in service");
            return;
        }
        log("Moving from " + this.currentStation + " to " + station);
        this.currentStation = station;
    }

    /** Зупиняє поїзд */
    public void stopJourney() {
        this.inService = false;
        log("Journey stopped at: " + this.currentStation);
    }

    /** Друкує маніфест поїзда з інформацією про вагон, двигун, кондуктора та місця */
    public void printManifest() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Manifest for ").append(name).append(" --- ");
        sb.append("Route: ").append(origin == null ? "(not set)" : origin).append(" -> ")
                .append(destination == null ? "(not set)" : destination).append(" ");
        sb.append("Engine: ").append(engine.getModel()).append(" (power: ")
                .append(engine.getPower()).append(", year: ").append(engine.getYear()).append(") ");
        sb.append("Conductor: ").append(conductor.getName()).append(" (license: ").append(conductor.getLicense()).append(") ");
        for (Carriage c : carriages) sb.append(c.detailedInfo()).append(" ");
        sb.append("Total seats: ").append(getTotalSeats()).append(" ");
        sb.append("Available seats: ").append(countAvailableSeats()).append(" ");
        log(sb.toString());
    }

    /** Завершення роботи поїзда */
    public void shutdown() { log("Train shutdown: " + name); }

    /** Внутрішній метод для логування повідомлень через TrainLogger */
    private void log(String msg) {
        if (logger != null) logger.log("[Train " + name + "] " + msg);
    }

    /**
     * Builder для поїзда
     */
    public static class Builder {
        private String name;
        private Engine engine;
        private List<Carriage> carriages;
        private Conductor conductor;
        private TrainLogger logger;
        private String origin;
        private String destination;

        public Builder name(String name) { this.name = name; return this; }
        public Builder engine(Engine engine) { this.engine = engine; return this; }
        public Builder carriages(List<Carriage> carriages) { this.carriages = carriages; return this; }
        public Builder conductor(Conductor conductor) { this.conductor = conductor; return this; }
        public Builder logger(TrainLogger logger) { this.logger = logger; return this; }
        public Builder origin(String origin) { this.origin = origin; return this; }
        public Builder destination(String destination) { this.destination = destination; return this; }

        /** Створює новий об'єкт поїзда */
        public Train build() { return new Train(this); }
    }
}
