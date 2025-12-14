package KI306.Shuprovych.Lab3;

import java.util.*;

/**
 * Абстрактний суперклас Train — загальні операції для поїздів.
 */
public abstract class Train {

    private final String name;
    private Engine engine;
    private List<Carriage> carriages;
    private Conductor conductor;
    private final TrainLogger logger;
    private String currentStation;
    private boolean inService;
    private String origin;
    private String destination;

    /** Захищений конструктор для підкласів */
    protected Train(String name, Engine engine, List<Carriage> carriages, Conductor conductor,
                    TrainLogger logger, String origin, String destination) {
        this.name = name == null ? "Unnamed" : name;
        this.engine = engine != null ? new Engine(engine) : new Engine();
        this.carriages = new ArrayList<>();
        if (carriages != null) for (Carriage c : carriages) this.carriages.add(new Carriage(c));
        this.conductor = conductor != null ? new Conductor(conductor) : new Conductor();
        this.logger = logger;
        this.currentStation = origin != null ? origin : "Depot";
        this.inService = false;
        this.origin = origin;
        this.destination = destination;
        log("Train created (abstract): " + this.name);
    }

    /** Копіювальний конструктор */
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

    /** Додає вагон */
    public void addCarriage(Carriage c) {
        carriages.add(new Carriage(c));
        log("Added carriage: " + c.getId());
    }

    /** Видаляє вагон за ID */
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

    /** Повертає загальну кількість місць */
    public int getTotalSeats() {
        int total = carriages.stream().mapToInt(Carriage::getSeatCount).sum();
        log("Total seats calculated: " + total);
        return total;
    }

    /** Повертає кількість вільних місць */
    public int countAvailableSeats() {
        int free = carriages.stream().mapToInt(Carriage::getAvailableSeats).sum();
        log("Available seats: " + free);
        return free;
    }

    /** Знаходить місце у вагоні */
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

    /** Резервує місце */
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

    /** Переміщує поїзд до наступної станції */
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

    /** Друкує маніфест поїзда */
    public void printManifest() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Manifest for ").append(name).append(" ---\n");
        sb.append("Type: ").append(getTrainType()).append("\n");
        sb.append("Route: ").append(origin == null ? "(not set)" : origin).append(" -> ")
                .append(destination == null ? "(not set)" : destination).append("\n");
        sb.append("Engine: ").append(engine.getModel()).append(" (power: ")
                .append(engine.getPower()).append(", year: ").append(engine.getYear()).append(")\n");
        sb.append("Conductor: ").append(conductor.getName()).append(" (license: ").append(conductor.getLicense()).append(")\n");
        for (Carriage c : carriages) sb.append(c.detailedInfo()).append("\n");
        sb.append("Total seats: ").append(getTotalSeats()).append("\n");
        sb.append("Available seats: ").append(countAvailableSeats()).append("\n");
        log(sb.toString());
        System.out.println(sb.toString());
    }

    /** Завершення роботи поїзда */
    public void shutdown() { log("Train shutdown: " + name); }

    /** Логування через TrainLogger */
    protected void log(String msg) {
        if (logger != null) logger.log("[Train " + name + "] " + msg);
    }

    // --- Геттери ---
    public String getName() { return name; }
    public abstract String getTrainType();
    public String getCurrentStation() { return currentStation; }
    public boolean isInService() { return inService; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
}
